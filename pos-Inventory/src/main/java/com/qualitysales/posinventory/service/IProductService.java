package com.qualitysales.posinventory.service;

import com.qualitysales.posinventory.Controllers.DTO.ProductDTO;
import com.qualitysales.posinventory.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface IProductService {
    List<Product> findByAll();
    ProductDTO findById(Integer id) throws Exception;
    List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    ProductDTO save(Product product);
    void deleteById(Integer id);
}
