package org.financemanager.controller;

import org.financemanager.entity.Category;
import org.financemanager.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Category createCategory(@ModelAttribute("category") Category category) {
        categoryRepo.save(category);
        return category;
    }

    @PutMapping("{id}")
    public Category updateCategory(@ModelAttribute("category") Category category, @PathVariable("id") Long id){
        categoryRepo.save(category);
        return category;
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        categoryRepo.deleteById(id);
    }

    @GetMapping("{id}")
    public Optional<Category> getById(@PathVariable("id") Long id){
        Optional<Category> category;
        category = categoryRepo.findById(id);
        return category;
    }
}
