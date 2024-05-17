package com.qualitysales.posinventory.repository;

import com.qualitysales.posinventory.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByNameOrLasNameAfter(String name, String lasSName);
}
