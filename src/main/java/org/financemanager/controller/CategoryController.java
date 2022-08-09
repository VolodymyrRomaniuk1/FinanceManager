package org.financemanager.controller;

import org.financemanager.entity.Category;
import org.financemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategoriesList(Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("listCategories", categories);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Category>> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@ModelAttribute("category") @Valid Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody @Valid Category category){
        categoryService.update(id, category);
        return new ResponseEntity<>("Category successfully updated.", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id){
        categoryService.delete(id);
        return new ResponseEntity<>("Category deleted.", HttpStatus.OK);
    }
}
