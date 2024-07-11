package com.savenkoff.study.task4;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public void clear() {
        try {
            dao.truncate();
        } catch (SQLException e) {
            System.out.println("Не удалось создать очистить таблицу пользователей: " + e.getMessage());
        }
    }

    public void create(User user) {
        try {
            dao.create(user);
        } catch (SQLException e) {
            System.out.println("Не удалось создать пользователя " + user + ": " + e.getMessage());
        }
    }

    public void deleteById(Long id) {
        try {
            User user = new User(id);
            dao.delete(user);
        } catch (SQLException e) {
            System.out.println("Не удалось удалить пользователя по ID " + id + ": " + e.getMessage());
        }
    }

    public void deleteByUsername(String username) {
        try {
            User user = new User(username);
            dao.delete(user);
        } catch (SQLException e) {
            System.out.println("Не удалось удалить пользователя по username " + username + ": " + e.getMessage());
        }
    }

    public Optional<User> getById(Long id) {
        try {
            User user = new User(id);
            return Optional.ofNullable(dao.get(user));
        } catch (SQLException e) {
            System.out.println("Не удалось получить пользователя по id " + id + ": " + e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<User> getByUsername(String username) {
        try {
            User user = new User(username);
            return Optional.ofNullable(dao.get(user));
        } catch (SQLException e) {
            System.out.println("Не удалось получить пользователя по username " + username + ": " + e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<User> updateById(Long id, User user) {
        try {
            User findUserData = new User(id);
            return Optional.ofNullable(dao.update(findUserData, user));
        } catch (SQLException e) {
            System.out.println("Не удалось обновить пользователя по id " + id + ": " + e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<User> updateByUsername(String username, User user) {
        try {
            User findUserData = new User(username);
            return Optional.ofNullable(dao.update(findUserData, user));
        } catch (SQLException e) {
            System.out.println("Не удалось обновить пользователя по username " + username + ": " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<User> getAll() {
        try {
            return dao.getAll();
        } catch (SQLException e) {
            System.out.println("Не удалось получить список всех пользователей: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
