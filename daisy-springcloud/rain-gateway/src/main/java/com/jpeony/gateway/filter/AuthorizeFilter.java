package com.jpeony.gateway.filter;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.jpeony.gateway.constant.ErrorCodeEnum;
import com.jpeony.gateway.exception.BizException;
import com.jpeony.gateway.pojo.User;
import com.jpeony.gateway.util.DESUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 【安全】全局过滤器校验请求头中的token
 *
 * @author yihonglei
 */
@Slf4j
@Component
@AllArgsConstructor
public class AuthorizeFilter implements GlobalFilter, Ordered {
    private static final String AUTHORIZE_TOKEN = "token";

    public final static String ATTRIBUTE_IGNORE_GLOBAL_FILTER = "@IgnoreTokenFilter";

    // TODO redis 存用户登录信息
//    private final IRedisService<String, User> redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 跳过token校验
        if (exchange.getAttribute(ATTRIBUTE_IGNORE_GLOBAL_FILTER) != null) {
            return chain.filter(exchange);
        }
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
//        String token = headers.getFirst(AUTHORIZE_TOKEN);
//        if (token == null) {
//            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
//        }
//        if (StringUtils.isBlank(token)) {
//            throw new BizException(ErrorCodeEnum.TOKEN_PARSE_ERROR);
//        }
//        String key = toString(token);
//        if (StringUtils.isBlank(key)) {
//            throw new BizException(ErrorCodeEnum.TOKEN_PARSE_ERROR);
//        }
//        String phone = key.substring(1);
        // TODO 从 redis 获取用户登录信息
//        User user = redisService.doGetObject(key);
        User user = new User();
        user.setUserId(1);
        if (user != null) {
            return chain.filter(exchange);
        }
        throw new BizException(ErrorCodeEnum.TOKEN_PARSE_ERROR);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    public static String toString(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        String tokenTemp = new String(Base64Utils.decode(DESUtils.dereplace(token).getBytes(StandardCharsets.UTF_8)));
        if (StringUtils.isEmpty(tokenTemp)) {
            return tokenTemp;
        } else {
            tokenTemp = tokenTemp.split("\\|")[0];
            return DESUtils.dereplace(tokenTemp);
        }
    }
}
