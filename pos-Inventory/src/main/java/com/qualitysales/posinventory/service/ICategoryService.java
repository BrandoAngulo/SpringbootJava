package com.qualitysales.posinventory.service;

import com.qualitysales.posinventory.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findByAll();
    Optional<Category> findById(Integer id);
    void save(Category category);
    void deleteById(Integer id);

}
