package com.microservicios.cliente.servicio;


import com.microservicios.cliente.modelo.Cliente;

import java.util.List;

public interface ClienteServicio {

    List<Cliente> listaClientes();
    Cliente buscarCliente(Integer codigoCliente);
    Cliente crearCliente(Cliente cliente);
    Cliente actualizarCliente(Integer codigoCliente, Cliente cliente);
    void eliminarCliente(Integer codigoCliente);
}
