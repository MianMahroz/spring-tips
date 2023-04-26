package com.mahroz.demo.springk8.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @GetMapping("/list")
    public ResponseEntity getProducts(){
        return ResponseEntity.ok(Arrays.asList("IPHONE","SAMSUNG","VIVO"));
    }

}
