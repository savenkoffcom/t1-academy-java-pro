package com.savenkoff.study.task5.entities;

public class User extends com.savenkoff.study.task4.User {

    public User (com.savenkoff.study.task4.User user) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
    }

    public User(String username) {
        super(username);
    }

    public User(Long id, String username) {
        super(id, username);
    }
}
