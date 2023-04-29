package com.mahroz.gateway.service.filter;


import com.mahroz.gateway.service.client.AuthClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

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
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    Mono<String> res = authClient.validateToken(authHeader);
                    res.subscribe(r->{
                        if(r.equalsIgnoreCase("Token is valid")){
                            log.info("TOKEN IS VALID");
                        }else {
                            throw new RuntimeException("un authorized access to application");
                        }
                    });
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
