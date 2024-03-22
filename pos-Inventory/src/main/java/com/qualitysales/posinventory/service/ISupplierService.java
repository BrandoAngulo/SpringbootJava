package com.qualitysales.posinventory.service;

import com.qualitysales.posinventory.Controllers.DTO.SupplierDTO;
import com.qualitysales.posinventory.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface ISupplierService {

    List<SupplierDTO> findByAll();
    Supplier findById(Integer id) throws RuntimeException;
    SupplierDTO save(SupplierDTO supplierDTO);
    void deleteById(Integer id);
}
