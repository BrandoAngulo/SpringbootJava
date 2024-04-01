package com.qualitysales.posinventory.Controllers;

import com.qualitysales.posinventory.Controllers.DTO.CategoryDTO;
import com.qualitysales.posinventory.repository.CategoryRepository;
import com.qualitysales.posinventory.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/posinventory/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<CategoryDTO> findById(@Valid @PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(categoryService.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {

        return ResponseEntity.ok().body(categoryService.findByAll());

    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody CategoryDTO categoryDTO) throws URISyntaxException {

        return ResponseEntity.ok().body(categoryService.save(categoryDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) throws Exception {

        return ResponseEntity.ok().body(categoryService.update(id, categoryDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws Exception {

        categoryService.deleteById(id);

        return ResponseEntity.ok("Category Successfully eliminated");
    }

}
