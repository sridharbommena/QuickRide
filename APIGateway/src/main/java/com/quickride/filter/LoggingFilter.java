package com.quickride.filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        log.info("Incoming Request: {} {}",
                request.getMethod(),
                request.getURI().getPath());

        log.info("Target URI: {}",
                request.getURI());

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("Response Status: {}",
                    exchange.getResponse().getStatusCode());
        }));

    }

    @Override
    public int getOrder() {
        return -1; //-1 means runs first before other filters
    }
}
