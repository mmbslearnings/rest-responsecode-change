package com.responsecode.change.controller;

import com.responsecode.change.controller.service.UserService;
import com.responsecode.change.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public Mono<ResponseEntity<User>> getUsers(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @GetMapping("/users/all")
    public Mono<List<User>> getAllUsers() {
        return userService.getUsers();
    }

    @PostMapping("/users/add")
    public Mono<ResponseEntity<String>> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
