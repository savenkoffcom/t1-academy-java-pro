package com.savenkoff.study.task5.servicies;

import com.savenkoff.study.task5.dto.ProductDTO;
import com.savenkoff.study.task5.dto.ProductShortDTO;
import com.savenkoff.study.task5.entities.Product;
import com.savenkoff.study.task5.repositories.ProductDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductDAO dao;

    public ProductService(ProductDAO dao) {
        this.dao = dao;
    }

    public void create(Product product) {
        try {
            dao.create(product);
        } catch (SQLException e) {
            System.out.println("Не удалось создать продукт " + product + ": " + e.getMessage());
        }
    }

    public List<ProductShortDTO> getProductsByUserIdDTO(Long userId) {
        try {
            List<Product> products = dao.getProductsByUserId(userId);
            return products.stream()
                    .map(product -> new ProductShortDTO(product.getAccNum(),product.getBalance(),product.getTypeProduct()))
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            System.out.println("Не удалось получить продукты пользователя по id " + userId + ": " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public ProductShortDTO getByIdDTO(Long id) {
        try {
            Product product = dao.getById(id).orElseThrow();
            new ProductShortDTO(product.getAccNum(), product.getBalance(), product.getTypeProduct());
        } catch (SQLException | NoSuchElementException e) {
            System.out.println("Не удалось получить продукт по id " + id + ": " + e.getMessage());
        }
        return null;
    }

    public List<ProductDTO> getAllDTO() {
        try {
            return dao.getAll().stream()
                    .map(product -> new ProductDTO(
                            product.getId(),product.getAccNum(),product.getBalance(),product.getTypeProduct(),product.getOwnerId().getId()
                    ))
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            System.out.println("Не удалось получить список всех продуктов: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
