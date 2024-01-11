package com.qualitysales.posinventory.Controllers;

import com.qualitysales.posinventory.Controllers.DTO.CategoryDTO;
import com.qualitysales.posinventory.model.Category;
import com.qualitysales.posinventory.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/posinventory")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if(categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            CategoryDTO categoryDTO = CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getDescription())
                    .productList(category.getProductList())
                    .build();
            System.out.println(">>>>>>>>>>>>id: " + categoryDTO.getId());
            return ResponseEntity.ok(categoryDTO);
        }

        return ResponseEntity.notFound().build();
    }

}
