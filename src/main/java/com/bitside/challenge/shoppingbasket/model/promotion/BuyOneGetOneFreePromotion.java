package com.bitside.challenge.shoppingbasket.model.promotion;

import com.bitside.challenge.shoppingbasket.service.InventoryService;

import java.util.Map;

public class BuyOneGetOneFreePromotion implements Promotion {
    private final String productName;

    public BuyOneGetOneFreePromotion(String productName) {
        this.productName = productName;
    }

    @Override
    public void apply(Map<String, Integer> itemCounts, InventoryService inventoryService, Map<String, Double> discountedPrices) {
        if (itemCounts.containsKey(productName)) {
            int quantity = itemCounts.get(productName);
            int payableQuantity = (quantity / 2) + (quantity % 2);
            double pricePerUnit = inventoryService.getProduct(productName).getPrice();
            discountedPrices.put(productName, (payableQuantity * pricePerUnit) / quantity);
        }
    }
}

