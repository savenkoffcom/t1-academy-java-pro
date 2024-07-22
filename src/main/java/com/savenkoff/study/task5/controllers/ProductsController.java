package com.savenkoff.study.task5.controllers;

import com.savenkoff.study.task5.dto.ProductDTO;
import com.savenkoff.study.task5.dto.ProductShortDTO;
import com.savenkoff.study.task5.dto.UserProductsDTO;
import com.savenkoff.study.task5.entities.User;
import com.savenkoff.study.task5.servicies.ProductService;
import com.savenkoff.study.task5.servicies.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService;

    private final UserService userService;

    public ProductsController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public UserProductsDTO getProductsByUserId(@PathVariable("id") Long userId) {
        User user = new User(userService.getById(userId).orElseThrow());
        return new UserProductsDTO(user.getUsername(),productService.getProductsByUserIdDTO(userId));
    }

    @GetMapping("/{id}")
    public ProductShortDTO getProductById(@PathVariable("id") Long productId) {
        return productService.getByIdDTO(productId);
    }

    @GetMapping("/")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllDTO();
    }
}
