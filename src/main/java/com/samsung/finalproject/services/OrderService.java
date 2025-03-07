package com.samsung.finalproject.services;
import com.samsung.finalproject.models.entities.Order;
import com.samsung.finalproject.models.repositories.CartItemRepository;
import com.samsung.finalproject.models.repositories.OrderRepository;
import com.samsung.finalproject.models.viewmodels.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.samsung.finalproject.models.entities.CartItem;


import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public void placeOrder(String customerName, String email, String phoneNumber) {
        List<CartItem> cartItems = cartItemRepository.findAll();
        if (cartItems.isEmpty()) throw new RuntimeException("Giỏ hàng rỗng!");

        Order order = new Order();
        order.setItems(cartItems);
        order.setCustomerName(customerName);
        order.setEmail(email);
        order.setPhoneNumber(phoneNumber);

        orderRepository.save(order);
        cartItemRepository.deleteAll();
    }

    public void placeOrder(Users user) {
    }
}

