package com.savenkoff.study.task4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Starter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.savenkoff.study.task4");

        UserService userService = context.getBean("userService",UserService.class);

        // Очищаем таблицу перед стартом
        userService.clear();

        // Добавляем пользователей
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUsername("daniil_" + i);
            userService.create(user);
        }
        // Удаляем пользователя по id
        userService.deleteById(1L);
        // Удаляем пользователя по username
        userService.deleteByUsername("daniil_1");
        // Получаем пользователя по id
        System.out.println(userService.getById(2L).orElse(null));
        // Получаем пользователя по username
        System.out.println(userService.getByUsername("daniil_2").orElse(null));
        // Обновляем пользователя по username
        System.out.println(userService.updateByUsername("daniil_2",new User("daniil_2024")).orElse(null));
        // Обновляем пользователя по id
        User user = userService.getByUsername("daniil_3").orElseThrow();
        System.out.println(userService.updateById(user.getId(),new User("daniil_3024")).orElse(null));
        // Получаем всех пользователей
        System.out.println("---- all users ----");
        userService.getAll().forEach(System.out::println);
    }
}
