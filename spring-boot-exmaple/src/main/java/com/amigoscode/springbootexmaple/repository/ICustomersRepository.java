package com.amigoscode.springbootexmaple.repository;

import com.amigoscode.springbootexmaple.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomersRepository extends JpaRepository<Customers, Integer> {


}
