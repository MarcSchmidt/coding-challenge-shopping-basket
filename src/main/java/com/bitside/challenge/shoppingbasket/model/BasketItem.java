package com.bitside.challenge.shoppingbasket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasketItem {
    private Product product;
    private int quantity;
}
