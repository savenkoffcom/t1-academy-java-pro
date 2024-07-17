package com.savenkoff.study.task5.repositories;

import com.savenkoff.study.task5.entities.Product;
import com.savenkoff.study.task5.entities.TypeProduct;
import com.savenkoff.study.task5.entities.User;
import com.savenkoff.study.task5.servicies.UserService;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDAO {

    private final Connection connection;
    private final Statement statement;
    private PreparedStatement preparedStatement;
    private UserService userService;

    public ProductDAO(Connection connection, UserService userService) throws SQLException {
        this.connection = connection;
        this.userService = userService;
        this.statement = connection.createStatement();
    }

    public void create(Product product) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO products (acc_num, balance, type_product, user_id) VALUES (?, ?, ?, ?);");
        preparedStatement.setString(1, product.getAccNum());
        preparedStatement.setFloat(2, product.getBalance());
        preparedStatement.setString(3, product.getTypeProduct().name());
        preparedStatement.setLong(4, product.getOwnerId().getId());
        preparedStatement.executeUpdate();
    }

    private Product convertResultSetEntityToProduct(ResultSet entity) throws SQLException {
        return new Product(
                entity.getLong("id"),
                entity.getString("acc_num"),
                entity.getFloat("balance"),
                TypeProduct.valueOf(entity.getString("type_product")),
                new User(userService.getById(entity.getLong("user_id")).orElseThrow())
        );
    }

    public List<Product> getProductsByUserId(Long userId) throws SQLException {
        List<Product> products = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM products WHERE user_id = " + userId + ";");
        while (resultSet.next()) {
            products.add(convertResultSetEntityToProduct(resultSet));
        }
        return products;
    }

    public Optional<Product> getById(Long id) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM products WHERE id = " + id + ";");
        if (resultSet.next()) {
            return Optional.of(convertResultSetEntityToProduct(resultSet));
        }
        return Optional.empty();
    }

    public List<Product> getAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM products;");
        while (resultSet.next()) {
            products.add(convertResultSetEntityToProduct(resultSet));
        }
        return products;
    }
}
