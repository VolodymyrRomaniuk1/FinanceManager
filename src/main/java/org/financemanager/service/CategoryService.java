package org.financemanager.service;

import org.financemanager.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Optional<Category> findById(Long id);

    Category save(Category category);

    Category update(Long id, Category category);

    void delete(Long id);
}