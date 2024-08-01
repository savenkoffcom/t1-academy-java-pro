package com.savenkoff.study.task6.servicies;

import com.savenkoff.study.task6.dto.ProductShortDTO;
import com.savenkoff.study.task6.dto.UserDTO;
import com.savenkoff.study.task6.dto.UserProductsDTO;
import com.savenkoff.study.task6.entities.User;
import com.savenkoff.study.task6.entities.projections.UserProductsProjection;
import com.savenkoff.study.task6.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;

    private final ProductService productService;

    public User getById(Long id) {
        // usersRepository.getById(id); -- deprecated, use getReferenceById
        return usersRepository.getReferenceById(id);
    }

    public List<User> getAll() {
        return usersRepository.findAll();
    }

    public List<UserDTO> getAllDTO() {
        return this.getAll().stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername()))
                .collect(Collectors.toList());
    }

    public UserProductsDTO getProductsByUserId(Long userId) {
        UserProductsProjection userProductsProjection = usersRepository.findByIdWithInProducts(userId);
        List<ProductShortDTO> userProductsDTO = userProductsProjection.getProducts().stream()
                        .map(productsProjection ->
                                new ProductShortDTO(
                                        productsProjection.getAccNum(),
                                        productsProjection.getBalance(),
                                        productsProjection.getTypeProduct()
                                )
                        )
                .toList();
        return new UserProductsDTO(userProductsProjection.getUsername(), userProductsDTO);
    }

    public ProductShortDTO getProductByUserIdAndProductIdDTO(Long userId, Long productId) {
        return productService.getProductByIdAndUserIdDTO(productId, userId);
    }
}
