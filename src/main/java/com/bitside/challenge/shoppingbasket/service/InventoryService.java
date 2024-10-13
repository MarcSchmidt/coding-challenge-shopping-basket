package com.bitside.challenge.shoppingbasket.service;

import com.bitside.challenge.shoppingbasket.model.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {
    private final Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getName(), product);
    }

    public Product getProduct(String productName) {
        return products.get(productName);
    }
}
