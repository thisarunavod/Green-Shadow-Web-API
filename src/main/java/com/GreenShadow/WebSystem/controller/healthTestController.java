package com.GreenShadow.WebSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/healthCheck")
public class healthTestController {

    @GetMapping
    public String healthCheck(){
        return "GreenShadow web is running perfect";
    }
}
