package org.financemanager.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.financemanager.exception.CategoryAlreadyExistsException;
import org.financemanager.exception.NoSuchCategoryException;
import org.financemanager.entity.Category;
import org.financemanager.repository.CategoryRepo;
import org.financemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    public static final Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> findAll() {
        logger.info("Executing category findAll");
        return categoryRepo.findAll(); //Sort.by(Sort.Direction.ASC, "id")
    }

    @Override
    public Optional<Category> findById(Long id) {
        logger.info("Executing category id " + id +" findById");
        Optional<Category> c = categoryRepo.findById(id);
        if (c.isEmpty()) {
            logger.error("Category id " + id + " not found");
            throw new NoSuchCategoryException("No such category");
        }
        logger.info("Category id " + id + " found");
        return c;
    }

    @Override
    public Category save(Category category) {
        logger.info("Executing category save");
        try {
            return categoryRepo.save(category);
        } catch (CategoryAlreadyExistsException e) {
            logger.error("Category save failed");
            throw new CategoryAlreadyExistsException("Category already exists");
        }
    }

    @Override
    public Category update(Long id, Category category) {
        logger.info("Executing category id " + id + " update");
        try {
            Optional<Category> newCategory = findById(id);
            if (newCategory.isPresent()) {
                logger.info("Category id " + id + " exists");
                newCategory.get().setName(category.getName());
                newCategory.get().setDescription(category.getDescription());
            }
            return save(newCategory.get());
        } catch (NoSuchCategoryException e) {
            logger.error("Category id " + id + " update failed");
            throw new NoSuchCategoryException("No such category");
        }
    }

    @Override
    public void delete(Long id) {
        logger.info("Executing category id " + id + " delete");
        try {
            categoryRepo.deleteById(id);
        } catch (NoSuchCategoryException e) {
            logger.error("Category id " + id + " deletion failed");
            throw new NoSuchCategoryException("No such category");
        }
    }
}
