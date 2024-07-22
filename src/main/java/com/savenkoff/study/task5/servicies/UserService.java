package com.savenkoff.study.task5.servicies;

import com.savenkoff.study.task5.repositories.UserDAO;
import org.springframework.stereotype.Service;
import java.sql.SQLException;

@Service
public class UserService extends com.savenkoff.study.task4.UserService {

    protected UserDAO dao;

    public UserService(UserDAO dao) {
        super(dao);
        this.dao = dao;
    }

    public void clearAll() {
        try {
            dao.truncateCascade();
        } catch (SQLException e) {
            System.out.println("Не удалось очистить таблицу пользователей: " + e.getMessage());
        }
    }
}
