package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.Controllers.DTO.CategoryDTO;
import com.qualitysales.posinventory.model.Category;
import com.qualitysales.posinventory.persistence.ICategoryDAO;
import com.qualitysales.posinventory.repository.CategoryRepository;
import com.qualitysales.posinventory.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryDAO categoryDAO;
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findByAll() {

        return categoryDAO.findAll();
    }

    @Override
    public Optional<Category> findById(Integer id) throws Exception {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            CategoryDTO.builder()
                    .id(category.getId())
                    .descripcion(category.getDescription())
                    .build();
            return categoryRepository.findById(id);
        }
        System.out.println("HOLA");
         throw new Exception("Category not found");
    }

    @Override
    public void save(Category category) {
        categoryDAO.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryDAO.deleteById(id);
    }
}
