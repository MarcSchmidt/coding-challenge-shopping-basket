package com.bitside.challenge.shoppingbasket.model.promotion;

import com.bitside.challenge.shoppingbasket.service.InventoryService;

import java.util.Map;

public interface Promotion {
    void apply(Map<String, Integer> itemCounts, InventoryService inventoryService, Map<String, Double> discountedPrices);
}
