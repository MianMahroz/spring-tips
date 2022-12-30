package com.mahroz.testcontainers.integration;

import com.mahroz.testcontainers.dto.OrderDto;
import com.mahroz.testcontainers.repo.OrderRepository;

import static io.restassured.RestAssured.given;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderControllerTest  extends  AbstractIntegrationTest{


    @Test
    public void test_saveOrder_success(){
        given(requestSpecification)
                .body(new OrderDto(90,4))
                .when().post("/saveOrder")
                .then().assertThat().statusCode(200);

    }

    @Test
    public void test_getOrders_success(){
        given(requestSpecification)
                .body(new OrderDto(90,4))
                .when().post("/saveOrder")
                .then().assertThat().statusCode(200);

            given(requestSpecification)
                    .when().get("/orders")
                    .then().assertThat().statusCode(200)
                    .and().contentType(ContentType.JSON)
                    .body("size()",equalTo(1))
                    .body("get(0).productId",equalTo(90));

    }


}
