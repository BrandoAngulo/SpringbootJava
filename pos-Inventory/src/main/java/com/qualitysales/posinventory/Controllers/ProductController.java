package com.qualitysales.posinventory.Controllers;

import com.qualitysales.posinventory.Controllers.DTO.CategoryDTO;
import com.qualitysales.posinventory.Controllers.DTO.ProductDTO;
import com.qualitysales.posinventory.model.Category;
import com.qualitysales.posinventory.model.Product;
import com.qualitysales.posinventory.service.IProductService;
import com.qualitysales.posinventory.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posinventory/product")
public class ProductController {

    @Autowired
    IProductService iProductService;

    @GetMapping("/findBy/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<Product> productOptional = iProductService.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            ProductDTO productDTO = ProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .stock(product.getStock())
                    .build();
            return ResponseEntity.ok(productDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAll() {
        List<ProductDTO> productList = iProductService.findByAll()
                .stream()
                .map(product -> ProductDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .supplier(product.getSupplier())
                        .category(product.getCategory())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .build()
                ).toList();
        return ResponseEntity.ok(productList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody ProductDTO productDTO) throws URISyntaxException {

        iProductService.save(Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .supplier(productDTO.getSupplier())
                .category(productDTO.getCategory())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .build());
        ResponseEntity.created(new URI("/api/posinventory/category/save")).build();
        System.out.println(ResponseEntity.ok(productDTO.getDescription()));
        return ResponseEntity.ok("Product successfully added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        Optional<Product> productOptional = iProductService.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setSupplier(productDTO.getSupplier());
            product.setCategory(productDTO.getCategory());
            product.setPrice(productDTO.getPrice());
            product.setStock(productDTO.getStock());
            iProductService.save(product);
            return ResponseEntity.ok("Product successfully update");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Optional<Product> productDTOOptional = iProductService.findById(id);
        if (!(productDTOOptional == null) && productDTOOptional.isPresent()) {
            iProductService.deleteById(id);
            return ResponseEntity.ok("Product succesfully deleted");
        }
        return ResponseEntity.notFound().build();
    }


}
