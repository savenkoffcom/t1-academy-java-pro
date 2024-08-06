package com.savenkoff.study.task6.controllers;

import com.savenkoff.study.task6.dto.ProductShortDTO;
import com.savenkoff.study.task6.dto.UserProductsDTO;
import com.savenkoff.study.task6.servicies.UserService;
import com.savenkoff.study.task6.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserService userService;

    @GetMapping("/")
    public List<UserDTO> getAllUsers() {
        return userService.getAllDTO();
    }

    @GetMapping("/{id}/products")
    public UserProductsDTO getProductsByUserId(@PathVariable("id") Long userId) {
        return userService.getProductsByUserId(userId);
    }

    @GetMapping("/{userId}/product/{productId}")
    public ProductShortDTO getProductByUserIdAndProductId(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        return userService.getProductByUserIdAndProductIdDTO(userId, productId);
    }
}
