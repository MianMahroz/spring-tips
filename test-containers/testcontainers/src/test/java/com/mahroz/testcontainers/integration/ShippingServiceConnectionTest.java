package com.mahroz.testcontainers.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@Slf4j
public class ShippingServiceConnectionTest {

    RestTemplate restTemplate = new RestTemplate();

    @Container
    static GenericContainer container = new GenericContainer(DockerImageName.parse("mianmahroz/shipping-service:latest"))
            .withExposedPorts(8082);

    @Test
    @DisplayName("withDynamicUrlShouldPass")
    public void when_service_is_alive() {

        String url = "http://" + container.getHost() + ":" + container.getFirstMappedPort()+"/isAlive";
        log.info("URL: {}", url);
        ResponseEntity<String> response
                = restTemplate.getForEntity(url , String.class);
        log.info("Response: {}", response.getBody());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK, () -> "This is not okay.");
        Assertions.assertEquals(response.getBody(), "YES", () -> "This is not okay.");

    }


    @Test
    public void when_service_in_good_health(){
        String url = "http://" + container.getHost() + ":" + container.getFirstMappedPort()+"/actuator/health";
        log.info("URL: {}", url);
        ResponseEntity<String> response
                = restTemplate.getForEntity(url , String.class);
        log.info("Response: {}", response.getBody());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK, () -> "This is not okay.");
    }

}
