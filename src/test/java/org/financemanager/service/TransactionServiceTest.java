package org.financemanager.service;

import org.financemanager.entity.Category;
import org.financemanager.entity.Transaction;
import org.financemanager.exception.CategoryAlreadyExistsException;
import org.financemanager.exception.NoSuchCategoryException;
import org.financemanager.exception.NoSuchTransactionException;
import org.financemanager.repository.TransactionRepo;
import org.financemanager.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepo transactionRepo;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Transaction transaction;

    private Category category;

    @BeforeEach
    public void setup(){
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

    @DisplayName("JUnit test for save method")
    @Test
    void givenTransactionObject_whenSave_thenReturnTransactionObject() {
        // given - precondition or setup
        given(transactionRepo.save(transaction)).willReturn(transaction);

        // when -  action or the behaviour that we are going test
        Transaction savedTransaction = transactionService.save(transaction);

        // then - verify the output
        System.out.println(savedTransaction);
        assertThat(savedTransaction).isNotNull();
    }

    @DisplayName("JUnit test for findAll method")
    @Test
    public void givenTransactionsList_whenFindAll_thenReturnTransactionsList(){
        // given - precondition or setup
        Transaction transaction1 = Transaction.builder()
                .id(2L)
                .category(category)
                .operationType("Gain")
                .sum(940)
                .date(Date.valueOf("2022-08-15"))
                .description("Transaction description asdasd 654")
                .build();

        given(transactionRepo.findAll()).willReturn(List.of(transaction,transaction1));

        // when -  action or the behaviour that we are going test
        List<Transaction> transactionsList = transactionService.findAll();

        // then - verify the output
        System.out.println(transactionsList);
        assertThat(transactionsList).isNotNull();
        assertThat(transactionsList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAll method (negative scenario)")
    @Test
    public void givenEmptyTransactionsList_whenFindAll_thenReturnEmptyTransactionsList(){
        // given - precondition or setup
        Transaction transaction1 = Transaction.builder()
                .id(2L)
                .category(category)
                .operationType("Gain")
                .sum(940)
                .date(Date.valueOf("2022-08-15"))
                .description("Transaction description asdasd 654")
                .build();

        given(transactionRepo.findAll()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<Transaction> transactionsList = transactionService.findAll();

        // then - verify the output
        System.out.println(transactionsList);
        assertThat(transactionsList).isEmpty();
        assertThat(transactionsList.size()).isEqualTo(0);
    }

    @DisplayName("JUnit test for findById method")
    @Test
    public void givenTransactionId_whenFindById_thenReturnTransactionObject(){
        // given - precondition or setup
        given(transactionRepo.findById(transaction.getId())).willReturn(Optional.of(transaction));

        // when -  action or the behaviour that we are going test
        Transaction savedTransaction = transactionService.findById(transaction.getId()).get();

        // then - verify the output
        System.out.println(savedTransaction);
        assertThat(savedTransaction).isNotNull();
    }

    @DisplayName("JUnit test for findById method (negative scenario)")
    @Test
    public void givenTransactionId_whenFindById_thenThrowNoSuchTransactionException(){
        // given - precondition or setup
        given(transactionRepo.findById(transaction.getId())).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        assertThrows(NoSuchTransactionException.class, () -> {
            transactionService.findById(transaction.getId());
        });
    }

    @DisplayName("JUnit test for update method")
    @Test
    public void givenTransactionObject_whenUpdate_thenReturnUpdatedTransaction(){
        // given - precondition or setup
        Category category1 = Category.builder()
                .id(2L)
                .name("Category Name 2323")
                .description("Description qwe qwe 456")
                .build();

        given(transactionRepo.save(transaction)).willReturn(transaction);
        given(transactionRepo.findById(transaction.getId())).willReturn(Optional.of(transaction));
        transaction.setCategory(category1);
        transaction.setOperationType("Gain");
        transaction.setSum(1500);
        transaction.setDate(Date.valueOf("2022-08-12"));
        transaction.setDescription("Updated Transaction Description");

        // when -  action or the behaviour that we are going test
        Transaction updatedTransaction = transactionService.update(transaction.getId(), transaction);

        // then - verify the output
        System.out.println(updatedTransaction);
        assertThat(updatedTransaction.getCategory()).isEqualTo(category1);
        assertThat(updatedTransaction.getOperationType()).isEqualTo("Gain");
        assertThat(updatedTransaction.getSum()).isEqualTo(1500);
        assertThat(updatedTransaction.getDate()).isEqualTo("2022-08-12");
        assertThat(updatedTransaction.getDescription()).isEqualTo("Updated Transaction Description");
    }

    @DisplayName("JUnit test for update method (negative scenario)")
    @Test
    public void givenTransactionId_whenUpdate_thenThrowNoSuchTransactionException(){
        // given - precondition or setup
        given(transactionRepo.findById(transaction.getId())).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        assertThrows(NoSuchTransactionException.class, () -> {
            transactionService.update(transaction.getId(), transaction);
        });
    }

    @DisplayName("JUnit test for delete method")
    @Test
    public void givenTransactionId_whenDelete_thenNothing(){
        // given - precondition or setup
        Long transactionId = 1L;

        willDoNothing().given(transactionRepo).deleteById(transactionId);

        // when -  action or the behaviour that we are going test
        transactionService.delete(transactionId);

        // then - verify the output
        verify(transactionRepo, times(1)).deleteById(transactionId);
    }

    @DisplayName("JUnit test for delete method (negative scenario)")
    @Test
    public void givenTransactionId_whenDelete_thenThrowNoSuchTransactionException(){
        // given - precondition or setup
        willThrow(NoSuchTransactionException.class).given(transactionRepo).deleteById(transaction.getId());

        // when -  action or the behaviour that we are going test
        assertThrows(NoSuchTransactionException.class, () -> {
            transactionService.delete(transaction.getId());
        });
    }
}
