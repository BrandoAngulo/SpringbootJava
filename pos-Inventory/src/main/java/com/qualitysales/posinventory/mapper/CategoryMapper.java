package com.qualitysales.posinventory.mapper;

import com.qualitysales.posinventory.Controllers.DTO.CategoryDTO;
import com.qualitysales.posinventory.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CategoryDTO categoryDTO);

    CategoryDTO toCategoryDTO(Category category);

    List<Category> toCategories(List<CategoryDTO> categoryDTO);

    List<CategoryDTO> toCategoryDTOS(List<Category> category);
}
