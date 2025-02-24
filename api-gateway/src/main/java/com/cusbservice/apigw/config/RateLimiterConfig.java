package com.cusbservice.apigw.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class RateLimiterConfig {

    // JWT token'dan userId alma (Authorization: Bearer <token>)
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                // Burada JWT parse edilerek userId alınır (dummy örnek)
                String userId = parseJwtAndGetUserId(token);
                return Mono.just(userId != null ? userId : "anonymous");
            }
            return Mono.just("anonymous");
        };
    }

    // JWT parse eden basit metod (gerçek uygulamada JWT kütüphanesi kullanılır)
    private String parseJwtAndGetUserId(String token) {
        // Dummy örnek: Gerçek uygulamada JWT decode edilir
        return "user123";
    }
}
