package org.sahith.products.controllers;

import org.sahith.products.dtos.CategoryDto;
import org.sahith.products.dtos.ProductDto;
import org.sahith.products.exceptions.ProductNotFoundException;
import org.sahith.products.models.Category;
import org.sahith.products.models.Product;
import org.sahith.products.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private IProductService productService;


    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable long id){

        if(id<0){
            throw new IllegalArgumentException();
        }
        Product product = productService.getProduct(id);
        if(product!=null){
            return from(product);
        }

        throw new ProductNotFoundException();

    }

    @PutMapping("/{id}")
    public ProductDto replaceProduct(@PathVariable long id, @RequestBody ProductDto productDto){
        if(id<0){
            throw new IllegalArgumentException();
        }
        Product product = from(productDto);
        Product responseProduct = productService.replaceProduct(id, product);
        return from(responseProduct);
    }

    @GetMapping("/")
    public List<ProductDto> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product product: products){
            ProductDto productDto = from(product);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    private ProductDto from(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImageUrl());
        if(product.getCategory()!=null){
            CategoryDto categoryDto =  new CategoryDto();
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);

        }

        return productDto;
    }

    private  Product from(ProductDto productDto) {
        Product product =  new Product();
        product.setId(productDto.getId());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImage());
        product.setPrice((productDto.getPrice()));
        if(productDto.getCategory() != null){
            Category category = new Category();
            category.setDescription(productDto.getCategory().getDescription());
            product.setCategory(category);
        }

        return product;
    }
}
