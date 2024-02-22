package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.Controllers.DTO.CategoryDTO;
import com.qualitysales.posinventory.model.Category;
import com.qualitysales.posinventory.persistence.ICategoryDAO;
import com.qualitysales.posinventory.repository.CategoryRepository;
import com.qualitysales.posinventory.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryDAO categoryDAO;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findByAll() {
        try {

            return (List<Category>) categoryRepository.findAll();

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public CategoryDTO findById(Integer id) throws Exception {

        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            return CategoryDTO.builder()
                    .id(category.getId())
                    .descripcion(category.getDescription())
                    .build();

        }
        throw new Exception("La categoria no existe");
    }


    @Override
    public Category save(CategoryDTO categoryDTO) throws URISyntaxException {

        return categoryRepository.save(Category.builder()
                .description(categoryDTO.getDescripcion())
                .build());
    }

    @Override
    public Category update(Integer id, CategoryDTO categoryDTO) throws Exception {

        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setDescription(categoryDTO.getDescripcion());
            categoryRepository.save(category);
            return category;
        }
        throw new Exception("Categoria no existe");
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional != null && categoryOptional.isPresent()) {
            categoryRepository.deleteById(id);
            return;
        }

        throw new Exception("Category not found");

    }
}
