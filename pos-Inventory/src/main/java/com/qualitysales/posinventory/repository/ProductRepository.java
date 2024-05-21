package com.qualitysales.posinventory.repository;

import com.qualitysales.posinventory.Controllers.DTO.ProductDTO;
import com.qualitysales.posinventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductByPriceBetween(BigDecimal minPrice, BigDecimal MaxPrice);
}
