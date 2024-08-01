package com.savenkoff.study.task6.entities.projections;

import java.util.List;

public interface UserProductsProjection {
    String getUsername();
    List<ProductsProjection> getProducts();
}
