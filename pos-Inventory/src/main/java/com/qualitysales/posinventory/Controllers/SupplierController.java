package com.qualitysales.posinventory.Controllers;

import com.qualitysales.posinventory.Controllers.DTO.SupplierDTO;
import com.qualitysales.posinventory.model.Supplier;
import com.qualitysales.posinventory.service.ISupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posinventory/supplier")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/findBy/{id}")
    public ResponseEntity<?> findByid(@PathVariable Integer id){
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<SupplierDTO>> findAll(){

        return ResponseEntity.ok(supplierService.findByAll());
    }

    @PostMapping("/save")
    public ResponseEntity<SupplierDTO> save(@Valid @RequestBody SupplierDTO supplierDTO){

        return ResponseEntity.ok(supplierService.save(supplierDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody SupplierDTO supplierDTO){
        Optional<Supplier> supplierOptional = supplierService.findById(id);
        if (supplierOptional.isPresent()){
            Supplier supplier = supplierOptional.get();
            supplier.setName(supplierDTO.getName());
            supplier.setPhone(supplierDTO.getPhone());
            supplier.setNit(supplierDTO.getNit());
            supplierService.save(supplier);

            return ResponseEntity.ok("Supplier successfully updated");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Optional<Supplier> supplierOptional = supplierService.findById(id);
        if (supplierOptional != null && supplierOptional.isPresent()){
            supplierService.deleteById(id);
            return ResponseEntity.ok("Supplier successfully deleted");
        }
        return ResponseEntity.notFound().build();
    }

}
