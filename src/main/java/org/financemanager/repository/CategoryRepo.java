package org.financemanager.repository;

import org.financemanager.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Query("select c from Category c order by c.id asc")
    List<Category> findAll();
}
