package com.example.my_first_springboot_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/web")
    public String index() {
        return "index"; // This returns the index.html template
    }
}