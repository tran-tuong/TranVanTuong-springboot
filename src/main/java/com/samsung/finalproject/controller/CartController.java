package com.samsung.finalproject.controller;

import com.samsung.finalproject.models.entities.CartItem;
import com.samsung.finalproject.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity) {
        cartService.addToCart(productId, quantity);
        return "redirect:/cart";
    }

    @GetMapping
    public String viewCart(Model model) {
        List<CartItem> cartItems = cartService.getCartItems();
        double totalAmount = cartService.calculateTotal();  // Calculate total price
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);  // Pass the total amount to the view
        return "cart";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam String customerName,
                           @RequestParam String email,
                           @RequestParam String phoneNumber,
                           Model model) {
        // Clear cart after checkout
        cartService.clearCart();

        // Pass customer information to the order success page
        model.addAttribute("customerName", customerName);
        model.addAttribute("email", email);
        model.addAttribute("phoneNumber", phoneNumber);

        // Redirect to order success page
        return "order-success";
    }
}


