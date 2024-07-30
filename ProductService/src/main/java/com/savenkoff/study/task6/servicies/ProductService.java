package com.savenkoff.study.task6.servicies;

import com.savenkoff.study.task6.dto.ProductDTO;
import com.savenkoff.study.task6.dto.ProductShortDTO;
import com.savenkoff.study.task6.entities.Product;
import com.savenkoff.study.task6.repositories.ProductDAO;
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

    public Product getProductByIdAndUserId(Long productId, Long userId) {
        try {
            return dao.getProductByIdAndByUserId(productId, userId).orElseThrow();
        } catch (SQLException | NoSuchElementException e) {
            System.out.println("Не удалось получить продукт пользователя с id " + userId +" по id " + productId  + ": " + e.getMessage());
        }
        return null;
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
                            product.getId(),product.getAccNum(),product.getBalance(),product.getTypeProduct(),product.getOwner().getId()
                    ))
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            System.out.println("Не удалось получить список всех продуктов: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public Product update(Product product) {
        try {
            return dao.update(product);
        } catch (SQLException e) {
            System.out.println("Не удалось обновить продукт с id = " + e.getMessage());
        }
        return null;
    }
}
