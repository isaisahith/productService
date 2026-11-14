package org.sahith.products.services;

import org.sahith.products.models.Product;

import java.util.List;

public interface ProductsService {
    Product getProduct(long id);
    Product replaceProduct(long id, Product product);
    List<Product> getAllProducts();
}
