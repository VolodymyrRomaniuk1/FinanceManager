package org.financemanager.service;

import org.financemanager.entity.Category;
import org.financemanager.exception.CategoryAlreadyExistsException;
import org.financemanager.exception.NoSuchCategoryException;
import org.financemanager.repository.CategoryRepo;
import org.financemanager.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;

    @BeforeEach
    public void setup(){
        category = Category.builder()
                .id(1L)
                .name("Category Name 1212")
                .description("Description asd asd 123")
                .build();
    }

    @DisplayName("JUnit test for save method")
    @Test
    void givenCategoryObject_whenSave_thenReturnCategoryObject() {
        // given - precondition or setup
        given(categoryRepo.save(category)).willReturn(category);

        // when -  action or the behaviour that we are going test
        Category savedCategory = categoryService.save(category);

        // then - verify the output
        System.out.println(savedCategory);
        assertThat(savedCategory).isNotNull();
    }

    @DisplayName("JUnit test for save method (negative scenario)")
    @Test
    void givenCategoryObject_whenSave_thenCategoryAlreadyExistsExceptionThrown() {
        // given - precondition or setup
        given(categoryRepo.save(category)).willThrow(CategoryAlreadyExistsException.class);

        // when -  action or the behaviour that we are going test
        assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryService.save(category);
        });
    }

    @DisplayName("JUnit test for findAll method")
    @Test
    public void givenCategoriesList_whenFindAll_thenReturnCategoriesList(){
        // given - precondition or setup
        Category category1 = Category.builder()
                .id(2L)
                .name("Category Name 2323")
                .description("Description qwe qwe 456")
                .build();

        given(categoryRepo.findAll()).willReturn(List.of(category,category1));

        // when -  action or the behaviour that we are going test
        List<Category> categoriesList = categoryService.findAll();

        // then - verify the output
        System.out.println(categoriesList);
        assertThat(categoriesList).isNotNull();
        assertThat(categoriesList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAll method (negative scenario)")
    @Test
    public void givenEmptyCategoriesList_whenFindAll_thenReturnEmptyCategoriesList(){
        // given - precondition or setup
        Category category1 = Category.builder()
                .id(2L)
                .name("Category Name 2323")
                .description("Description qwe qwe 456")
                .build();

        given(categoryRepo.findAll()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<Category> categoriesList = categoryService.findAll();

        // then - verify the output
        System.out.println(categoriesList);
        assertThat(categoriesList).isEmpty();
        assertThat(categoriesList.size()).isEqualTo(0);
    }

    @DisplayName("JUnit test for findById method")
    @Test
    public void givenCategoryId_whenFindById_thenReturnCategoryObject(){
        // given - precondition or setup
        given(categoryRepo.findById(category.getId())).willReturn(Optional.of(category));

        // when -  action or the behaviour that we are going test
        Category savedCategory = categoryService.findById(category.getId()).get();

        // then - verify the output
        System.out.println(savedCategory);
        assertThat(savedCategory).isNotNull();
    }

    @DisplayName("JUnit test for findById method (negative scenario)")
    @Test
    public void givenCategoryId_whenFindById_thenThrowNoSuchCategoryException(){
        // given - precondition or setup
        given(categoryRepo.findById(category.getId())).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        assertThrows(NoSuchCategoryException.class, () -> {
            categoryService.findById(category.getId());
        });
    }

    @DisplayName("JUnit test for update method")
    @Test
    public void givenCategoryIdAndCategoryObject_whenUpdate_thenReturnUpdatedCategory(){
        // given - precondition or setup
        given(categoryRepo.save(category)).willReturn(category);
        given(categoryRepo.findById(category.getId())).willReturn(Optional.of(category));
        category.setName("Updated Category Name");
        category.setDescription("Updated Description");

        // when -  action or the behaviour that we are going test
        Category updatedCategory = categoryService.update(category.getId(), category);

        // then - verify the output
        System.out.println(updatedCategory);
        assertThat(updatedCategory.getName()).isEqualTo("Updated Category Name");
        assertThat(updatedCategory.getDescription()).isEqualTo("Updated Description");
    }

    @DisplayName("JUnit test for update method (negative scenario)")
    @Test
    public void givenCategoryId_whenUpdate_thenThrowNoSuchCategoryException(){
        // given - precondition or setup
        given(categoryRepo.findById(category.getId())).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        assertThrows(NoSuchCategoryException.class, () -> {
            categoryService.update(category.getId(), category);
        });
    }

    @DisplayName("JUnit test for delete method")
    @Test
    public void givenCategoryId_whenDelete_thenNothing(){
        // given - precondition or setup
        Long categoryId = 1L;

        willDoNothing().given(categoryRepo).deleteById(categoryId);

        // when -  action or the behaviour that we are going test
        categoryService.delete(categoryId);

        // then - verify the output
        verify(categoryRepo, times(1)).deleteById(categoryId);
    }

    @DisplayName("JUnit test for delete method (negative scenario)")
    @Test
    public void givenCategoryId_whenDelete_thenThrowNoSuchCategoryException(){
        // given - precondition or setup
        willThrow(NoSuchCategoryException.class).given(categoryRepo).deleteById(category.getId());

        // when -  action or the behaviour that we are going test
        assertThrows(NoSuchCategoryException.class, () -> {
            categoryService.delete(category.getId());
        });
    }
}