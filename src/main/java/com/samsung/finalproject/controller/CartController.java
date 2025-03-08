package com.samsung.finalproject.controller;

import com.samsung.finalproject.models.entities.CartItem;
import com.samsung.finalproject.models.entities.Order;
import com.samsung.finalproject.models.viewmodels.Users;
import com.samsung.finalproject.services.CartService;
import com.samsung.finalproject.services.OrderService;
import com.samsung.finalproject.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;


    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity) {
        cartService.addToCart(productId, quantity);
        return "redirect:/cart";
    }

    @GetMapping
    public String viewCart(Model model) {
        List<CartItem> cartItems = cartService.getCartItems();
        model.addAttribute("cartItems", cartItems);

        double totalAmount = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        model.addAttribute("totalAmount", totalAmount);

        return "cart";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam String customerName,
                           @RequestParam String email,
                           @RequestParam String phoneNumber,
                           Model model) {
        // Check if user is authenticated (logged in)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return "redirect:/login";
        }

//        // Get user details
//        Users user = userService.getCurrentUser();
//        if (user == null) {
//            return "redirect:/login";
//        }

        // Clear the cart after the order is placed
        cartService.clearCart();

        // Get cart items and calculate total
        List<CartItem> cartItems = cartService.getCartItems();
        double totalAmount = cartService.calculateTotal();

        // Save the order into the database
        cartService.saveOrder(customerName, email, phoneNumber, totalAmount);


        // Pass data to the success page
        model.addAttribute("customerName", customerName);
        model.addAttribute("email", email);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("totalAmount", totalAmount); // Display total amount on success page

        return "order-success";
    }
}


