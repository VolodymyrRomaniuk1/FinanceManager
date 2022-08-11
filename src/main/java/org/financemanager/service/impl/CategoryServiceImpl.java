package org.financemanager.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.financemanager.exception.CategoryAlreadyExistsException;
import org.financemanager.exception.NoSuchCategoryException;
import org.financemanager.entity.Category;
import org.financemanager.exception.NoSuchTransactionExeption;
import org.financemanager.repository.CategoryRepo;
import org.financemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {



    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        try {
            if(categoryRepo.findById(id).isEmpty()){
                throw new NoSuchTransactionExeption();
            }
            return categoryRepo.findById(id);
        } catch (NoSuchCategoryException e) {
            throw new NoSuchCategoryException();
        }
    }

    @Override
    public Category save(Category category) {
        try {
            return categoryRepo.saveAndFlush(category);
        }catch (CategoryAlreadyExistsException e){
            throw new CategoryAlreadyExistsException();
        }
    }

    @Override
    public Category update(Long id, Category category) {
        try {
            Optional<Category> cat = findById(id);
            if (cat.isPresent()) {
                cat.get().setName(category.getName());
                cat.get().setDescription(category.getDescription());
            }
            return save(cat.get());
        }catch (NoSuchCategoryException e){
            throw new NoSuchCategoryException();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            categoryRepo.deleteById(id);
        }catch (NoSuchCategoryException e){
            throw new NoSuchCategoryException();
        }
    }
}
