package org.financemanager.controller;

import org.financemanager.dto.ReportReqDto;
import org.financemanager.entity.Category;
import org.financemanager.entity.Transaction;
import org.financemanager.service.impl.CategoryServiceImpl;
import org.financemanager.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {

    private final CategoryServiceImpl categoryService;
    private final TransactionServiceImpl transactionService;

    @Autowired
    public IndexController(CategoryServiceImpl categoryService, TransactionServiceImpl transactionService) {
        this.categoryService = categoryService;
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public String index (){
        return "index";
    }

    @GetMapping("/categoriesList")
    @PreAuthorize("hasAuthority('categories:read')")
    public String getCategories(Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categoriesList", categories);
        return "categoriesList";
    }

    @GetMapping("categories/{id}/edit")
    @PreAuthorize("hasAuthority('categories:write')")
    public String editCategory(@PathVariable("id") Long id, Model model){
        model.addAttribute("category", categoryService.findById(id));
        return "edit";
    }

    @GetMapping("categories/new")
    @PreAuthorize("hasAuthority('categories:write')")
    public String newCategory(@ModelAttribute("category") Category category){
        return "newCategory";
    }

    @GetMapping("transactions/new")
    @PreAuthorize("hasAuthority('categories:write')")
    public String newCategory(Model model, @ModelAttribute("transaction") Transaction transaction){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categoriesList", categories);
        return "newTransaction";
    }

    @GetMapping("/transactionsList")
    @PreAuthorize("hasAuthority('transactions:read')")
    public String getTransactions(Model model){
        List<Transaction> transactions = transactionService.findAll();
        model.addAttribute("transactionsList", transactions);
        return "transactionsList";
    }

    @GetMapping("/reports")
    @PreAuthorize("hasAuthority('reports:read')")
    public String reports(Model model, @ModelAttribute("reportReqDto") ReportReqDto reportReqDto){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categoriesList", categories);
        return "reports";
    }
}