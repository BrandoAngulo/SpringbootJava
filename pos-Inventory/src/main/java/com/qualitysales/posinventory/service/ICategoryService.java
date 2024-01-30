package com.qualitysales.posinventory.service;

import com.qualitysales.posinventory.Controllers.DTO.CategoryDTO;
import com.qualitysales.posinventory.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findByAll();
    CategoryDTO findById(Integer id) throws Exception;
    void save(Category category);
    void deleteById(Integer id);

}
