package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.model.Product;
import com.qualitysales.posinventory.persistence.IProductDAO;
import com.qualitysales.posinventory.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDAO productDAO;
    @Override
    public List<Product> findByAll() {

        return productDAO.findAll();
    }

    @Override
    public Optional<Product> findById(Integer id) {

        return productDAO.findById(id);
    }

    @Override
    public List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {

        return productDAO.findByPriceInRange(minPrice, maxPrice);
    }

    @Override
    public void save(Product product) {
        productDAO.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        productDAO.deleteById(id);
    }
}
