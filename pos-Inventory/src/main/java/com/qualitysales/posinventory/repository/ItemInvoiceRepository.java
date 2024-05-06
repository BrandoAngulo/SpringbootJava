package com.qualitysales.posinventory.repository;

import com.qualitysales.posinventory.model.ItemInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInvoiceRepository extends JpaRepository<ItemInvoice, Integer> {
}
