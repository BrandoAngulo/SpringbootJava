package com.qualitysales.posinventory.persistence.impl;

import com.qualitysales.posinventory.model.Supplier;
import com.qualitysales.posinventory.persistence.ISupplierDAO;
import com.qualitysales.posinventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class SupplierDAOImpl implements ISupplierDAO {
    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    public List<Supplier> findAll() {
        return (List<Supplier>) supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> findById(Integer id) {
        return supplierRepository.findById(id);
    }

    @Override
    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public void deleteById(Integer id) {
        supplierRepository.deleteById(id);
    }
}
