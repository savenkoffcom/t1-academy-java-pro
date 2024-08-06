package com.savenkoff.study.task6.entities.projections;

import com.savenkoff.study.task6.entities.TypeProduct;

public interface ProductsProjection {
    String getAccNum();
    Float getBalance();
    TypeProduct getTypeProduct();
}
