package org.sahith.products.controllers;

import org.sahith.products.dtos.CategoryDto;
import org.sahith.products.dtos.ProductDto;
import org.sahith.products.exceptions.InvalidIdException;
import org.sahith.products.exceptions.ProductNotFoundException;
import org.sahith.products.models.Category;
import org.sahith.products.models.Product;
import org.sahith.products.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsService productService;


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){

        if(id<0){
            throw new IllegalArgumentException();
        }
        Product product = productService.getProduct(id);
        if(product!=null){
            return new ResponseEntity<>(from(product), HttpStatusCode.valueOf(200));
        }

        throw new ProductNotFoundException();

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        if(id<0){
            throw new IllegalArgumentException();
        }
        Product product = from(productDto);
        Product responseProduct = productService.replaceProduct(id, product);
        return new ResponseEntity<>(from(responseProduct), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product product: products){
            ProductDto productDto = from(product);
            productDtoList.add(productDto);
        }
        return new ResponseEntity<>(productDtoList, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity("Product deleted successfully",HttpStatusCode.valueOf(200));
    }

    @PostMapping("/")
    public ResponseEntity<ProductDto> addProduct( @RequestBody ProductDto productDto){

        if(productDto.getCategory()==null || productDto.getCategory().getId()==null){
            throw new InvalidIdException();
        }

        Product product = from(productDto);
        Product responseProduct = productService.addProduct( product);
        if(responseProduct==null){
            return null;
        }
        return new ResponseEntity<>(from(responseProduct), HttpStatusCode.valueOf(201));
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
            categoryDto.setId(product.getCategory().getId());
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
            category.setId(productDto.getCategory().getId());
            category.setDescription(productDto.getCategory().getDescription());
            product.setCategory(category);
        }

        return product;
    }
}
