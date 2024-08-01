package com.savenkoff.study.task6.controllers;

import com.savenkoff.study.task6.dto.*;
import com.savenkoff.study.task6.servicies.ProductService;
import com.savenkoff.study.task6.servicies.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductsController {
    private final ProductService productService;
    private final UserService userService;

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
        return productService.debtByRequestPaymentDTO(paymentDTO);
    }
}
