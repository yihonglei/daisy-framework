package com.mhjy.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mhjy.constant.CommonConstant;
import com.mhjy.pojo.Bo.HeaderBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author yangnan
 * header信息处理
 */
public class HeaderInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static String MARK = "redMark";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Map<String, String> headerMap = Maps.newHashMap();
        Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                headerMap.put(key, request.getHeader(key));
            }
        }
        logger.info("请求接口:{} requestHeader:{}", request.getRequestURI(), JSON.toJSONString(headerMap));
        String token = headerMap.get("token");
        HeaderBO headerBO = new HeaderBO();
        headerBO.setToken(token);
//        headerBO.setChannelsNum(headerMap.get("car-ps"));
//        headerBO.setImei(headerMap.get("car-mi"));
//        headerBO.setPlatform(headerMap.get("car-pf"));
//        headerBO.setMobelVersion(headerMap.get("car-mv"));
//        headerBO.setSysVersion(headerMap.get("car-sv"));
//        headerBO.setCoordinate(headerMap.get("coordinate"));
//        headerBO.setVersion(headerMap.get("car-vs"));
//        headerBO.setCarHqVersion(headerMap.get("carhqversion"));
//        headerBO.setSecretary(headerMap.get("secretary"));
//        headerBO.setSecretaryPhone(headerMap.get("secretary_phone"));
//        headerBO.setUserType(StringUtils.isNumeric(headerMap.get("user-type")) ? Integer.parseInt(headerMap.get("user-type")) : UserTypeEnum.ENTERPRISE.getType());
//        if(MARK.equals(headerMap.get("mark"))){
//            headerBO.setMark(headerMap.get("mark"));
//            headerBO.setUserId(Integer.valueOf(headerMap.get("userid")));
//            headerBO.setPhone(headerMap.get("phone"));
//            headerBO.setStatus(Integer.valueOf(headerMap.get("status")));
//            headerBO.setGender(Integer.valueOf(headerMap.get("gender")));
//            headerBO.setIdNumber(headerMap.get("idnumber"));
//        }
        request.setAttribute(CommonConstant.HEADER, headerBO);
        return true;
    }
}
