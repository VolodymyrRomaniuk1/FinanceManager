package org.financemanager.controller;

import org.financemanager.dto.ReportReqDto;
import org.financemanager.entity.Category;
import org.financemanager.entity.Transaction;
import org.financemanager.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.financemanager.repository.CategoryRepo;

import java.util.List;

@Controller
public class IndexController {

    CategoryRepo categoryRepo;
    TransactionRepo transactionRepo;

    @Autowired
    public IndexController(CategoryRepo categoryRepo, TransactionRepo transactionRepo) {
        this.categoryRepo = categoryRepo;
        this.transactionRepo = transactionRepo;
    }

    @GetMapping("/")
    public String index (@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model){
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/categoriesList")
    public String getCategories(Model model){
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categoriesList", categories);
        return "categoriesList";
    }

    @GetMapping("categories/{id}/edit")
    public String editCategory(@PathVariable("id") Long id, Model model){
        model.addAttribute("category", categoryRepo.getById(id));
        return "edit";
    }

    @GetMapping("categories/new")
    public String newCategory(@ModelAttribute("category") Category category){
        return "newCategory";
    }

    @GetMapping("transactions/new")
    public String newCategory(Model model, @ModelAttribute("transaction") Transaction transaction){
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categoriesList", categories);
        return "newTransaction";
    }

    @GetMapping("/transactionsList")
    public String getTransactions(Model model){
        List<Transaction> transactions = transactionRepo.findAll();
        model.addAttribute("transactionsList", transactions);
        return "transactionsList";
    }

    @GetMapping("/reports")
    public String reports(Model model, @ModelAttribute("reportReqDto") ReportReqDto reportReqDto){
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categoriesList", categories);
        return "reports";
    }
}