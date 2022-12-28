package com.mahroz.javacrud.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@Configuration
public class ScopeConfiguration {
}



@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
class RequestContext{

    private final String uuId = UUID.randomUUID().toString();


    public String getUuId(){
        return this.uuId;
    }
}

@RestController
class RequestController{

    private final RequestContext requestContext;

    @Lazy
    public RequestController(RequestContext requestContext){
        this.requestContext = requestContext;
    }


    @GetMapping("/getId")
    public String getUUID(){
        return this.requestContext.getUuId();
    }
}
