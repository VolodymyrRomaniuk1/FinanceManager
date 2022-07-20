package org.financemanager.controller;

import org.financemanager.entity.Category;
import org.financemanager.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping
    public String getCategories(Model model){
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("listCategories", categories);
        return "categories/categories";
    }

    @PostMapping
    public String createCategory(@ModelAttribute("category") Category category) {
        categoryRepo.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/new")
    public String newCategory(@ModelAttribute("category") Category category, Model model){
        return "categories/new";
    }

    @PutMapping("{id}")
    public String updateCategory(@ModelAttribute("category") Category category, @PathVariable("id") Long id){
        categoryRepo.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/{id}/edit")
    public String editCategory(@PathVariable("id") Long id, Model model){
        model.addAttribute("category", categoryRepo.getById(id));
        return "categories/edit";
    }

    @GetMapping("{id}")
    @ResponseBody
    public Optional<Category> getById(@PathVariable("id") Long id){
        Optional<Category> category;
        category = categoryRepo.findById(id);
        return category;
    }
}
