package com.qualitysales.posinventory.service;

import com.qualitysales.posinventory.Controllers.DTO.CategoryDTO;
import com.qualitysales.posinventory.model.Category;

import java.net.URISyntaxException;
import java.util.List;

public interface ICategoryService {
    List<Category> findByAll();
    CategoryDTO findById(Integer id) throws Exception;
    Category save(CategoryDTO categoryDTO) throws URISyntaxException;

    Category update(Integer id, CategoryDTO categoryDTO) throws Exception;

    void deleteById(Integer id) throws Exception;

}
