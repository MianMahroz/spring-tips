package com.mahroz.gateway.service;

import com.mahroz.gateway.service.client.AuthClient;
import com.mahroz.gateway.service.filter.AuthFilter;
import com.mahroz.gateway.service.filter.RouteValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients
//@CrossOrigin(origins = "http://localhost:4200")
public class GatewayServiceApplication {


	private RouteValidator validator;
	private AuthClient authClient;

	public GatewayServiceApplication(RouteValidator validator, AuthClient authClient) {
		this.validator = validator;
		this.authClient = authClient;
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/delivery/**")
						.filters(f->f.filter(new AuthFilter(validator,authClient).apply(new AuthFilter.Config())))
						.uri("http://localhost:8083"))
				.route(o -> o
						.path("/order/**")
						.filters(f->f.filter(new AuthFilter(validator,authClient).apply(new AuthFilter.Config())))
						.uri("http://localhost:8082"))
				.route(o -> o
						.path("/auth/**")
						.filters(f->f.filter(new AuthFilter(validator,authClient).apply(new AuthFilter.Config())))
						.uri("http://localhost:8084"))

				.build();
	}


}
