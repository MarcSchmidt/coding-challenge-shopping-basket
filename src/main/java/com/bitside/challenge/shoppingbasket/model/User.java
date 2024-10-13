package com.bitside.challenge.shoppingbasket.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String username;
    private Basket basket = new Basket();

    public User(String username) {
        this.username = username;
    }
}