package com.savenkoff.study.task6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);

        /**
         * Ссылки:
         * http://localhost:8081/products-app/api/v1/products/ - все продукты с id
         * http://localhost:8081/products-app/api/v1/products/{id} - получить продукт по id
         * http://localhost:8081/products-app/api/v1/users/ - все пользователи с id
         * http://localhost:8081/products-app/api/v1/users/{id}/products - получить продукты пользователя по id
         * http://localhost:8081/products-app/api/v1/users/{userId}/product/{productId} - получить конкретный продукт по id пользователя и id продукта
         * http://localhost:8081/products-app/api/v1/products/debtAccount - списать средства с аккаунта
         */
    }
}
