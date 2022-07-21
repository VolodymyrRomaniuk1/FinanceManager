package org.financemanager.controller;

import org.financemanager.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.financemanager.repository.CategoryRepo;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping("/")
    public String index (@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model){
        model.addAttribute("name", name);
        return "index";
    }
    @GetMapping("/categories")
    @ResponseBody
    public List<Category> getCategoriesList(Model model){
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("listCategories", categories);
        return categories;
    }

    @GetMapping("/categoriesList")
    public String getCategories(Model model){
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("listCategories", categories);
        return "categories/categoriesList";
    }

    @GetMapping("categories/{id}/edit")
    public String editCategory(@PathVariable("id") Long id, Model model){
        model.addAttribute("category", categoryRepo.getById(id));
        return "categories/edit";
    }

    @GetMapping("categories/new")
    public String newCategory(@ModelAttribute("category") Category category, Model model){
        return "categories/new";
    }
}