package org.sahith.products.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String description;
    private Double price;
    private Category category;
    private String imageUrl;
}
