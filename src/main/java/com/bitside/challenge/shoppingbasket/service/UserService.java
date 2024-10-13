package com.bitside.challenge.shoppingbasket.service;

import com.bitside.challenge.shoppingbasket.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public User createUser(String username) {
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("User already exists with username: " + username);
        }
        User user = new User(username);
        users.put(username, user);
        return user;
    }

    public User getUser(String username) {
        return users.get(username);
    }
}
