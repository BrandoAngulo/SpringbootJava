package com.qualitysales.posinventory.persistence;

import com.qualitysales.posinventory.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryDAO {
    List<Category> findAll();
     Optional<Category> findById(Integer id);
     void save(Category category);
     void deleteById(Integer id);
}
