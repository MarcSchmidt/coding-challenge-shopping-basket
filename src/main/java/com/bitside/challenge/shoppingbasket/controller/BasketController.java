package com.bitside.challenge.shoppingbasket.controller;

import com.bitside.challenge.shoppingbasket.model.User;
import com.bitside.challenge.shoppingbasket.model.promotion.BuyOneGetOneFreePromotion;
import com.bitside.challenge.shoppingbasket.model.promotion.TenPercentOffPromotion;
import com.bitside.challenge.shoppingbasket.service.BasketService;
import com.bitside.challenge.shoppingbasket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{username}/basket")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BasketController {
    private final BasketService basketService;
    private final UserService userService;

    @PostMapping("/scan/{productName}")
    public ResponseEntity<String> scanItem(@PathVariable String username, @PathVariable String productName) {
        User user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found: " + username);
        }
        basketService.scan(user, productName);
        return ResponseEntity.ok("Item " + productName + " added to " + username + "'s basket.");
    }

    @GetMapping("/total")
    public ResponseEntity<String> getTotal(@PathVariable String username) {
        User user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found: " + username);
        }
        double total = basketService.getTotal(user);
        return ResponseEntity.ok("Total price for " + username + ": " + String.format("%.2f", total) + " Euro");
    }

    @GetMapping("/")
    public ResponseEntity<String> getItems(@PathVariable String username) {
        User user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found: " + username);
        }
        return ResponseEntity.ok(user.getBasket().getItems().toString());
    }

    @PostMapping("/promotions/buy1get1free/{productName}")
    public ResponseEntity<String> addBuyOneGetOneFreePromotion(@PathVariable String username, @PathVariable String productName) {
        basketService.addPromotion(new BuyOneGetOneFreePromotion(productName));
        return ResponseEntity.ok("Buy 1 Get 1 Free promotion added for Product: " + productName);
    }

    @PostMapping("/promotions/tenpercent/{productName}")
    public ResponseEntity<String> addTenPercentOffPromotion(@PathVariable String username, @PathVariable String productName) {
        basketService.addPromotion(new TenPercentOffPromotion(productName));
        return ResponseEntity.ok("10% off promotion added for Product: " + productName);
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearBasket(@PathVariable String username) {
        User user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found: " + username);
        }
        basketService.clearBasket(user);
        return ResponseEntity.ok(username + "'s basket cleared.");
    }
}
