package org.financemanager.controller;

import org.financemanager.entity.Category;
import org.financemanager.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping
    public String getCategories(Model model){
        Iterable<Category> categories = categoryRepo.findAll();
        model.addAttribute("listCategories", categories);
        return "categories/categories";
    }

    @GetMapping("{id}")
    @ResponseBody
    public Optional<Category> getById(@PathVariable("id") Long id){
        Optional<Category> category;
        category = categoryRepo.findById(id);
        return category;
    }
}
