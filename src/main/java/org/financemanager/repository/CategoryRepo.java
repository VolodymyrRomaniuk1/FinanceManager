package org.financemanager.repository;

import org.financemanager.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    List<Category> findAll();
}
