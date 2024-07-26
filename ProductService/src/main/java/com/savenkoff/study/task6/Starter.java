package com.savenkoff.study.task6;

import com.savenkoff.study.task6.entities.Product;
import com.savenkoff.study.task6.entities.TypeProduct;
import com.savenkoff.study.task6.entities.User;
import com.savenkoff.study.task6.servicies.ProductService;
import com.savenkoff.study.task6.servicies.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Starter {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Starter.class, args);

        // Получаем бины
        UserService userService = context.getBean(UserService.class);
        ProductService productService = context.getBean(ProductService.class);

        // Каскадно (из-за связи) очищаем БД
        userService.clearAll();

        // Подготавливаем пользователей
        userService.create(new User("user_1"));
        userService.create(new User("user_2"));
        userService.create(new User("user_3"));

        // Получаем пользователей для добавления им продуктов (так как cast наследуемых классов не работает, пришлось импровизировать)
        User user_1 = userService.getByUsername("user_1").orElseThrow();
        User user_2 = userService.getByUsername("user_2").orElseThrow();

        // Подготавливаем продукты
        productService.create(new Product("00001", 100F, TypeProduct.ACCOUNT,user_1));
        productService.create(new Product("00002", 100.01F, TypeProduct.ACCOUNT,user_1));
        productService.create(new Product("00003", 1_000_000F, TypeProduct.ACCOUNT,user_1));

        productService.create(new Product("00010", 2F, TypeProduct.ACCOUNT,user_2));
        productService.create(new Product("00011", 20_000F, TypeProduct.ACCOUNT,user_2));

        /**
         * Ссылки:
         * http://localhost:8081/products-app/api/v1/products/ - все продукты с id
         * http://localhost:8081/products-app/api/v1/users/ - все пользователи с id
         * http://localhost:8081/products-app/api/v1/products/{id} - получить продукт по id
         * http://localhost:8081/products-app/api/v1/products/user/{id} - получить продукты пользователя по id
         * http://localhost:8081/products-app/api/v1/products/user/{userId}/product/{productId} - получить конкретный продукт по id пользователя и id продукта
         * http://localhost:8081/products-app/api/v1/products/debtAccount - списать средства с аккаунта
         */
    }
}
