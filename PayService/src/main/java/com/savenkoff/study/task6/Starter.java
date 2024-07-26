package com.savenkoff.study.task6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Starter {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Starter.class, args);
    }

    /**
     * URLs:
     * * GET http://localhost:8082/payer-app/api/v1/products/user/{userId} - запрос всех продуктов у платежного сервиса
     * * POST http://localhost:8082/payer-app/api/v1/payments/payment - запрос на списание средств по продукту, в теле передаётся json формата:
     * {
     * 	"userId": 1,
     * 	"productId": 1,
     * 	"amount": 10
     * }
     * После нескольких вызовов деньги должны кончиться, тогда прилетит Exception
     */

}
