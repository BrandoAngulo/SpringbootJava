package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.model.Supplier;
import com.qualitysales.posinventory.repository.SupplierRepository;
import com.qualitysales.posinventory.service.ISuppliearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class SupplierServiceImpl implements ISuppliearService {

    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    public List<Supplier> findByAll() {
        return null;
    }

    @Override
    public Optional<Supplier> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void save(Supplier supplier) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
