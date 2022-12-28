package com.mahroz.javacrud.configuration;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Configuration
public class BasicConfiguration {

    @Bean
    public ApplicationListener<ApplicationReadyEvent> basicApplicationListener(CustomerRepository customerRepository){
        return  event -> customerRepository.saveAll(
                Stream.of("A","B","C")
                .map(s-> new Customer(null,s)).toList())
                .forEach(System.out::println);

    }



}


record Customer(@Id Integer id, String name){}


interface  CustomerRepository extends CrudRepository<Customer,Integer> {

}