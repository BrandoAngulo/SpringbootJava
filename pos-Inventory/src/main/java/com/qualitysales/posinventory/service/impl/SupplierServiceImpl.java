package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.Controllers.DTO.SupplierDTO;
import com.qualitysales.posinventory.model.Supplier;
import com.qualitysales.posinventory.repository.SupplierRepository;
import com.qualitysales.posinventory.service.ISupplierService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
@NoArgsConstructor

public class SupplierServiceImpl implements ISupplierService {

    private SupplierRepository supplierRepository;

    @Override
    public List<SupplierDTO> findByAll() {
        List<Supplier> listSupplier = supplierRepository.findAll();

        try {
            log.info("finByAll: " + listSupplier);
            return listSupplier.stream().map(supplier -> SupplierDTO.builder()
                            .id(supplier.getId())
                            .name(supplier.getName())
                            .phone(supplier.getPhone())
                            .nit(supplier.getNit())
                            .build())
                    .toList();
        } catch (RuntimeException e) {
            log.error("finByAll: " + listSupplier);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Supplier findById(Integer id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(""));
        try {

            return supplier;

        } catch (RuntimeException e) {
            log.error("findById" + supplier);
            throw new RuntimeException(e);
        }
    }

    @Override
    public SupplierDTO save(SupplierDTO supplierDTO) {
        try {
            return SupplierDTO.builder()
                    .id(supplierDTO.getId())
                    .name(supplierDTO.getName())
                    .phone(supplierDTO.getPhone())
                    .nit(supplierDTO.getNit())
                    .build();

        } catch (RuntimeException e) {
            log.error("save" + supplierDTO);
            throw new RuntimeException(e);
        }

    }

    public Supplier update(Integer id, SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El id no existe"));
        try {
            log.info("update: " +supplier);
            supplier.setName(supplierDTO.getName());
            supplier.setPhone(supplierDTO.getPhone());
            supplier.setNit(supplierDTO.getNit());
            return supplierRepository.save(supplier);

        } catch (RuntimeException e) {
            log.info("");
        }

    }

    @Override
    public void deleteById(Integer id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        try {
            log.info("deleteById" + supplier);
            supplierRepository.deleteById(supplier.getId());
        } catch (RuntimeException e) {
            log.error("deleteById" + supplier);
            throw new RuntimeException(e);
        }

    }
}
