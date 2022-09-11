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

/**
 * Class implements CategoryService interface.
 * Contains methods for operating with categories
 * */
@Service
public class CategoryServiceImpl implements CategoryService {

    public static final Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    /**
     * Returns a list of all categories in database
     * */
    @Override
    public List<Category> findAll() {
        logger.info("Executing category findAll");
        return categoryRepo.findAll();
    }

    /**
     * Finds category in database by id and returns category object if exists, throws NoSuchCategoryException otherwise
     * */
    @Override
    public Optional<Category> findById(Long id) {
        logger.info("Executing category id " + id +" findById");
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isEmpty()) {
            logger.error("Category id " + id + " not found");
            throw new NoSuchCategoryException("No such category");
        }
        logger.info("Category id " + id + " found");
        return category;
    }

    /**
     * Saves category object to a database. Throws CategoryAlreadyExistsException if there is already such category in database
     * */
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

    /**
     * Updates existing category object in database. Throws NoSuchCategoryException if no such category found in database
     * */
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

    /**
     * Deletes existing category object from database. Throws NoSuchCategoryException if no such category found in database
     * */
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
