package com.bitside.challenge.shoppingbasket.service;

import com.bitside.challenge.shoppingbasket.model.BasketItem;
import com.bitside.challenge.shoppingbasket.model.Product;
import com.bitside.challenge.shoppingbasket.model.User;
import com.bitside.challenge.shoppingbasket.model.promotion.Promotion;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BasketService {
    private final List<Promotion> promotions = new ArrayList<>();
    private final InventoryService inventoryService;

    public void scan(User user, String productName) {
        Product product = inventoryService.getProduct(productName);
        if (product == null) {
            throw new IllegalArgumentException("Product " + productName + " does not exist.");
        }
        user.getBasket().scan(new BasketItem(product, 1));
    }

    public void addPromotion(Promotion promotion) {
        promotions.add(promotion);
    }

    public double getTotal(User user) {
        Map<String, Double> discountedPrices = applyPromotions(user);
        return user.getBasket().getItems().values().stream()
                .mapToDouble(item -> {
                    double price = discountedPrices.getOrDefault(
                            item.getProduct().getName(),
                            item.getProduct().getPrice()
                    );
                    return price * item.getQuantity();
                })
                .sum();
    }

    private Map<String, Double> applyPromotions(User user) {
        Map<String, Double> discountedPrices = new HashMap<>();
        Map<String, Integer> itemCounts = new HashMap<>();
        user.getBasket().getItems().forEach((productName, item) -> itemCounts.put(productName, item.getQuantity()));

        for (Promotion promotion : promotions) {
            promotion.apply(itemCounts, inventoryService, discountedPrices);
        }
        return discountedPrices;
    }

    public void clearBasket(User user) {
        user.getBasket().clear();
    }
}
