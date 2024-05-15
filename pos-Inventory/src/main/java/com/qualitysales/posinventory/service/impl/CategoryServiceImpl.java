package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.Controllers.DTO.CategoryDTO;
import com.qualitysales.posinventory.mapper.CategoryMapper;
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
    private static final  String NF = "id not found";

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = CategoryMapper.MAPPER.toCategoryDtos(categories);
        try {
            log.info("findByAll: {}", categories);
            return categoryDTOList;

        } catch (RuntimeException e) {
            log.error("findByAll: " + categories);
            throw new IllegalArgumentException(e);

        }
    }

    @Override
    public CategoryDTO findById(Integer id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NF));
        CategoryDTO categoryDTO = CategoryMapper.MAPPER.toCategoryDto(category);
        try {
            log.info("findById: {}", category);
            return categoryDTO;

        } catch (RuntimeException e) {

            log.error("findById: " + category);
            throw new IllegalArgumentException(e);
        }
    }


    @Override
    public CategoryDTO save(Category category) {

        try {
            CategoryDTO saveCategoryDTO = CategoryMapper.MAPPER.toCategoryDto(category);
           categoryRepository.save(category);
           log.info("save: {}", category);
           return saveCategoryDTO;

        } catch (IllegalArgumentException e) {
            log.error("save: {}", category);
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public CategoryDTO update(Integer id, CategoryDTO categoryDTO) throws Exception {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NF));

        try {
            category.setDescription(categoryDTO.getDescription());
            categoryRepository.save(category);
            log.info("update: " + category);
            return categoryDTO;

        } catch (IllegalArgumentException e) {
            log.error("update" + category);
            throw new IllegalArgumentException(e);

        }
    }

    @Override
    public void deleteById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NF));

        try {
            log.info("deleteById/eliminado correctamente" + category);
            categoryRepository.deleteById(id);

        } catch (IllegalArgumentException e) {
            log.error("deleteById" + category);
            throw new IllegalArgumentException(e);
        }

    }
}
