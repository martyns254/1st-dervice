package com.example.my_first_springboot_app.controller;

import com.example.my_first_springboot_app.model.User;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private List<User> users = new ArrayList<>();

    public UserController() {
        users.add(new User("Brio", "brio@gmail.com", 25));
        users.add(new User("Rio", "Rio@gmail.com", 30));
        users.add(new User ( "Chris", "chris@gmail.com", 38));
    }

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        if (id >= 0 && id < users.size()){
            return users.get(id);

        }
        return null;
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
        users.add(user);
        return user;
    }
}