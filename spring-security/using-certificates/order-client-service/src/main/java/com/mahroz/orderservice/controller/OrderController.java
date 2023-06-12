package com.mahroz.orderservice.controller;

import com.mahroz.orderservice.config.SSLConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
@Slf4j
public class OrderController {


    /**
     * This property bean is created in {@link SSLConfig#webClient()} ()}
     **/
    @Autowired
    WebClient webClient;


    /**
     * This controller method invokes server using the wires bean {@link this#webClient}
     * @return secure response received from server
     */

    @GetMapping
    public String getDataFromServer() {
        Mono<String> dateFromServer = webClient.get()
                .uri("https://localhost:8082/server")
                .retrieve().bodyToMono(String.class);
        return dateFromServer.block();
    }

}
