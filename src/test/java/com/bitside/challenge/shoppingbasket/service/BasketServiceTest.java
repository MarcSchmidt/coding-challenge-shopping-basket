package com.bitside.challenge.shoppingbasket.service;

import com.bitside.challenge.shoppingbasket.model.Product;
import com.bitside.challenge.shoppingbasket.model.User;
import com.bitside.challenge.shoppingbasket.model.promotion.BuyOneGetOneFreePromotion;
import com.bitside.challenge.shoppingbasket.model.promotion.TenPercentOffPromotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketServiceTest {
    private InventoryService inventoryService;
    private User user;

    @BeforeEach
    void setUp() {
        this.inventoryService = new InventoryService();
        this.user = new User();
        inventoryService.addProduct(new Product("A0001", 12.99));
        inventoryService.addProduct(new Product("A0002", 3.99));
    }

    @Test
    public void testNoPromotions() {
        BasketService basket = new BasketService(inventoryService);

        basket.scan(user, "A0001");
        basket.scan(user, "A0002");

        double totalValue = basket.getTotal(user);
        assertEquals(16.98, totalValue, 0.01);
    }

    @Test
    public void testInvalidProduct() {
        BasketService basket = new BasketService(inventoryService);

        try {
            basket.scan(user, "INVALID");
        } catch (IllegalArgumentException e) {
            assertEquals("Product INVALID does not exist.", e.getMessage());
        }
    }

    @Test
    public void testBuyOneGetOneFreePromotion() {
        BasketService basket = new BasketService(inventoryService);
        basket.addPromotion(new BuyOneGetOneFreePromotion("A0002"));

        basket.scan(user, "A0002");
        basket.scan(user, "A0001");
        basket.scan(user, "A0002");

        double total = basket.getTotal(user);
        assertEquals(16.98, total, 0.01);
    }

    @Test
    public void testTenPercentOffPromotion() {
        BasketService basket = new BasketService(inventoryService);
        basket.addPromotion(new TenPercentOffPromotion("A0001"));

        basket.scan(user, "A0002");
        basket.scan(user, "A0001");
        basket.scan(user, "A0002");

        double total = basket.getTotal(user);
        assertEquals(19.67, total, 0.01);
    }
}