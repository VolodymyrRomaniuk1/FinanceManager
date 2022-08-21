package org.financemanager.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.financemanager.entity.Category;
import org.financemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    public static final Logger logger = LogManager.getLogger(CategoryController.class);

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll(Model model){
        logger.info("Getting categories list");
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categoriesList", categories);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("{id:[\\d]+}")
    public ResponseEntity<Optional<Category>> getById(@PathVariable("id") Long id){
        logger.info("Getting category by id " + id);
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@Valid @ModelAttribute Category category, BindingResult bindingResult) {
        logger.info("Creating new category");
        if(bindingResult.hasErrors()){
            logger.error("Provided category has errors");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @PutMapping("{id:[\\d]+}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @Valid @RequestBody  Category category, BindingResult bindingResult){
        logger.info("Updating category id " + id);
        if(bindingResult.hasErrors()){
            logger.error("Provided category has errors");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        categoryService.update(id, category);
        logger.info("Category id " + id + " successfully updated");
        return new ResponseEntity<>("Category successfully updated.", HttpStatus.OK);
    }

    @DeleteMapping("{id:[\\d]+}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id){
        logger.info("Deleting category id " + id);
        categoryService.delete(id);
        logger.info("Category id " + id + " deleted");
        return new ResponseEntity<>("Category deleted.", HttpStatus.OK);
    }
}
