package org.sahith.products.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private Double price;
    private String description;
    private String image;
    private CategoryDto category;
}
