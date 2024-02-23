package com.microservicios.cliente.servicio.ServicioImpl;

import com.microservicios.cliente.modelo.Cliente;
import com.microservicios.cliente.repositorio.ClienteRepositorio;
import com.microservicios.cliente.servicio.ClienteServicio;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;
/*    @Override
    public List<Cliente> listaClientes() {
        List<Cliente> clientes = clienteRepositorio.findAll();

        return ;
    }*/

    @Override
    public Cliente buscarCliente(Integer codigoCliente) {
        return null;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente actualizarCliente(Integer codigoCliente, Cliente cliente) {
        return null;
    }

    @Override
    public void eliminarCliente(Integer codigoCliente) {

    }
}
