package com.qualitysales.posinventory.service;

import com.qualitysales.posinventory.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findByAll();
    Optional<Product> findById(Integer id);
    List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    void save(Product product);
    void deleteById(Integer id);
}
