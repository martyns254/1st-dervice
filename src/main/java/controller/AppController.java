package com.example.my_first_springboot_app.controller;

import com.example.my_first_springboot_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "<h1>Hello World!</h1><p>Welcome to my Spring Boot application!</p>" +
                "<p><a href='/'>← Back to Home</a></p>";
    }

    @GetMapping("/greet/{name}")
    @ResponseBody
    public String greet(@PathVariable String name) {
        return String.format("<h1>Hello, %s!</h1><p>Nice to meet you!</p>" +
                "<p><a href='/'>← Back to Home</a></p>", name);
    }

    @GetMapping("/welcome")
    @ResponseBody
    public String welcome(@RequestParam String name, @RequestParam(defaultValue = "1") int visits) {
        String message = visits == 1 ?
                String.format("Welcome %s! This is your first visit.", name) :
                String.format("Welcome back %s! You've visited %d times.", name, visits);

        return String.format("<h1>%s</h1><p><a href='/'>← Back to Home</a></p>", message);
    }

    @GetMapping("/about")
    @ResponseBody
    public String about() {
        long userCount = userService.getUserCount();
        long activeUsers = userService.getActiveUserCount();
        double avgAge = userService.getAverageAge();

        return String.format(
                "<div style='font-family: Arial; max-width: 600px; margin: 40px auto; padding: 30px;'>" +
                        "<h1>About My Spring Boot App</h1>" +
                        "<p>This is a comprehensive user management application built with Spring Boot.</p>" +
                        "<h2>Current Statistics:</h2>" +
                        "<ul>" +
                        "<li>Total Users: %d</li>" +
                        "<li>Active Users: %d</li>" +
                        "<li>Average Age: %.1f years</li>" +
                        "</ul>" +
                        "<h2>Features:</h2>" +
                        "<ul>" +
                        "<li>User CRUD operations</li>" +
                        "<li>User search and filtering</li>" +
                        "<li>Data validation</li>" +
                        "<li>RESTful API</li>" +
                        "<li>Exception handling</li>" +
                        "</ul>" +
                        "<p><a href='/'>← Back to Home</a></p>" +
                        "</div>",
                userCount, activeUsers, avgAge
        );
    }
}