package com.spark.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SparkController {

    final String BASE_URL = "/api/spark";

    @GetMapping(value = BASE_URL + "/hello", produces = "application/json")
    public String hello() {
        return "Ola!!";
    }
}
