package com.cusbservice.apigw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OAuth2Service {

    private final WebClient webClient;

    @Autowired
    public OAuth2Service(WebClient.Builder webClientBuilder) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction();
        this.webClient = webClientBuilder.apply(oauth2.oauth2Configuration()).build();
    }

    public Mono<String> callProtectedService() {
        return webClient.get()
                .uri("http://localhost:8081/protected-endpoint")
                .retrieve()
                .bodyToMono(String.class);
    }
}
