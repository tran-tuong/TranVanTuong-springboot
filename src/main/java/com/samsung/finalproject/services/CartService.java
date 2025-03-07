package com.samsung.finalproject.services;

import com.samsung.finalproject.models.entities.CartItem;
import com.samsung.finalproject.models.entities.Product;
import com.samsung.finalproject.models.repositories.CartItemRepository;
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
}



