package com.savenkoff.study.task6.controllers;

import com.savenkoff.study.task6.configurations.properties.ExecutorsProperties;
import com.savenkoff.study.task6.dto.RequestPaymentDTO;
import com.savenkoff.study.task6.dto.ResponsePaymentDTO;
import com.savenkoff.study.task6.services.ProductsExecutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentsController {

    private final ProductsExecutorService productsExecutorService;
    private final ExecutorsProperties executorsProperties;

    public PaymentsController(ProductsExecutorService productsExecutorService, ExecutorsProperties executorsProperties) {
        this.productsExecutorService = productsExecutorService;
        this.executorsProperties = executorsProperties;
    }

    @PostMapping("/payment")
    public ResponsePaymentDTO userPayment(@RequestBody RequestPaymentDTO paymentDTO) {
        // Так как у нас ни экранных форм, ни интерфейса управления мы предполагаем, что выбор продукта пользователем
        // был осуществлен заранее запросом через продуктовый контроллер, поэтому здесь передаём готовое тело запроса
        ResponseEntity<ResponsePaymentDTO> response = productsExecutorService.getRestTemplateForProducts().postForEntity(
                executorsProperties.getProductsExecutorService().getUrl() + "/api/v1/products/debtAccount",
                paymentDTO,
                ResponsePaymentDTO.class
        );

        return response.getBody();
    }
}
