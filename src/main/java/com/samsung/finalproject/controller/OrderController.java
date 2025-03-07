package com.samsung.finalproject.controller;

import com.samsung.finalproject.models.repositories.UserRepository;
import com.samsung.finalproject.models.viewmodels.Users;
import com.samsung.finalproject.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestParam Long userId) {
        Users user = (Users) userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        orderService.placeOrder(user);
        return ResponseEntity.ok("Đặt hàng thành công!");
    }
}
