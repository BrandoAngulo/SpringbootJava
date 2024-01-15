package com.qualitysales.posinventory.service;

import com.qualitysales.posinventory.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface ISupplierService {

    List<Supplier> findByAll();
    Optional<Supplier> findById(Integer id);
    void save(Supplier supplier);
    void deleteById(Integer id);
}
