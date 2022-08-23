package org.financemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.financemanager.entity.Category;
import org.financemanager.entity.Transaction;
import org.financemanager.exception.NoSuchTransactionException;
import org.financemanager.exception.handler.GlobalExceptionHandler;
import org.financemanager.service.impl.TransactionServiceImpl;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    private MockMvc mvc;

    @Mock
    private TransactionServiceImpl transactionService;

    @InjectMocks
    private TransactionController transactionController;

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    private Category category;

    private Transaction transaction;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders.standaloneSetup(transactionController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        category = Category.builder()
                .id(1L)
                .name("Category Name 1212")
                .description("Description asd asd 123")
                .build();

        transaction = Transaction.builder()
                .id(1L)
                .category(category)
                .operationType("Spending")
                .sum(422.1)
                .date(Date.valueOf("2022-08-14"))
                .description("Transaction description zxczxc 321")
                .build();
    }

    @DisplayName("JUnit test for getById method")
    @Test
    public void givenTransactionId_whenGetById_thenReturn200AndTransactionJson() throws Exception{
        given(transactionService.findById(transaction.getId())).willReturn(Optional.of(transaction));

        MockHttpServletResponse response = mvc.perform(get("/transactions/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        System.out.println(response.getStatus());
        System.out.println(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("JUnit test for getById method (no such transaction)")
    @Test
    public void givenNoSuchTransactionExceptionThrown_whenGetById_thenReturn404() throws Exception{
        given(transactionService.findById(1L)).willThrow(NoSuchTransactionException.class);

        MockHttpServletResponse response = mvc.perform(get("/transactions/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @DisplayName("JUnit test for saveTransaction method")
    @Test
    public void whenSaveTransaction_thenReturn200() throws Exception{
        MockHttpServletResponse response = mvc.perform(
                        post("/transactions").contentType(MediaType.APPLICATION_JSON)
                                //.content()
                                .param("category", String.valueOf(new JSONObject(ow.writeValueAsString(category))))
                                .param("operationType", "Spending")
                                .param("sum", "422.1")
                                .param("date", "2022-08-14")
                                .param("description", "Transaction Description")
                )
                .andReturn()
                .getResponse();

        System.out.println(response.getContentAsString());
        System.out.println(ow.writeValueAsString(category));
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @DisplayName("JUnit test for deleteTransaction method")
    @Test
    public void whenDeleteTransaction_thenReturn200() throws Exception{
        willDoNothing().given(transactionService).delete(transaction.getId());

        MockHttpServletResponse response = mvc.perform(delete("/transactions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("JUnit test for deleteTransaction method (no such transaction)")
    @Test
    public void whenDeleteTransaction_thenReturn404() throws Exception{
        willThrow(NoSuchTransactionException.class).given(transactionService).delete(transaction.getId());

        MockHttpServletResponse response = mvc.perform(delete("/transactions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}