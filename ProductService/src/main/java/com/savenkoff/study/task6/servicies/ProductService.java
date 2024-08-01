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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsRepository productsRepository;

    public Product getProductByIdAndUserId(Long productId, Long userId) {
        Product product = productsRepository.getByIdAndOwnerId(productId, userId);
        if (product == null)
            throw new NoSuchElementException("Не найден договор с id = " + productId + " для пользователя с id =" + userId);
        return product;
    }

    public ProductShortDTO getProductByIdAndUserIdDTO(Long productId, Long userId) {
        Product product = this.getProductByIdAndUserId(productId, userId);
        return new ProductShortDTO(product.getAccNum(), product.getBalance(), product.getTypeProduct());
    }

    public Product getById(Long id) {
        Product product = productsRepository.getReferenceById(id);
        if (product == null)
            throw new NoSuchElementException("Не найден договор с id " + id);
        return product;
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
            throw new IllegalArgumentException("На счете " + product.getAccNum() + " не достаточно средств для совершения платежа. Текущий баланс: " + currentBalance);
        currentBalance -= request.amount();
        product.setBalance(currentBalance);
        this.update(product);
        return new ResponsePaymentDTO(0,"Payment successfully, balance " + currentBalance);
    }
}
