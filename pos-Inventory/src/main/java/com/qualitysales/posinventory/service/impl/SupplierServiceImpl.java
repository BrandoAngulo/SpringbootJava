package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.Controllers.DTO.SupplierDTO;
import com.qualitysales.posinventory.model.Supplier;
import com.qualitysales.posinventory.repository.SupplierRepository;
import com.qualitysales.posinventory.service.SupplierService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional

public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierDTO> findByAll() {
        List<Supplier> listSupplier = supplierRepository.findAll();

        try {
            log.info("finByAll: " + listSupplier.stream().map(Supplier::getId).toList());
            return listSupplier.stream().map(supplier -> SupplierDTO.builder()
                            .id(supplier.getId())
                            .name(supplier.getName())
                            .phone(supplier.getPhone())
                            .nit(supplier.getNit())
                            .build())
                    .toList();
        } catch (RuntimeException e) {
            log.error("finByAll: " + listSupplier);
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Supplier findById(Integer id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("id no existe"));
        try {

            return supplier;

        } catch (RuntimeException e) {
            log.error("findById = " + supplier);
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public SupplierDTO save(Supplier supplier) {
        if (supplier.getName() == null) {
            log.error("error en el id = " + supplier);
        }
        try {
            SupplierDTO supplierDto = SupplierDTO.builder()
                    .name(supplier.getName())
                    .phone(supplier.getPhone())
                    .nit(supplier.getNit())
                    .build();
            supplierRepository.save(supplier);
            return supplierDto;

        } catch (RuntimeException e) {
            log.error("save" + supplier);
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public SupplierDTO update(Integer id, Supplier supplier) {
        Supplier supplier1 = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El id no existe"));
        SupplierDTO supplierDTO = new SupplierDTO();
        try {
            log.info("update: " + supplier);
            supplier1.setName(supplierDTO.getName());
            supplier1.setPhone(supplierDTO.getPhone());
            supplier1.setNit(supplierDTO.getNit());
            supplierRepository.save(supplier);
            return supplierDTO;

        } catch (RuntimeException e) {
            log.error("update" + supplier);
            throw new RuntimeException(e);
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
