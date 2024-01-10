package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.model.Product;
import com.qualitysales.posinventory.service.IProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements IProductService {
    @Override
    public List<Product> findByAll() {
        return null;
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return null;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
