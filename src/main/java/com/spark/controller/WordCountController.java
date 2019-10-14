package com.spark.controller;

import com.spark.service.WordCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class WordCountController {

	@Autowired
	WordCountService service;

	@RequestMapping(method = RequestMethod.POST, path = "/countText")
	public Long countText(@RequestParam(required = false) String name) {
		return service.countName(name);
	}

    @RequestMapping(method = RequestMethod.POST, path = "/contains")
    public Object contains(@RequestParam(required = false) String name) {
        return service.contains(name);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/mapBiblia")
	public Object mapReduceBiblia() {
		return service.mapReduce();
	}

}
