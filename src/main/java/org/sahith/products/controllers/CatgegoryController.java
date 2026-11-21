package org.sahith.products.controllers;

import org.sahith.products.dtos.CategoryDto;
import org.sahith.products.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CatgegoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<String>> getCategories(){
        List<String> categories =  categoryService.getCategories();
        return new ResponseEntity<>(categories, HttpStatusCode.valueOf(200));
    }

//    @GetMapping
//    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
//        return  null;
//    }
//
//    @PutMapping
//    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
//        return null;
//    }
}
