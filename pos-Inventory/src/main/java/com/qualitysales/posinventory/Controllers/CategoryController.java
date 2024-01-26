package com.qualitysales.posinventory.Controllers;

import com.qualitysales.posinventory.Controllers.DTO.CategoryDTO;
import com.qualitysales.posinventory.model.Category;
import com.qualitysales.posinventory.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posinventory")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/category/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            CategoryDTO categoryDTO = CategoryDTO.builder()
                    .id(category.getId())
                    .descripcion(category.getDescription())
                    .productList(category.getProductList())
                    .build();
            return ResponseEntity.ok(categoryDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/category/findAll")
    public ResponseEntity<?> findAll() {
        List<CategoryDTO> categoryList = categoryService.findByAll()
                .stream()
                .map(category -> CategoryDTO.builder()
                        .id(category.getId())
                        .descripcion(category.getDescription())
                        .productList(category.getProductList())
                        .build())
                .toList();
        return ResponseEntity.ok(categoryList);


    }

    @PostMapping("/category/save")
    public ResponseEntity<?> save(@RequestBody CategoryDTO categoryDTO) throws URISyntaxException {
        if (categoryDTO.getDescripcion().isBlank()) {
            ResponseEntity.badRequest().build();

        }
        categoryService.save(Category.builder()
                .description(categoryDTO.getDescripcion())
                .build());

        ResponseEntity.created(new URI("/api/posinventory/category/save")).build();
        return ResponseEntity.ok("Categoria creada con exito");
    }

    @PutMapping("/category/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setDescription(categoryDTO.getDescripcion());
            categoryService.save(category);
            return ResponseEntity.ok("Categoria actualizada correctamente");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional != null && categoryOptional.isPresent()) {
            categoryService.deleteById(id);
            return ResponseEntity.ok("Categoria eliminada con exito");
        }

        return ResponseEntity.badRequest().build();
    }

}
