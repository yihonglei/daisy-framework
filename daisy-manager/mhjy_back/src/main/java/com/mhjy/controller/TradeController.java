package com.mhjy.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ijpay.core.kit.HttpKit;
import com.ijpay.core.kit.WxPayKit;
import com.mhjy.entity.*;
import com.mhjy.enums.ErrorCodeEnum;
import com.mhjy.enums.GoodsHistoryTypeEnum;
import com.mhjy.enums.RemainTypeEnum;
import com.mhjy.exception.BizException;
import com.mhjy.pojo.Bo.TradeBO;
import com.mhjy.pojo.Bo.UserBO;
import com.mhjy.pojo.Dto.TradeDto;
import com.mhjy.service.*;
import com.mhjy.util.ApiResponse;
import com.mhjy.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/api")
public class TradeController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    WxPayV3Bean wxPayV3Bean;

    @Autowired
    UserService userService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysCompanyUserService sysCompanyUserService;

    @Autowired
    TradeService tradeService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    PaperService paperService;


    @Autowired
    MasterService masterService;

    @Autowired
    FundService fundService;

    @Autowired
    PaperHistoryService paperHistoryService;

    @RequestMapping(value = "wxPrepay")
    public ApiResponse<TradeBO> wxprepayJsApi(@RequestBody TradeDto tradeDto) {
        try {
            ApiResponse<TradeBO> result = ApiResponse.success(tradeService.wxPrepayJsApiV3(tradeDto));
            return result;
        } catch (BizException e) {
            return ApiResponse.error(ErrorCodeEnum.NOT_FOUND_CUSTOMER.getCode(), ErrorCodeEnum.NOT_FOUND_CUSTOMER.getMsg(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR.getCode(), ErrorCodeEnum.SYSTEM_DEFAULT_ERROR.getMsg(), null);
        }
    }

    @RequestMapping(value = "/wxPrepayPaperNotify", method = {org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public void payPaperNotify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = new HashMap<>(12);
        try {
            String timestamp = request.getHeader("Wechatpay-Timestamp");
            String nonce = request.getHeader("Wechatpay-Nonce");
            String serialNo = request.getHeader("Wechatpay-Serial");
            String signature = request.getHeader("Wechatpay-Signature");

            log.info("timestamp:{} nonce:{} serialNo:{} signature:{}", timestamp, nonce, serialNo, signature);
            String result = HttpKit.readData(request);
            log.info("支付通知密文 {}", result);

            // 需要通过证书序列号查找对应的证书，verifyNotify 中有验证证书的序列号
            String plainText = WxPayKit.verifyNotify(serialNo, result, signature, nonce, timestamp,
                    wxPayV3Bean.getApiKey3(), wxPayV3Bean.getPlatformCertPath());

            log.info("支付通知明文 {}", plainText);

            if (StrUtil.isNotEmpty(plainText)) {
                response.setStatus(200);
                map.put("code", "SUCCESS");
                map.put("message", "SUCCESS");
                JSONObject jsonObject = JSONUtil.parseObj(plainText);
                String out_trade_no = jsonObject.getStr("out_trade_no");
                String openid = jsonObject.getStr("appid");
                log.info("out_trade_no {}", out_trade_no);

                Trade trade = tradeService.tradeWithTradeNo(out_trade_no);
                long alongId = trade.getAlong_id();
                long uid = trade.getUid();
                int type = trade.getType();
                double fen = trade.getFen();
                // 1、新增用户vip or 续费时长 2、更新 ismatch 和 isVip 字段
                Paper paper = paperService.paperWithId(alongId);

                Boolean flag = tradeService.updateStatus(out_trade_no, 1);
                log.info("=======更新订单状态======", flag);

                UserBO userBO = userService.getUserWithUid(uid);
                Date now = new Date();

                if (type == 3) {  // 抽
                    // 给所在企业返佣20% 给师傅返佣30%
                    PaperHistory paperHistory = new PaperHistory();
                    paperHistory.setUid(uid);
                    paperHistory.setCid(userBO.getCid());
                    paperHistory.setPaperid(paper.getId());
                    paperHistory.setType(2);
                    paperHistory.setCid(userBO.getCid());
                    paperHistory.setCreated_at(now);
                    paperHistory.setUpdated_at(now);
                    Boolean flag1 = paperHistoryService.addPaperHistory(paperHistory);
                    log.info("=======插入抽取历史表======", flag1);

                } else if (type == 4) {  //放
                    //更新纸条的状态
                    paperService.updatePaperStatus(1, paper.getId());

                    long paperid = paper.getId();
                    long cid = paper.getCid();
                    PaperHistory paperHistory = new PaperHistory();
                    paperHistory.setUid(uid);
                    paperHistory.setCid(cid);
                    paperHistory.setPaperid(paperid);
                    paperHistory.setType(1);
                    paperHistory.setCreated_at(now);
                    paperHistory.setUpdated_at(now);
                    paperHistoryService.addPaperHistory(paperHistory);
                }

                double back30 = fen*0.3;
                double back20 = fen*0.2;
                /*
                 * 3、增加师傅收入30% 增加所属企业管理员20%（如果所属企业管理员是本人30% 20%舍弃;如果企业管理员是师傅，提成30%）
                 * -先判断是不是企业管理员
                 * -
                 * */

                int is_match = userBO.getIsmatch();
                SysCompanyUser sysCompanyUser = sysCompanyUserService.userInfoWithUid(uid);
                int invited_by_type = sysCompanyUser.getInvited_by_type();

                long invited_by = sysCompanyUser.getInvited_by();
                long cid = sysCompanyUser.getCid();
                // 获取cid对应的管理员用户
                UserBO invited_by_userBo = userService.getUserWithUid(invited_by);
//                log.info("============开始：豆变动历史============ {}", buyNum);
                if (type == 3) {// 抽
                    if (is_match != 1) {
                        double income = invited_by_userBo.getIncome()+back30;
                        double remain = invited_by_userBo.getRemaining()+back30;
                        userService.updateUserExtrIncome(income, invited_by);
                        userService.updateUserExtrRemain(remain, invited_by);

                        Fund fund = new Fund();
                        fund.setUid(invited_by);
                        fund.setCid(invited_by_userBo.getCid());
                        fund.setType(3);
                        fund.setFund(back30);
                        fund.setAlongid(trade.getId());
                        fund.setSerialno(serialNo);
                        fund.setDirection(1);
                        fund.setDesc("");
                        fund.setCreated_at(new Date());
                        fund.setUpdated_at(new Date());
                        fundService.addFund(fund);
                        if (invited_by_type == 0) { // 管理员邀请 管理员是师傅 返给cid的用户30%

                        } else if (invited_by_type == 1) {

                            SysUser sysUser = sysUserService.getUserById(cid);
                            UserBO userBO1 = userService.getUserWithUid(sysUser.getUid());
                            double income1 = userBO1.getIncome()+back20;
                            double remain1 = userBO1.getRemaining()+back20;

                            userService.updateUserExtrIncome(income1, sysUser.getUid());
                            userService.updateUserExtrRemain(remain1, sysUser.getUid());

                            Fund fund1 = new Fund();
                            fund1.setUid(sysUser.getUid());
                            fund1.setCid(userBO1.getCid());
                            fund1.setType(2);
                            fund1.setFund(back20);
                            fund1.setAlongid(trade.getId());
                            fund1.setSerialno(serialNo);
                            fund1.setDirection(1);
                            fund1.setDesc("");
                            fund1.setCreated_at(new Date());
                            fund1.setUpdated_at(new Date());
                            fundService.addFund(fund1);
                        }
                    }

                } else if (type == 4) {//放

                }

            } else {
                log.info("============支付信息回调：支付失败============");
                response.setStatus(500);
                map.put("code", "ERROR");
                map.put("message", "签名错误");
            }
            response.setHeader("Content-type", ContentType.JSON.toString());
            response.getOutputStream().write(JSONUtil.toJsonStr(map).getBytes(StandardCharsets.UTF_8));
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/wxPrepayGoodsNotify", method = {org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public void payNotify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = new HashMap<>(12);
        try {
            String timestamp = request.getHeader("Wechatpay-Timestamp");
            String nonce = request.getHeader("Wechatpay-Nonce");
            String serialNo = request.getHeader("Wechatpay-Serial");
            String signature = request.getHeader("Wechatpay-Signature");

            log.info("timestamp:{} nonce:{} serialNo:{} signature:{}", timestamp, nonce, serialNo, signature);
            String result = HttpKit.readData(request);
            log.info("支付通知密文 {}", result);

            // 需要通过证书序列号查找对应的证书，verifyNotify 中有验证证书的序列号
            String plainText = WxPayKit.verifyNotify(serialNo, result, signature, nonce, timestamp,
                    wxPayV3Bean.getApiKey3(), wxPayV3Bean.getPlatformCertPath());

            log.info("支付通知明文 {}", plainText);

            if (StrUtil.isNotEmpty(plainText)) {
                response.setStatus(200);
                map.put("code", "SUCCESS");
                map.put("message", "SUCCESS");
                JSONObject jsonObject = JSONUtil.parseObj(plainText);
                String out_trade_no = jsonObject.getStr("alongId");
                String openid = jsonObject.getStr("appid");
                log.info("out_trade_no {}", out_trade_no);

                Trade trade = tradeService.tradeWithTradeNo(out_trade_no);
                long alongId = trade.getAlong_id();
                long uid = trade.getUid();
                int type = trade.getType();
                double fen = trade.getFen();
                // 1、新增用户vip or 续费时长 2、更新 ismatch 和 isVip 字段
                Goods goods = goodsService.getGoodsWithGoodId(alongId);
                int buy_time = Integer.valueOf(goods.getBuy_time()); // 几个月

                UserBO userBO = userService.getUserWithUid(uid);
                Date now = new Date();
                if (type == 1) {  // VIP
                    Date vip_expire = userBO.getVip_expire();

                    boolean afterNow = DateUtils.compareDate(DateUtils.formatString(vip_expire, DateUtils.TIME_DATE));
                    if (afterNow) {
                        vip_expire = DateUtils.plusMonths(vip_expire, buy_time);
                    } else {
                        vip_expire = DateUtils.plusMonths(now, buy_time);
                    }
                    userService.updateUserExtrVIP(1, vip_expire, uid);
                } else if (type == 2) {  //续费时长
                    Date match_expire = userBO.getMatch_expire();

                    boolean afterNow = DateUtils.compareDate(DateUtils.formatString(match_expire, DateUtils.TIME_DATE));
                    if (afterNow) {
                        match_expire = DateUtils.plusMonths(match_expire, buy_time);
                    } else {
                        match_expire = DateUtils.plusMonths(now, buy_time);
                    }
                    userService.updateUserExtrMatch(1, match_expire, uid);
                }

                //本人财务表变更
                Fund fund0 = new Fund();
                fund0.setUid(uid);
                fund0.setCid(userBO.getCid());
                fund0.setType(0);
                fund0.setFund(fen);
                fund0.setAlongid(trade.getId());
                fund0.setSerialno(serialNo);
                fund0.setDirection(2);
                fund0.setDesc("购买商品");
                fund0.setCreated_at(new Date());
                fund0.setUpdated_at(new Date());
                fundService.addFund(fund0);

                double back30 = fen*0.3;
                double back20 = fen*0.2;
                /*
                * 3、增加师傅收入30% 增加所属企业管理员20%（如果所属企业管理员是本人30% 20%舍弃;如果企业管理员是师傅，提成30%）
                * -先判断是不是企业管理员
                * -
                * */

                int is_match = userBO.getIsmatch();
                SysCompanyUser sysCompanyUser = sysCompanyUserService.userInfoWithUid(uid);
                int invited_by_type = sysCompanyUser.getInvited_by_type();

                long invited_by = sysCompanyUser.getInvited_by();
                long cid = sysCompanyUser.getCid();
                // 获取cid对应的管理员用户
                UserBO invited_by_userBo = userService.getUserWithUid(invited_by);
//                log.info("============开始：豆变动历史============ {}", buyNum);
                if (type == 1) {// VIP
                    if (is_match != 1) {
                        double income = invited_by_userBo.getIncome()+back30;
                        double remain = invited_by_userBo.getRemaining()+back30;
                        userService.updateUserExtrIncome(income, invited_by);
                        userService.updateUserExtrRemain(remain, invited_by);

                        Fund fund = new Fund();
                        fund.setUid(invited_by);
                        fund.setCid(invited_by_userBo.getCid());
                        fund.setType(5);
                        fund.setFund(back30);
                        fund.setAlongid(trade.getId());
                        fund.setSerialno(serialNo);
                        fund.setDirection(1);
                        fund.setDesc("");
                        fund.setCreated_at(new Date());
                        fund.setUpdated_at(new Date());
                        fundService.addFund(fund);
                        if (invited_by_type == 0) { // 管理员邀请 管理员是师傅 返给cid的用户30%

                        } else if (invited_by_type == 1) {

                            SysUser sysUser = sysUserService.getUserById(cid);
                            UserBO userBO1 = userService.getUserWithUid(sysUser.getUid());
                            double income1 = userBO1.getIncome()+back20;
                            double remain1 = userBO1.getRemaining()+back20;

                            userService.updateUserExtrIncome(income1, sysUser.getUid());
                            userService.updateUserExtrRemain(remain1, sysUser.getUid());

                            Fund fund1 = new Fund();
                            fund1.setUid(sysUser.getUid());
                            fund1.setCid(userBO1.getCid());
                            fund1.setType(6);
                            fund1.setFund(back20);
                            fund1.setAlongid(trade.getId());
                            fund1.setSerialno(serialNo);
                            fund1.setDirection(1);
                            fund1.setDesc("");
                            fund1.setCreated_at(new Date());
                            fund1.setUpdated_at(new Date());
                            fundService.addFund(fund1);
                        }
                    }

                } else if (type == 2) {
                    //续费成为月老 1、创建后台管理账号  2、更新此用户的cid 3、月老消费记录
                    //#TODO请联系管理员开通月老账号
                    SysUser sysUser = new SysUser();
                    sysUser.setUid(uid);
                    sysUser.setAttcode(userBO.getAttCode());
                    sysUser.setUpdated_at(now);
                    sysUser.setCreated_at(now);
                    sysUser.setUpdated_by(1);
                    sysUser.setCreated_by(1);
                    sysUser.setAvatar(userBO.getHeadimgurl());
                    sysUser.setSalt(userBO.getAttCode());
                    sysUser.setName(userBO.getNickname());
                    sysUser.setUsername(userBO.getAttCode());
                    sysUser.setPhone(userBO.getAttCode());
                    sysUser.setToken(userBO.getAttCode());
                    sysUser.setPassword("Aa123456Bb");
                    SysUser sysUser1 = sysUserService.autoAddSysUser(sysUser);
                    long cid1 = sysUser1.getId();

                    // 更新当前C用户的cid
                    userService.updateUserExtrCid(cid1, uid);
                }

            } else {
                log.info("============支付信息回调：支付失败============");
                response.setStatus(500);
                map.put("code", "ERROR");
                map.put("message", "签名错误");
            }
            response.setHeader("Content-type", ContentType.JSON.toString());
            response.getOutputStream().write(JSONUtil.toJsonStr(map).getBytes(StandardCharsets.UTF_8));
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
