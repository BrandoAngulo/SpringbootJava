package com.qualitysales.posinventory.service;

import com.qualitysales.posinventory.Controllers.DTO.CategoryDTO;
import com.qualitysales.posinventory.model.Category;

import java.net.URISyntaxException;
import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findById(Integer id) throws Exception;
    CategoryDTO save(Category category) throws URISyntaxException;

    CategoryDTO update(Integer id, CategoryDTO categoryDTO) throws Exception;

    void deleteById(Integer id) throws Exception;

}
