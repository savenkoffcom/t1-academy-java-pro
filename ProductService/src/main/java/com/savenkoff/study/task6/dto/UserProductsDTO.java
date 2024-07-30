package com.savenkoff.study.task6.dto;

import java.util.List;

public record UserProductsDTO(String username, List<ProductShortDTO> products) {
}
