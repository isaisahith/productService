package org.sahith.products.services;

import org.sahith.products.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICategoryService implements CategoryService{


    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<String> getCategories() {
        return  categoryRepo.getAllCategories();
    }
}
