package com.qualitysales.posinventory.Controllers;

import com.qualitysales.posinventory.Controllers.DTO.ProductDTO;
import com.qualitysales.posinventory.model.Product;
import com.qualitysales.posinventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/posinventory/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/findBy/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> productList = productService.findByAll();
        return ResponseEntity.ok(productList);
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@Valid @RequestBody ProductDTO productDTO) {

        return ResponseEntity.ok(productService.save(productDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {

        return ResponseEntity.ok(productService.update(id, productDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        productService.deleteById(id);
    }


}
