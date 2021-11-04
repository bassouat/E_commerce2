package com.educative.ecommerce.controller;

import com.educative.ecommerce.common.ApiResponse;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> body=categoryService.listCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);

    }

    @PostMapping("/create")
    public  ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category){
        if(Objects.nonNull(categoryService.readCategory(category.getCategoryName()))){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false,"category already exists"),HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"create category"),HttpStatus.CREATED);

    }

    @PutMapping("/update/{categoryID}")
    public ResponseEntity<ApiResponse> updateCategory( @PathVariable("categoryID") Integer categoryID,@Valid @RequestBody Category category){
        //Check if the category exists
        if(Objects.nonNull(categoryService.readCategory(categoryID))){
        //if it exists update it
            categoryService.updateCategory(categoryID,category);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true,"category updated"),HttpStatus.OK);

        }
        //if category doesn't exists, return an unsuccessfull response
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category doesn't exists"), HttpStatus.NOT_FOUND);

    }



}
