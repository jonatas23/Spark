package com.spark.controller;

import com.spark.service.GeoSparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoSparkController {

    final String BASE_URL = "/api/spark";

    @Autowired
    GeoSparkService sparkService;

    @GetMapping(value = BASE_URL + "/intersects", produces = "application/json")
    public void intersects() {
        sparkService.stIntersects();
    }

    @GetMapping(value = BASE_URL + "/contains", produces = "application/json")
    public void stContains() {
        sparkService.stContains();
    }
}
