package org.financemanager.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.financemanager.dto.ReportDto;
import org.financemanager.dto.ReportReqDto;
import org.financemanager.entity.Category;
import org.financemanager.entity.Transaction;
import org.financemanager.service.CategoryService;
import org.financemanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/reports")
@Controller
public class ReportController {
    public static final Logger logger = LogManager.getLogger(ReportController.class);

    private final CategoryService categoryService;
    private final TransactionService transactionService;

    @Autowired
    public ReportController(CategoryService categoryService, TransactionService transactionService){
        this.categoryService = categoryService;
        this.transactionService = transactionService;
    }


    @RequestMapping(method = RequestMethod.POST, params = "reportType!=dayByDayReportSpecificCategory")
    @PreAuthorize("hasAuthority('reports:read')")
    public ResponseEntity<ReportDto> findAllByDateBetweenAndOperationType(Model model, @ModelAttribute("reportReqDto") ReportReqDto reportReqDto){
        logger.info("Executing findAllByDateBetweenAndOperationType");
        List<Transaction> transactionList = transactionService.findAllByDateBetweenAndOperationType(reportReqDto.getDateStart(), reportReqDto.getDateEnd(), reportReqDto.getOperationType());
        transactionList.sort(((o1, o2) -> (int) (o1.getCategory().getId() - o2.getCategory().getId())));
        ReportDto reportDto = new ReportDto(
                transactionList,
                reportReqDto.getDateStart(),
                reportReqDto.getDateEnd(),
                reportReqDto.getOperationType(),
                null
        );
        model.addAttribute("reportDto", reportDto);
        return new ResponseEntity<>(reportDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, params = "reportType=dayByDayReportSpecificCategory")
    @PreAuthorize("hasAuthority('reports:read')")
    public ResponseEntity<ReportDto> findAllByDateBetweenAndOperationTypeAndCategory(Model model, @ModelAttribute("reportReqDto") ReportReqDto reportReqDto){
        logger.info("Executing findAllByDateBetweenAndOperationTypeAndCategory");
        ReportDto reportDto = new ReportDto(
                transactionService.findAllByDateBetweenAndOperationTypeAndCategory(reportReqDto.getDateStart(), reportReqDto.getDateEnd(), reportReqDto.getOperationType(), reportReqDto.getCategory()),
                reportReqDto.getDateStart(),
                reportReqDto.getDateEnd(),
                reportReqDto.getOperationType(),
                reportReqDto.getCategory()
        );
        model.addAttribute("reportDto", reportDto);
        return new ResponseEntity<>(reportDto, HttpStatus.OK);
    }
}