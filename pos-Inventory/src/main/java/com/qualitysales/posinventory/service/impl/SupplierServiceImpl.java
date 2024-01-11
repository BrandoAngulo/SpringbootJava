package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.model.Supplier;
import com.qualitysales.posinventory.persistence.ISupplierDAO;
import com.qualitysales.posinventory.service.ISuppliearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SupplierServiceImpl implements ISuppliearService {

    @Autowired
    private ISupplierDAO supplierDAO;
    @Override
    public List<Supplier> findByAll() {

        return supplierDAO.findAll();
    }

    @Override
    public Optional<Supplier> findById(Integer id) {

        return supplierDAO.findById(id);
    }

    @Override
    public void save(Supplier supplier) {
        supplierDAO.save(supplier);
    }

    @Override
    public void deleteById(Integer id) {
        supplierDAO.deleteById(id);
    }
}
