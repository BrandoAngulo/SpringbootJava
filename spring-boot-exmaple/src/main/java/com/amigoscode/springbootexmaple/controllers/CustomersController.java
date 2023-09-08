package com.amigoscode.springbootexmaple.controllers;

import com.amigoscode.springbootexmaple.dto.CustomersNuevoDTO;
import com.amigoscode.springbootexmaple.entities.Customers;
import com.amigoscode.springbootexmaple.repository.ICustomersRepository;
import com.amigoscode.springbootexmaple.service.impl.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/clientes/")
public class CustomersController {
    private final ICustomersRepository customersRepository;
    private final CustomerService customerService;

    public CustomersController(ICustomersRepository customersRepository, CustomerService customerService) {
        this.customersRepository = customersRepository;
        this.customerService = customerService;
    }

    @GetMapping("listar")
    public List<Customers> listarClientes() {
        return customersRepository.findAll();
    }
    @GetMapping("{cliente_id}")
    public Optional<Customers> buscarClienteid(@PathVariable("cliente_id") Integer cliente_id){
        return customersRepository.findById(cliente_id);
    }

    @PostMapping("registrar")
    public ResponseEntity<?> registrarCliente(@RequestBody CustomersNuevoDTO cliente) {
        return ResponseEntity.ok().body(customerService.crearCliente(cliente));
    }

    @DeleteMapping("{cliente_id}")
    public void eliminarCliente(@PathVariable("cliente_id") Integer cliente_id) {
        customersRepository.deleteById(cliente_id);
    }

    @PutMapping("{cliente_id}")
    public ResponseEntity<Customers> actualizarCliente(@PathVariable("cliente_id") Integer cliente_id, @RequestBody CustomersNuevoDTO cliente) throws Exception {

        return ResponseEntity.ok().body(customerService.editarCliente(cliente_id, cliente));
    }

}
