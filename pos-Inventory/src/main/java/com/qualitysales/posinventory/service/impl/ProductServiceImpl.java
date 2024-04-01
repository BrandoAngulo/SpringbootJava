package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.Controllers.DTO.ProductDTO;
import com.qualitysales.posinventory.model.Product;
import com.qualitysales.posinventory.repository.ProductRepository;
import com.qualitysales.posinventory.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public List<Product> findByAll() {

        return productRepository.findAll();
    }

    @Override
    public ProductDTO findById(Integer id) throws Exception {

        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Error id no existe"));
        try {
            log.info("findById" + product.getId());
            return ProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .supplier(product.getSupplier())
                    .category(product.getCategory())
                    .price(product.getPrice())
                    .stock(product.getStock())
                    .build();
        } catch (RuntimeException e) {
            log.error("findById" + product);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> lista = productRepository.findAll();
        String findRange = "findByPriceRange";
        if (lista.isEmpty()) {
            log.error(findRange + lista);
            throw new RuntimeException();
        }
        try {
            log.info(findRange + lista);
            return productRepository.findProductByPriceBetween(minPrice, maxPrice);

        } catch (RuntimeException e) {
            log.error(findRange + lista);
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public ProductDTO save(Product product) {
        Product product1 = new Product();
        if (product1.getId() == null) {
            log.error("save" + product1);
            throw new RuntimeException();
        }
        try {
            return ProductDTO.builder()
                    .name(product1.getName())
                    .description(product1.getDescription())
                    .category(product1.getCategory())
                    .price(product1.getPrice())
                    .stock(product1.getStock())
                    .build();
        } catch (RuntimeException e) {
            log.error("save" + product1);
            throw new RuntimeException(e);
        }

    }

    public Product update(Integer id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow
                (() -> new RuntimeException("Product not found"));
        try {
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setSupplier(productDTO.getSupplier());
            product.setCategory(productDTO.getCategory());
            product.setStock(productDTO.getStock());
            log.info("update = " + product);
            return productRepository.save(product);
        } catch (RuntimeException e) {
            log.error("update = " + product);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error en el id"));
        try {
            log.info("deleteById/product delete successfully: " + product);
            productRepository.deleteById(id);
        } catch (RuntimeException e) {
            log.error("deleteBiId: " + product);
            throw new RuntimeException(e);
        }
    }
}
