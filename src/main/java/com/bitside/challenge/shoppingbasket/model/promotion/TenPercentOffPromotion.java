package com.bitside.challenge.shoppingbasket.model.promotion;

import com.bitside.challenge.shoppingbasket.service.InventoryService;

import java.util.Map;

public class TenPercentOffPromotion implements Promotion {
    private final String productName;

    public TenPercentOffPromotion(String productName) {
        this.productName = productName;
    }

    @Override
    public void apply(Map<String, Integer> itemCounts, InventoryService inventoryService, Map<String, Double> discountedPrices) {
        if (itemCounts.containsKey(productName)) {
            double pricePerUnit = inventoryService.getProduct(productName).getPrice();
            discountedPrices.put(productName, pricePerUnit * 0.90);
        }
    }
}