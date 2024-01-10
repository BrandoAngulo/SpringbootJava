package com.qualitysales.posinventory.persistence;

import com.qualitysales.posinventory.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface ISupplierDAO {
    List<Supplier> findAll();
    Optional<Supplier> findById(Integer id);
    void save(Supplier supplier);
    void deleteById(Integer id);
}
