package org.financemanager.repository;

import org.financemanager.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findAll();
    //Optional<Category> findById(Long id);
    @Override
    <S extends Category> S saveAndFlush(S s);
}
