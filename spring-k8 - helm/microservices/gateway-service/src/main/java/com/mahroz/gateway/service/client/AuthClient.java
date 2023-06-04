package com.mahroz.gateway.service.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.client.WebClient.builder;

@Component
public class AuthClient {

    @Value("${AUTH_CLIENT_URL}")
    private String AUTH_CLIENT_URL;
    public  Mono<String> validateToken(String token){

        Mono<String> response = builder()
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultHeaders(httpHeaders -> httpHeaders.setBearerAuth(token))
                    .defaultCookie("JSESSIONID",token)
                    .baseUrl(AUTH_CLIENT_URL)
                    .build()
                    .get()
                    .uri(u->u.path("/validate").queryParam("token",token).build())
                    .retrieve()
                    .bodyToMono(String.class);

            return  response;
    }

}

