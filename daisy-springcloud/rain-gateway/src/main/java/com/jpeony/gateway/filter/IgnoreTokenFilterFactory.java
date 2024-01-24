package com.jpeony.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 不需要校验token的请求
 *
 * @author yihonglei
 */
@Component
public class IgnoreTokenFilterFactory extends AbstractGatewayFilterFactory<IgnoreTokenFilterFactory.Config> {

    public IgnoreTokenFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new IgnoreGlobalFilter(config);
    }

    public static class Config {

    }

    @Override
    public String name() {
        return "IgnoreTokenFilter";
    }

    public static class IgnoreGlobalFilter implements GatewayFilter, Ordered {

        private Config config;

        IgnoreGlobalFilter(Config config) {
            this.config = config;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            exchange.getAttributes().put(AuthorizeFilter.ATTRIBUTE_IGNORE_GLOBAL_FILTER, true);
            return chain.filter(exchange);
        }

        @Override
        public int getOrder() {
            return Ordered.HIGHEST_PRECEDENCE;
        }
    }
}
