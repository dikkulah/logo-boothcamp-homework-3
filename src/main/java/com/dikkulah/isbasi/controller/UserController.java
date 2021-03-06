package com.dikkulah.isbasi.controller;

import com.dikkulah.isbasi.model.Commercial;
import com.dikkulah.isbasi.model.User;
import com.dikkulah.isbasi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping(value = "/{email}/customers")
    public List<Commercial> getCommercialsByUserEmail(@PathVariable String email) {
        return userService.getCommercialsByUserEmail(email);
    }

}
