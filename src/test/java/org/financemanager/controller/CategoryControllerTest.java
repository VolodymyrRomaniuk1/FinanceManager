package org.financemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.financemanager.entity.Category;
import org.financemanager.exception.NoSuchCategoryException;
import org.financemanager.exception.handler.GlobalExceptionHandler;
import org.financemanager.service.impl.CategoryServiceImpl;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    private MockMvc mvc;

    @Mock
    private CategoryServiceImpl categoryService;

    @InjectMocks
    private CategoryController categoryController;

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    private Category category;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        category = Category.builder()
                .id(1L)
                .name("Category Name 1212")
                .description("Description asd asd 123")
                .build();
    }

    @DisplayName("JUnit test for getById method")
    @Test
    public void givenCategoryId_whenGetById_thenReturn200AndCategoryJson() throws Exception{
        given(categoryService.findById(category.getId())).willReturn(Optional.of(category));

        MockHttpServletResponse response = mvc.perform(get("/categories/1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(new ObjectMapper().readValue(response.getContentAsString(), Category.class)).isEqualTo(category);
    }

    @DisplayName("JUnit test for getById method (no such category)")
    @Test
    public void givenNoSuchCategoryExceptionThrown_whenGetById_thenReturn404() throws Exception{
        given(categoryService.findById(1L)).willThrow(NoSuchCategoryException.class);

        MockHttpServletResponse response = mvc.perform(get("/categories/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @DisplayName("JUnit test for saveCategory method")
    @Test
    public void whenSaveCategory_thenReturn200() throws Exception{
        MockHttpServletResponse response = mvc.perform(
                post("/categories").contentType(MediaType.APPLICATION_JSON)
                        .param("name", "Category Name 1212")
                        .param("description", "Description asd asd 123")
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @DisplayName("JUnit test for saveCategory method (with bad arguments)")
    @Test
    public void whenSaveCategoryWithErrors_thenReturn404() throws Exception{
        MockHttpServletResponse response = mvc.perform(
                        post("/categories").contentType(MediaType.APPLICATION_JSON)
                                .param("name", "C")
                                .param("description", "Description asd asd 123")
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @DisplayName("JUnit test for updateCategory method")
    @Test
    public void whenUpdateCategory_thenReturn200() throws Exception{
        category.setName("Updated Category Name");
        category.setDescription("Updated Category Description");

        MockHttpServletResponse response = mvc.perform(
                        put("/categories/1").contentType(MediaType.APPLICATION_JSON)
                                .content(String.valueOf(new JSONObject(ow.writeValueAsString(category))))
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("JUnit test for updateCategory method (with bad arguments)")
    @Test
    public void whenUpdateCategoryWithErrors_thenReturn404() throws Exception{
        category.setName("U");
        category.setDescription("Updated Category Description");

        MockHttpServletResponse response = mvc.perform(
                        put("/categories/1").contentType(MediaType.APPLICATION_JSON)
                                .content(String.valueOf(new JSONObject(ow.writeValueAsString(category))))
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @DisplayName("JUnit test for deleteCategory method")
    @Test
    public void whenDeleteCategory_thenReturn200() throws Exception{
        willDoNothing().given(categoryService).delete(category.getId());

        MockHttpServletResponse response = mvc.perform(delete("/categories/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("JUnit test for deleteCategory method (no such category)")
    @Test
    public void whenDeleteCategory_thenReturn404() throws Exception{
        willThrow(NoSuchCategoryException.class).given(categoryService).delete(category.getId());

        MockHttpServletResponse response = mvc.perform(delete("/categories/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}