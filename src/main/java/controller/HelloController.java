package com.example.my_first_springboot_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("/")
    public String home() {
        return " Welcome to my first Spring Boot application!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World from Spring Boot! ";
    }
    @GetMapping("/About")
    public String About() {
        return "This is my springboot!";
    }
    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return "Hello " + "martin," + "Welcome" + "!";

    }
    @GetMapping("/welcome")
    public String welcome(@RequestParam(defaultValue = "Friend") String name,
                          @RequestParam(defaultValue = "1") int visits) {
        return "Welcome back, " + name + "! This is visit #" + visits + "Yy";
    }


}