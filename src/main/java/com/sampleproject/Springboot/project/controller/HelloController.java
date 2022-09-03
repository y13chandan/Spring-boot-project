package com.sampleproject.Springboot.project.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/")
    public String helloWorld() {
        return "Welcome to my first Spring boot aaa project";
    }
}
