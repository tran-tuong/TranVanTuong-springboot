package com.samsung.finalproject.controller;

import com.samsung.finalproject.models.entities.Product;
import com.samsung.finalproject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Optional;
import com.samsung.finalproject.services.CartService;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductViewController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    // Hiển thị danh sách sản phẩm
    @GetMapping("/products")
    public String showProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }

    // Tìm kiếm sản phẩm theo tên
    @GetMapping("/products/search")
    public String searchProducts(@RequestParam String name, Model model) {
        List<Product> products = productService.searchProductsByName(name);
        model.addAttribute("products", products);
        return "product-list";
    }

    // Hiển thị chi tiết sản phẩm
    @GetMapping("/products/{id}")
    public String showProductDetail(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product-detail";
        }
        return "redirect:/products";
    }

}

