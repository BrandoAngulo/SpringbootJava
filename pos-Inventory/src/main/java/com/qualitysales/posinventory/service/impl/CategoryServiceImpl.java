package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.Controllers.DTO.CategoryDTO;
import com.qualitysales.posinventory.model.Category;
import com.qualitysales.posinventory.repository.CategoryRepository;
import com.qualitysales.posinventory.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        System.out.println("categories = antes de");
        List<Category> categories = categoryRepository.findAll();
        //return  categories;
        System.out.println("categories = " + categories);
        try {
            log.info("findByAll: " + categories);
            return categories;

        } catch (RuntimeException e) {
            log.error("findByAll: " + categories);
            throw new IllegalArgumentException(e);

        }
    }

    @Override
    public CategoryDTO findById(Integer id) {
        Logger logger = Logger.getLogger(getClass().getName());

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error id no encontrado"));
        try {
            System.out.println("category = " + category);
            log.info("findById: " + category);
            return CategoryDTO.builder()
                    .id(category.getId())
                    .descripcion(category.getDescription())
                    .build();

        } catch (RuntimeException e) {

            logger.info("findById" + category);
            log.error("findById: " + category);
            throw new RuntimeException(e);
        }
    }


    @Override
    public Category save(CategoryDTO categoryDTO) {
        Category category = new Category();
        try {

            return categoryRepository.save(
                    Category.builder()
                            .description(categoryDTO.getDescripcion())
                            .build());

        } catch (RuntimeException e) {
            log.error("save: " + category);
            throw new RuntimeException(e);
        }
    }

    @Override
    public CategoryDTO update(Integer id, CategoryDTO categoryDTO) throws Exception {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error id no encontrado"));

        try {
            category.setDescription(categoryDTO.getDescripcion());
            categoryRepository.save(category);
            log.info("update: " + category);
            return categoryDTO;

        } catch (RuntimeException e) {
            log.error("update" + category);
            throw new RuntimeException(e);

        }
    }

    @Override
    public void deleteById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error id no encontrado"));

        try {
            log.info("deleteById/eliminado correctamente" + category);
            categoryRepository.deleteById(id);

        } catch (RuntimeException e) {
            log.error("deleteById" + category);
            throw new RuntimeException(e);
        }

    }
}
