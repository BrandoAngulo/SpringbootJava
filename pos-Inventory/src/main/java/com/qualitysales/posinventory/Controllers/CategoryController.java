package com.qualitysales.posinventory.Controllers;

import com.qualitysales.posinventory.Controllers.DTO.CategoryDTO;
import com.qualitysales.posinventory.model.Category;
import com.qualitysales.posinventory.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/posinventory/category")
@RestController
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @GetMapping("/find-by/{id}")
    public ResponseEntity<CategoryDTO> findById(@Valid @PathVariable Integer id)  {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);

    }

    @PostMapping("/save")
    public ResponseEntity<CategoryDTO> save(@Valid @RequestBody Category category)  {

        return ResponseEntity.ok().body(categoryService.save(category));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) throws Exception {

        return ResponseEntity.ok().body(categoryService.update(id, categoryDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws Exception {

        categoryService.deleteById(id);

        return ResponseEntity.ok("Category Successfully eliminated");
    }

}
