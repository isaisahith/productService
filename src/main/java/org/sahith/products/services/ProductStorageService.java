package org.sahith.products.services;

import org.sahith.products.exceptions.ProductNotFoundException;
import org.sahith.products.models.Category;
import org.sahith.products.models.Product;
import org.sahith.products.models.State;
import org.sahith.products.repositories.CategoryRepo;
import org.sahith.products.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Primary
public class ProductStorageService implements ProductsService{

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Product getProduct(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);

        if(productOptional.isPresent() && productOptional.get().getState()!=null && productOptional.get().getState().equals(State.ACTIVE)){
            return productOptional.get();
        }else{
            throw new ProductNotFoundException();
        }

    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isPresent() && productOptional.get().getState()==State.ACTIVE){
            return productRepo.save(product);

        }else{
            throw new ProductNotFoundException();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product addProduct(Product product){
        Optional<Category> categoryOptional = categoryRepo.findById(product.getCategory().getId());

        if((categoryOptional.isPresent())){
            Category category = categoryOptional.get();
            product.setCategory(category);
            System.out.println("Inside if "+category.getId());
        }else{
            Category category = product.getCategory();
            categoryRepo.save(category);
            System.out.println("Inside else "+category.getId());
        }

        return productRepo.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);

        if(productOptional.isPresent() && productOptional.get().getState().equals(State.ACTIVE)){
            Product product = productOptional.get();
            product.setState(State.INACTIVE);
            productRepo.save(product);
        }else{
            throw new ProductNotFoundException();
        }
    }
}
