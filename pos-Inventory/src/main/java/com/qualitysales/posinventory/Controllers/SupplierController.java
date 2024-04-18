package com.qualitysales.posinventory.Controllers;

import com.qualitysales.posinventory.Controllers.DTO.SupplierDTO;
import com.qualitysales.posinventory.model.Supplier;
import com.qualitysales.posinventory.repository.SupplierRepository;
import com.qualitysales.posinventory.service.SupplierService;
import com.qualitysales.posinventory.service.impl.SupplierServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posinventory/supplier")
public class SupplierController {

    @Autowired
    private SupplierServiceImpl supplierService;

    @GetMapping("/findBy/{id}")
    public ResponseEntity<?> findByid(@PathVariable Integer id) {

        return ResponseEntity.ok(supplierService.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {

        return ResponseEntity.ok(supplierService.findByAll());
    }

    @PostMapping("/save")
    public ResponseEntity<SupplierDTO> save(@Valid @RequestBody Supplier supplier) {

        return ResponseEntity.ok(supplierService.save(supplier));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SupplierDTO> update(@PathVariable Integer id, @RequestBody Supplier supplier) {
        return ResponseEntity.ok(supplierService.update(id, supplier));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        supplierService.deleteById(id);
    }

}
