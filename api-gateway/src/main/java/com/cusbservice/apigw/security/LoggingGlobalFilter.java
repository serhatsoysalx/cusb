package com.cusbservice.apigw.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getURI().getPath();
        String method = exchange.getRequest().getMethodValue();
        String clientIP = exchange.getRequest().getHeaders().getFirst("X-Forwarded-For");

        log.info("➡️ Incoming Request | method={} | path={} | clientIP={}", method, requestPath, clientIP);

        long startTime = System.currentTimeMillis();

        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    long duration = System.currentTimeMillis() - startTime;
                    int statusCode = exchange.getResponse().getStatusCode() != null ?
                            exchange.getResponse().getStatusCode().value() : 500;

                    log.info("⬅️ Response | method={} | path={} | status={} | duration={}ms", method, requestPath, statusCode, duration);
                })
        );
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
