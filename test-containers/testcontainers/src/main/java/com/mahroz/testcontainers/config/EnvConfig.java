package com.mahroz.testcontainers.config;

import org.springframework.beans.factory.annotation.Value;

public class EnvConfig {

    @Value("${topic.name}")
    protected String shippingTopic;
}
