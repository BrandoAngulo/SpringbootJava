package com.qualitysales.posinventory.Controllers;

import com.qualitysales.posinventory.Controllers.DTO.ProductDTO;
import com.qualitysales.posinventory.model.Product;
import com.qualitysales.posinventory.service.ProductService;
import com.qualitysales.posinventory.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/posinventory/product")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }


    @GetMapping("/findBy/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(productService.findByAll());
    }

    @PostMapping("/save")
    public ResponseEntity<ProductDTO> save(@Valid @RequestBody Product product) {

        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping("/price")
    public ResponseEntity<?> findByPriceRange(@RequestParam BigDecimal minPrice,
                                              @RequestParam BigDecimal maxPrice) {

        return ResponseEntity.ok(productService.findByPriceRange(minPrice, maxPrice));
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
