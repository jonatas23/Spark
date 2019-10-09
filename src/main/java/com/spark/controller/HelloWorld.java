package com.spark.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/spark")
public class HelloWorld {

    @GetMapping(value = "/", produces = "application/json")
    public String helloWorld() {
        return "Hello World!!";
    }
}
