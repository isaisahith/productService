package org.sahith.products.services;

import org.sahith.products.models.Product;

import java.util.List;

public interface ProductsService {
    Product getProduct(Long id);
    Product replaceProduct(Long id, Product product);
    List<Product> getAllProducts();
}
