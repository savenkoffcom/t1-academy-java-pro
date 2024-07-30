package com.savenkoff.study.task6.dto;

public record RequestPaymentDTO(Long userId, Long productId, Float amount) {
}
