package com.savenkoff.study.task6.controllers;

import com.savenkoff.study.task6.dto.*;
import com.savenkoff.study.task6.entities.Product;
import com.savenkoff.study.task6.entities.User;
import com.savenkoff.study.task6.servicies.ProductService;
import com.savenkoff.study.task6.servicies.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    private final ProductService productService;
    private final UserService userService;

    public ProductsController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public UserProductsDTO getProductsByUserId(@PathVariable("id") Long userId) {
        User user = userService.getById(userId).orElseThrow();
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

    @PostMapping("/debtAccount")
    public ResponsePaymentDTO userPaymentFromAccount(@RequestBody RequestPaymentDTO paymentDTO) {
        Product product = productService.getProductByIdAndUserId(paymentDTO.productId(), paymentDTO.userId());
        if (product == null)
            throw new NoSuchElementException("Не найден договор пользователя");
        Float currentBalance = product.getBalance();
        // Ok, закладываем ту же логику, что реккомендовали
        if (Objects.isNull(currentBalance) || currentBalance.compareTo(paymentDTO.amount()) < 0)
            throw new IllegalArgumentException("На счете " + product.getAccNum() + " не достаточно средств для совершения платежа. Текущий баланс: " + currentBalance);
        currentBalance -= paymentDTO.amount();
        product.setBalance(currentBalance);
        productService.update(product);
        return new ResponsePaymentDTO(0,"Payment successfully, balance " + currentBalance);
    }
}
