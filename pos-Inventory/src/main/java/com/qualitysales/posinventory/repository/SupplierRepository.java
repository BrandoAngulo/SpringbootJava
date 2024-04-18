package com.qualitysales.posinventory.repository;

import com.qualitysales.posinventory.model.Supplier;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
