package org.financemanager.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.financemanager.dto.ReportDto;
import org.financemanager.dto.ReportReqDto;
import org.financemanager.service.CategoryService;
import org.financemanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/reports")
@Controller
public class ReportController {
    public static final Logger logger = LogManager.getLogger(ReportController.class);

    private CategoryService categoryService;
    private TransactionService transactionService;

    @Autowired
    public ReportController(CategoryService categoryService, TransactionService transactionService){
        this.categoryService = categoryService;
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<ReportDto> findAllByDateBetweenAndOperationType(Model model, @ModelAttribute("reportReqDto") ReportReqDto reportReqDto){
        logger.info("Getting transactions list between dates and operation type");
        ReportDto reportDto = new ReportDto(
                transactionService.findAllByDateBetweenAndOperationType(reportReqDto.getDateStart(), reportReqDto.getDateEnd(), reportReqDto.getOperationType()),
                reportReqDto.getDateStart(),
                reportReqDto.getDateEnd(),
                reportReqDto.getOperationType()
        );
        model.addAttribute("reportDto", reportDto);
        return new ResponseEntity<>(reportDto, HttpStatus.OK);
    }
}