package com.qualitysales.posinventory.mapper;

import com.qualitysales.posinventory.Controllers.DTO.CategoryDTO;
import com.qualitysales.posinventory.model.Category;
import org.modelmapper.ModelMapper;

public class CategoryMapper {

    private ModelMapper modelMapper;
    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryDTO convertToDTO(Category category){
        return modelMapper.map(category, CategoryDTO.class);
    }
    public Category convertToEntity(CategoryDTO categoryDTO){
        return modelMapper.map(categoryDTO, Category.class);
    }



}
