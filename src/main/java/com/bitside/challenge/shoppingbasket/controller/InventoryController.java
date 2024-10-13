package com.bitside.challenge.shoppingbasket.controller;

import com.bitside.challenge.shoppingbasket.model.Product;
import com.bitside.challenge.shoppingbasket.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/add/{productName}")
    public String scanItem(@PathVariable String productName, @RequestBody double price) {
        inventoryService.addProduct(new Product(productName, price));
        return "Product " + productName + " with price " + price + "added to inventory.";
    }
}
