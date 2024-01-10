package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.model.Category;
import com.qualitysales.posinventory.service.ICategoryService;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements ICategoryService {
    @Override
    public List<Category> findByAll() {
        return null;
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
