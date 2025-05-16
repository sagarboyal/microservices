package com.main.limitservice.controller;

import com.main.limitservice.configuration.LimitConfig;
import com.main.limitservice.model.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/limits")
public class LimitController {
    @Autowired
    private LimitConfig limitConfig;

    @GetMapping
    public Limit retrieveLimit(){
        return new Limit(limitConfig.getMinimum(), limitConfig.getMaximum());
    }
}
