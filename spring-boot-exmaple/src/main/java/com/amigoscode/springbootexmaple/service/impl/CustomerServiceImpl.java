package com.amigoscode.springbootexmaple.service.impl;

import com.amigoscode.springbootexmaple.dto.CustomersNuevoDTO;
import com.amigoscode.springbootexmaple.entities.Customers;
import com.amigoscode.springbootexmaple.repository.ICustomersRepository;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    ICustomersRepository customersRepository;

    @Override
    public Customers crearCliente(CustomersNuevoDTO cliente) {
        Customers customers = new Customers(cliente.getId(), cliente.getNombre(), cliente.getCorreo(), cliente.getNumero(), cliente.getPass());
        return customersRepository.save(customers);
    }

    @Override
    public Customers editarCliente(Integer id, CustomersNuevoDTO cliente) throws Exception {
        Customers customers = customersRepository.findById(id).orElse(null);
        if (customers != null) {
            customers.setNombre(cliente.getNombre());
            customers.setCorreo(cliente.getCorreo());
            customers.setNumero(cliente.getNumero());
            customers.setPass(cliente.getPass());
            customersRepository.save(customers);
            return customers;
        }
        throw new Exception("El cliente no existe");
    }
}
