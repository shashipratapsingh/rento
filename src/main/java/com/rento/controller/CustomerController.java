package com.rento.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome To Dashboard";
    }

    @GetMapping("/profile")
    public String profile() {
        return "Customer Profile";
    }
}