package com.GreenShadow.WebSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/equipment")
public class EquipmentController {

    @GetMapping
    public String healthCheck(){ return "Equipment Controller is running Successfully";}
}
