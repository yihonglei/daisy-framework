package com.jpeony.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.jpeony.gateway.constant.TraceConstant.TRACE_KEY;

/**
 * 【监控】记录请求响应数据，api 耗时分析
 *
 * @author yihonglei
 */
@Slf4j
@Component
public class ApiMonitorFilter implements GlobalFilter, Ordered {

    private static final String START_TIME = "startTime";

    private static final String X_REAL_IP = "X-Real-IP";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (StringUtils.isBlank(exchange.getRequest().getHeaders().getFirst(TRACE_KEY))) {
            String traceId = UUID.randomUUID().toString().replace("-", "");
            exchange.getRequest().mutate().header(TRACE_KEY, traceId).build();
        }
        if (log.isDebugEnabled()) {
            String info = String.format("Method:{%s} Host:{%s} Header:{%s} Path:{%s} Query:{%s}",
                    Objects.requireNonNull(exchange.getRequest().getMethod()).name(), exchange.getRequest().getURI().getHost(),
                    exchange.getRequest().getHeaders(),
                    exchange.getRequest().getURI().getPath(), exchange.getRequest().getQueryParams());
            log.debug(info);
        }
        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(START_TIME);
            if (startTime != null) {
                if (log.isDebugEnabled()) {
                    Long executeTime = (System.currentTimeMillis() - startTime);
                    List<String> ips = exchange.getRequest().getHeaders().get(X_REAL_IP);
                    String ip = ips != null ? ips.get(0) : null;
                    String api = exchange.getRequest().getURI().getRawPath();
                    int code = exchange.getResponse().getStatusCode() != null
                            ? exchange.getResponse().getStatusCode().value() : 500;
                    log.debug("来自IP地址：{}的请求接口：{}，响应状态码：{}，请求耗时：{}ms", ip, api, code, executeTime);
                }
            }
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
