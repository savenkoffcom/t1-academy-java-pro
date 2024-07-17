package com.savenkoff.study.task5.dto;

import com.savenkoff.study.task5.entities.TypeProduct;

public record ProductDTO(Long id, String accNum, Float balance, TypeProduct typeProduct, Long user_id) {
}
