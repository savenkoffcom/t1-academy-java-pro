package com.savenkoff.study.task6.servicies;

import com.savenkoff.study.task6.dto.ProductDTO;
import com.savenkoff.study.task6.dto.ProductShortDTO;
import com.savenkoff.study.task6.dto.RequestPaymentDTO;
import com.savenkoff.study.task6.dto.ResponsePaymentDTO;
import com.savenkoff.study.task6.entities.Product;
import com.savenkoff.study.task6.repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsRepository productsRepository;

    public Product getProductByIdAndUserId(Long productId, Long userId) {
        return Optional.ofNullable(productsRepository.getByIdAndOwnerId(productId, userId))
                .orElseThrow(() -> new NoSuchElementException("Не найден договор с id = %d для пользователя с id = %d".formatted(productId, userId)));
    }

    public ProductShortDTO getProductByIdAndUserIdDTO(Long productId, Long userId) {
        Product product = this.getProductByIdAndUserId(productId, userId);
        return new ProductShortDTO(product.getAccNum(), product.getBalance(), product.getTypeProduct());
    }

    public Product getById(Long id) {
        return Optional.of(productsRepository.getReferenceById(id))
                .orElseThrow(() -> new NoSuchElementException("Не найден договор с id %d".formatted(id)));
    }

    public ProductShortDTO getByIdDTO(Long id) {
        Product product = this.getById(id);
        return new ProductShortDTO(product.getAccNum(), product.getBalance(), product.getTypeProduct());
    }

    public List<ProductDTO> getAllDTO() {
        return productsRepository.findAllWithUser().stream()
            .map(product -> new ProductDTO(
                    product.getId(),product.getAccNum(),product.getBalance(),product.getTypeProduct(),product.getOwner().getId()
            ))
            .collect(Collectors.toList());
    }

    public Product update(Product product) {
        return productsRepository.save(product);
    }

    public ResponsePaymentDTO debtByRequestPaymentDTO(RequestPaymentDTO request) {
        Product product =  this.getProductByIdAndUserId(request.productId(), request.userId());
        Float currentBalance = product.getBalance();
        // Ok, закладываем ту же логику, что рекомендовали
        if (Objects.isNull(currentBalance) || currentBalance.compareTo(request.amount()) < 0)
            throw new IllegalArgumentException("На счете %s не достаточно средств для совершения платежа. Текущий баланс: %f".formatted( product.getAccNum(), currentBalance));
        currentBalance -= request.amount();
        product.setBalance(currentBalance);
        this.update(product);
        return new ResponsePaymentDTO(0,"Payment successfully, balance %f".formatted(currentBalance));
    }
}
