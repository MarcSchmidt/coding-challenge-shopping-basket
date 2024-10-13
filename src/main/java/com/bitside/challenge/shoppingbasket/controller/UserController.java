package com.bitside.challenge.shoppingbasket.controller;

import com.bitside.challenge.shoppingbasket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/{username}")
    public ResponseEntity<String> createUser(@PathVariable String username) {
        try {
            userService.createUser(username);
            return ResponseEntity.ok("User created with username: " + username);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
