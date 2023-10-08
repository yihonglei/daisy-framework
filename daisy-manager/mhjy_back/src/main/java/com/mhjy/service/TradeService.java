package com.mhjy.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ijpay.core.IJPayHttpResponse;
import com.ijpay.core.enums.RequestMethod;
import com.ijpay.core.kit.PayKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.core.utils.DateTimeZoneUtil;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.enums.WxApiType;
import com.ijpay.wxpay.enums.WxDomain;
import com.ijpay.wxpay.model.v3.Amount;
import com.ijpay.wxpay.model.v3.Payer;
import com.ijpay.wxpay.model.v3.UnifiedOrderModel;
import com.mhjy.entity.*;
import com.mhjy.enums.ErrorCodeEnum;
import com.mhjy.enums.GoodsHistoryTypeEnum;
import com.mhjy.exception.BizException;
import com.mhjy.mapper.TradeMapper;
import com.mhjy.pojo.Bo.*;
import com.mhjy.pojo.Dto.SysChargeListDto;
import com.mhjy.pojo.Dto.SysFinanceChargeAnyDto;
import com.mhjy.pojo.Dto.TradeDto;
import com.mhjy.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.cert.X509Certificate;
import java.util.*;

@Service
public class TradeService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final static int OK = 200;

    String serialNo;

    @Resource
    WxPayV3Bean wxPayV3Bean;

    @Autowired
    private UserService userService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    PaperService paperService;

    @Autowired
    TradeMapper tradeMapper;

    public List<SysTradeBO> sysChargeList(String token, SysChargeListDto sysChargeListDto) throws Exception {
        int status = sysChargeListDto.getStatus();
        int type = sysChargeListDto.getType();
        String startDate = sysChargeListDto.getStartDate();
        String endDate = sysChargeListDto.getEndDate();

        List<SysTradeBO> result = new ArrayList<>();
        SysUserBO sysUserBO = sysUserService.userinfo(token);
        Boolean containAdmin = false;
        for (Object object:sysUserBO.getRoles()) {
            if (object instanceof String) {
                String rolename = (String) object;
                if (rolename.equals("admin")) {
                    containAdmin = true;
                }
            }
        }
        long cid = sysUserBO.getId();

        List<Trade> listTmp;
        if (containAdmin) {//超级管理员
            listTmp = tradeMapper.sysTradeList(0, type, status, startDate, endDate);
        } else {
            listTmp = tradeMapper.sysTradeList(cid, type, status, startDate, endDate);
        }

        for (Trade trade: listTmp) {
            SysTradeBO sysTradeBO = new SysTradeBO();
            BeanUtils.copyProperties(trade, sysTradeBO);
            long uid = trade.getUid();
            User user = userService.getUserInfoWithUid(uid);
            sysTradeBO.setUsername(user.getNickname());
            result.add(sysTradeBO);
        }

        return result;
    }

    public Trade tradeWithTradeNo(String tradeno) {

        return tradeMapper.tradeWithTradeNo(tradeno);
    }

    public Boolean updateStatus(String tradeno, int status) {

        return tradeMapper.updateStatus(tradeno, status) == 1;
    }

    public SysFinanceChargeAnyBO sysFinanceChargeAny(HeaderBO headerBO, SysFinanceChargeAnyDto sysFinanceChargeAnyDto) {
        SysFinanceChargeAnyBO sysFinanceChargeAnyBO = new SysFinanceChargeAnyBO();

        Date date = sysFinanceChargeAnyDto.getDate();
        int index = sysFinanceChargeAnyDto.getIndex();
        String totalAmount = tradeMapper.totalChargeMoney(date, index, 0);
        String vipAmount = tradeMapper.totalChargeMoney(date, index, 1);
        String conAmount = tradeMapper.totalChargeMoney(date, index, 2);
        String pullAmount = tradeMapper.totalChargeMoney(date, index, 3);
        String pushAmount = tradeMapper.totalChargeMoney(date, index, 4);

        if (totalAmount == null || totalAmount.isEmpty()) {
            totalAmount = "0";
        }

        if (vipAmount == null || vipAmount.isEmpty()) {
            vipAmount = "0";
        }

        if (conAmount == null || conAmount.isEmpty()) {
            conAmount = "0";
        }

        if (pullAmount == null || pullAmount.isEmpty()) {
            pullAmount = "0";
        }

        if (pushAmount == null || pushAmount.isEmpty()) {
            pushAmount = "0";
        }

        sysFinanceChargeAnyBO.setTotalAmount(totalAmount);
        sysFinanceChargeAnyBO.setVipAmount(vipAmount);
        sysFinanceChargeAnyBO.setConAmount(conAmount);
        sysFinanceChargeAnyBO.setPullAmount(pullAmount);
        sysFinanceChargeAnyBO.setPushAmount(pushAmount);

        return sysFinanceChargeAnyBO;
    }

    public TradeBO wxPrepayJsApiV3(TradeDto tradeDto) throws Exception {
        long uid = tradeDto.getUid();
        long id = tradeDto.getGid();
        int type = tradeDto.getType();
        User user = userService.getUserInfoWithUid(uid);
        if (user == null) {
            throw new BizException(ErrorCodeEnum.NOT_FOUND_CUSTOMER);
        }
        if (type == 1 || type == 2) {
            Goods goods = goodsService.getGoodsWithGoodId(id);

            return CreateGoodsOrder(user, goods, type);
        } else if (type == 3 || type == 4) {
            Paper paper = paperService.paperWithId(id);

            return CreatePaperOrder(user, paper, type);
        }

        return null;
    }

    /**
     * 微信创建订单 1 2
     *
     * @throws Exception
     */
    public TradeBO CreateGoodsOrder(User user, Goods goods, int type) throws Exception {
        try {
            String trade_no = PayKit.generateStr();
            String timeExpire = DateTimeZoneUtil.dateToTimeZone(System.currentTimeMillis() + 1000 * 60 * 3);

            int money = (int)goods.getSale_price()*100;
            if (user.getId() == 1) {
                money = 1;
            }

            UnifiedOrderModel unifiedOrderModel = new UnifiedOrderModel()
                    .setAppid(wxPayV3Bean.getAppId())
                    .setMchid(wxPayV3Bean.getMchId())
                    .setDescription(goods.getGood_name())
                    .setOut_trade_no(trade_no)
                    .setTime_expire(timeExpire)
                    .setAttach(goods.getGood_name())
                    .setNotify_url(wxPayV3Bean.getDomain().concat("/api/wxPrepayGoodsNotify"))
                    .setAmount(new Amount().setTotal(money))
                    .setPayer(new Payer().setOpenid(user.getOpenid()));

            log.info("统一下单参数 {}", JSONUtil.toJsonStr(unifiedOrderModel));
            IJPayHttpResponse response = WxPayApi.v3(
                    RequestMethod.POST,
                    WxDomain.CHINA.toString(),
                    WxApiType.JS_API_PAY.toString(),
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    null,
                    wxPayV3Bean.getKeyPath(),
                    JSONUtil.toJsonStr(unifiedOrderModel)
            );
            log.info("统一下单响应 {}", response);

            if (response.getStatus() == OK) {
                // 根据证书序列号查询对应的证书来验证签名结果
                boolean verifySignature = WxPayKit.verifySignature(response, wxPayV3Bean.getPlatformCertPath());
                log.info("=======verifySignature: ======={}=======", verifySignature);
                if (verifySignature) {
                    String body = response.getBody();
                    JSONObject jsonObject = JSONUtil.parseObj(body);
                    String prepayId = jsonObject.getStr("prepay_id");

                    log.info("=======开始生成本地账单信息=========:{}=======", prepayId);
                    createPlatformOrder(user.getId(), type, goods.getId(), goods.getSale_price() * 100, trade_no, prepayId);
                    log.info("=======结束生成本地账单信息=========:{}=======", prepayId);

                    Map<String, String> map = WxPayKit.jsApiCreateSign(wxPayV3Bean.getAppId(), prepayId, wxPayV3Bean.getKeyPath());
                    log.info("=======唤起支付参数:======={}=======", map);
                    TradeBO tradeBO = new TradeBO();
                    tradeBO.setPackages(map.get("package"));
                    tradeBO.setSignType(map.get("signType"));
                    tradeBO.setNonceStr(map.get("nonceStr"));
                    tradeBO.setPaySign(map.get("paySign"));
                    tradeBO.setAppId(map.get("appId"));
                    tradeBO.setTimeStamp(map.get("timeStamp"));

                    return tradeBO;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 微信创建订单 3 4
     *
     * @throws Exception
     */
    public TradeBO CreatePaperOrder(User user, Paper paper, int type) throws Exception {
        try {
            String trade_no = PayKit.generateStr();
            String timeExpire = DateTimeZoneUtil.dateToTimeZone(System.currentTimeMillis() + 1000 * 60 * 3);

            int money = paper.getMoney()*100;
            if (user.getId() == 1 || user.getId() == 99) {
                money = 1;
            }

            UnifiedOrderModel unifiedOrderModel = new UnifiedOrderModel()
                    .setAppid(wxPayV3Bean.getAppId())
                    .setMchid(wxPayV3Bean.getMchId())
                    .setDescription(user.getNickname())
                    .setOut_trade_no(trade_no)
                    .setTime_expire(timeExpire)
                    .setAttach(user.getNickname())
                    .setNotify_url(wxPayV3Bean.getDomain().concat("/api/wxPrepayPaperNotify"))
                    .setAmount(new Amount().setTotal(money))
                    .setPayer(new Payer().setOpenid(user.getOpenid()));

            log.info("统一下单参数 {}", JSONUtil.toJsonStr(unifiedOrderModel));
            IJPayHttpResponse response = WxPayApi.v3(
                    RequestMethod.POST,
                    WxDomain.CHINA.toString(),
                    WxApiType.JS_API_PAY.toString(),
                    wxPayV3Bean.getMchId(),
                    getSerialNumber(),
                    null,
                    wxPayV3Bean.getKeyPath(),
                    JSONUtil.toJsonStr(unifiedOrderModel)
            );
            log.info("统一下单响应 {}", response);

            if (response.getStatus() == OK) {
                // 根据证书序列号查询对应的证书来验证签名结果
                boolean verifySignature = WxPayKit.verifySignature(response, wxPayV3Bean.getPlatformCertPath());
                log.info("=======verifySignature: ======={}=======", verifySignature);
                if (verifySignature) {
                    String body = response.getBody();
                    JSONObject jsonObject = JSONUtil.parseObj(body);
                    String prepayId = jsonObject.getStr("prepay_id");

                    log.info("=======开始生成本地账单信息=========:{}=======", prepayId);
                    createPlatformOrder(user.getId(), type, paper.getId(), paper.getMoney() * 100, trade_no, prepayId);
                    log.info("=======结束生成本地账单信息=========:{}=======", prepayId);

                    Map<String, String> map = WxPayKit.jsApiCreateSign(wxPayV3Bean.getAppId(), prepayId, wxPayV3Bean.getKeyPath());
                    log.info("=======唤起支付参数:======={}=======", map);
                    TradeBO tradeBO = new TradeBO();
                    tradeBO.setPackages(map.get("package"));
                    tradeBO.setSignType(map.get("signType"));
                    tradeBO.setNonceStr(map.get("nonceStr"));
                    tradeBO.setPaySign(map.get("paySign"));
                    tradeBO.setAppId(map.get("appId"));
                    tradeBO.setTimeStamp(map.get("timeStamp"));

                    return tradeBO;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Trade createPlatformOrder(long uid, int type, long alongid, double fen, String trade_no, String prepayId) {

        log.info("============{}={}={}={}={}={}===========", uid, type, alongid, fen, trade_no, prepayId);
        Date now = new Date();

        UserExtr userExtr = userService.getUserExtrWithUid(uid);

        Trade trade = new Trade();
        trade.setUid(uid);
        trade.setType(type);
        trade.setAlong_id(alongid);
        trade.setCid(userExtr.getCid());
        trade.setTrade_no(trade_no);
        trade.setPrepay_id(prepayId);
        trade.setFen(fen);
        trade.setCreated_at(now);
        trade.setUpdated_at(now);
        boolean success = tradeMapper.trade(trade) == 1;

        if (success) {
            return trade;
        }
        return null;
    }

    private String getSerialNumber() {
        if (StrUtil.isEmpty(serialNo)) {
            // 获取证书序列号
            X509Certificate certificate = PayKit.getCertificate(FileUtil.getInputStream(wxPayV3Bean.getCertPath()));
            serialNo = certificate.getSerialNumber().toString(16).toUpperCase();

//            System.out.println("输出证书信息:\n" + certificate.toString());
//            // 输出关键信息，截取部分并进行标记
//            System.out.println("证书序列号:" + certificate.getSerialNumber().toString(16));
//            System.out.println("版本号:" + certificate.getVersion());
//            System.out.println("签发者：" + certificate.getIssuerDN());
//            System.out.println("有效起始日期：" + certificate.getNotBefore());
//            System.out.println("有效终止日期：" + certificate.getNotAfter());
//            System.out.println("主体名：" + certificate.getSubjectDN());
//            System.out.println("签名算法：" + certificate.getSigAlgName());
//            System.out.println("签名：" + certificate.getSignature().toString());
        }
        System.out.println("serialNo:" + serialNo);
        return serialNo;
    }
}
