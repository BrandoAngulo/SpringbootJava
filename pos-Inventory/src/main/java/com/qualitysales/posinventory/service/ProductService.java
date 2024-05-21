package com.qualitysales.posinventory.service;

import com.qualitysales.posinventory.Controllers.DTO.ProductDTO;
import com.qualitysales.posinventory.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductDTO> findByAll();
    ProductDTO findById(Integer id);
    List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    ProductDTO save(Product product);
    Product update(Integer id, ProductDTO productDTO);
    void deleteById(Integer id);
}
