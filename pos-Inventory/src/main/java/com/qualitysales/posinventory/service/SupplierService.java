package com.qualitysales.posinventory.service;

import com.qualitysales.posinventory.Controllers.DTO.SupplierDTO;
import com.qualitysales.posinventory.model.Supplier;

import java.util.List;

public interface SupplierService {

    List<SupplierDTO> findByAll();
    Supplier findById(Integer id) throws RuntimeException;
    SupplierDTO save(Supplier supplier);
    Supplier update(Integer id, SupplierDTO supplierDTO);
    void deleteById(Integer id);
}
