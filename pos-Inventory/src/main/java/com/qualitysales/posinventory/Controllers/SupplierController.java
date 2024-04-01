package com.qualitysales.posinventory.Controllers;

import com.qualitysales.posinventory.Controllers.DTO.SupplierDTO;
import com.qualitysales.posinventory.model.Supplier;
import com.qualitysales.posinventory.service.SupplierService;
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
    private SupplierService supplierService;

    @GetMapping("/findBy/{id}")
    public ResponseEntity<?> findByid(@PathVariable Integer id) {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<SupplierDTO>> findAll() {

        return ResponseEntity.ok(supplierService.findByAll());
    }

    @PostMapping("/save")
    public ResponseEntity<SupplierDTO> save(@Valid @RequestBody SupplierDTO supplierDTO) {

        return ResponseEntity.ok(supplierService.save(supplierDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Supplier> update(@PathVariable Integer id, @RequestBody SupplierDTO supplierDTO) {
        return ResponseEntity.ok(supplierService.update(id, supplierDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        supplierService.deleteById(id);
    }

}
