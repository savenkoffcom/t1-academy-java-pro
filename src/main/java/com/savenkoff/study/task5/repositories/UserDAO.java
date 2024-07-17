package com.savenkoff.study.task5.repositories;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class UserDAO extends com.savenkoff.study.task4.UserDAO {

    private final Statement statement;

    public UserDAO(Connection connection) throws SQLException {
        super(connection);
        this.statement = connection.createStatement();
    }

    public void truncateCascade() throws SQLException {
        statement.executeUpdate("TRUNCATE TABLE users CASCADE;");
    }
}
