package com.bitside.challenge.shoppingbasket.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Basket {
    private final Map<String, BasketItem> items = new HashMap<>();

    public void scan(BasketItem basketItem) {
        items.merge(basketItem.getProduct().getName(), basketItem, (existing, newItem) -> {
            existing.setQuantity(existing.getQuantity() + newItem.getQuantity());
            return existing;
        });
    }

    public void clear() {
        items.clear();
    }
}
