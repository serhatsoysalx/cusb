package com.cusbservice.apigw.security;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class GlobalAuthFilter implements GlobalFilter, Ordered {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        logRequestDetails(request);

        if (isAuthRequired(request)) {
            List<String> authHeaders = request.getHeaders().get(AUTHORIZATION_HEADER);

            if (authHeaders == null || authHeaders.isEmpty() || !authHeaders.get(0).startsWith(BEARER_PREFIX)) {
                return handleUnauthorized(exchange);
            }

            String token = authHeaders.get(0).substring(BEARER_PREFIX.length());

            if (!validateToken(token)) {
                return handleUnauthorized(exchange);
            }
        }

        return chain.filter(exchange);
    }

    private void logRequestDetails(ServerHttpRequest request) {
        System.out.println("Incoming Request: " + request.getMethod() + " " + request.getURI());
        request.getHeaders().forEach((key, value) -> System.out.println(key + ": " + value));
    }

    private boolean isAuthRequired(ServerHttpRequest request) {
        return !request.getURI().getPath().contains("/api/auth/");
    }

    private boolean validateToken(String token) {
        return token.length() > 10;
    }

    private Mono<Void> handleUnauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
