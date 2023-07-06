package com.mahroz.gateway.service.filter;


import com.mahroz.gateway.service.client.AuthClient;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@Slf4j
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private RouteValidator validator;
    private AuthClient authClient;


    public AuthFilter(RouteValidator validator, AuthClient authClient) {
        super(Config.class);

        this.validator = validator;
        this.authClient = authClient;
    }


    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {

                 if (!exchange.getRequest().getCookies().containsKey("JSESSIONID")) {
                    throw new RuntimeException("missing JSESSIONID COOKIE");
                 }


                try {
                    String authToken = exchange.getRequest().getCookies().get("JSESSIONID").get(0).getValue();

                    Mono<String> res = authClient.validateToken(authToken);

                        if(Objects.requireNonNull(res.block()).equalsIgnoreCase("valid")){
                            log.info("TOKEN IS VALID");
                        }else {
                            throw new RuntimeException("un authorized access to application");
                        }

                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("un authorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
