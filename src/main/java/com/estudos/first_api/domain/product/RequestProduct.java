package com.estudos.first_api.domain.product;

public record RequestProduct(
        Integer id,
        String name,
        Integer price,
        String description,
        String category,
        String image) {
}
