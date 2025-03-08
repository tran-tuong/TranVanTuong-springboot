package com.samsung.finalproject.services;

import com.samsung.finalproject.models.entities.CartItem;
import com.samsung.finalproject.models.entities.Order;
import com.samsung.finalproject.models.entities.Product;
import com.samsung.finalproject.models.repositories.CartItemRepository;
import com.samsung.finalproject.models.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.samsung.finalproject.models.repositories.ProductRepository;
import java.util.List;


@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }

    public void addToCart(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    public void clearCart() {
        cartItemRepository.deleteAll();
    }

    public double calculateTotal() {
        double total = 0;
        List<CartItem> cartItems = getCartItems();
        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    // Save the order to the database
    public void saveOrder(String customerName, String email, String phoneNumber, double totalAmount) {
        Order order = new Order();
        order.setItems(getCartItems());
        order.setCustomerName(customerName);
        order.setEmail(email);
        order.setPhoneNumber(phoneNumber);
        order.setTotalAmount(totalAmount);

        // Save the order to the database
        orderRepository.save(order);
    }
}



