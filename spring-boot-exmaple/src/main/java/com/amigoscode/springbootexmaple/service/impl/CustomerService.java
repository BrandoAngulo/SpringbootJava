package com.amigoscode.springbootexmaple.service.impl;

import com.amigoscode.springbootexmaple.dto.CustomersNuevoDTO;
import com.amigoscode.springbootexmaple.entities.Customers;

public interface CustomerService {
    public Customers crearCliente(CustomersNuevoDTO cliente);
    public Customers editarCliente(Integer id, CustomersNuevoDTO cliente) throws Exception;
}
