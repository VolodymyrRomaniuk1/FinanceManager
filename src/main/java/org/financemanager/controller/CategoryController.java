package org.financemanager.controller;

import org.financemanager.entity.Category;
import org.financemanager.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping
    public ResponseEntity<List<Category>> getCategoriesList(Model model){
        List<Category> categories = categoryRepo.findAll();
        if(categories.isEmpty()){
            return new ResponseEntity<>(categories, HttpStatus.NOT_FOUND);
        }
        model.addAttribute("listCategories", categories);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Category>> getById(@PathVariable("id") Long id){
        Optional<Category> category;
        category = categoryRepo.findById(id);
        if(category.isEmpty()){
            return new ResponseEntity<>(category, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@ModelAttribute("category") Category category) {
        if(category == null){
            return new ResponseEntity<>(category, HttpStatus.NOT_FOUND);
        }
        categoryRepo.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@ModelAttribute("category") Category category){
        if(category == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        categoryRepo.save(category);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Long id){
        categoryRepo.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
