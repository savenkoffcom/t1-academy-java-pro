package com.savenkoff.study.task4;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {

    private final Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public UserDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    public void truncate() throws SQLException {
        statement.executeUpdate("TRUNCATE TABLE users;");
    }

    public void create(User user) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO users (username) VALUES (?);");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.executeUpdate();
    }

    public void delete(User user) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ? or username = ?");
        if (user.getId() != null) preparedStatement.setLong(1, user.getId());
        else preparedStatement.setNull(1, Types.BIGINT);
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.executeUpdate();
    }

    public User get(User user) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ? or username = ?");
        if (user.getId() != null) preparedStatement.setLong(1, user.getId());
        else preparedStatement.setNull(1, Types.BIGINT);
        preparedStatement.setString(2, user.getUsername());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User dbUser = new User();
            dbUser.setId(resultSet.getLong(1));
            dbUser.setUsername(resultSet.getString("username"));
            return dbUser;
        }
        return null;
    }

    public User update(User findUserData, User newUserData) throws SQLException {
        User dbUser = get(findUserData);
        if (dbUser == null)
            throw new SQLException("User not found by params: " + dbUser);
        preparedStatement = connection.prepareStatement("UPDATE users SET username = ? WHERE id = ? or username = ?");
        preparedStatement.setString(1, newUserData.getUsername());
        if (findUserData.getId() != null) preparedStatement.setLong(2, dbUser.getId());
        else preparedStatement.setNull(2, Types.BIGINT);
        preparedStatement.setString(3, dbUser.getUsername());
        preparedStatement.executeUpdate();
        return get(dbUser);
    }

    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()) {
            users.add(new User(resultSet.getLong(1),resultSet.getString(2)));
        }
        return users;
    }
}
