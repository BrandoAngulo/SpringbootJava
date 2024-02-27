package com.microservicios.cliente.servicio.ServicioImpl;

import com.microservicios.cliente.client.CreditoCliente;
import com.microservicios.cliente.controlador.DTO.CreditoDTO;
import com.microservicios.cliente.http.response.ClienteByCreditoResponse;
import com.microservicios.cliente.modelo.Cliente;
import com.microservicios.cliente.repositorio.ClienteRepositorio;
import com.microservicios.cliente.servicio.ClienteServicio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@AllArgsConstructor
@Slf4j
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;
    private final CreditoCliente creditoCliente;

    @Override
    public List<Cliente> listaClientes() {
        List<Cliente> clientes = clienteRepositorio.findAll();

        return clientes;
    }

    @Override
    public Cliente buscarCliente(Integer codigoCliente) {
        Cliente cliente = clienteRepositorio.findById(codigoCliente).orElseThrow(RuntimeException::new);
        try {

            return cliente;

        } catch (RuntimeException e) {
            log.error("buscarCliente" + cliente);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        Cliente guardarCliente = clienteRepositorio.save(cliente);
        log.info("crearCliente" + guardarCliente);
        return guardarCliente;
    }

    @Override
    public Cliente actualizarCliente(Integer codigoCliente, Cliente cliente) {
        Cliente clienteExiste = clienteRepositorio.findById(codigoCliente).orElseThrow(RuntimeException::new);
        try {
            clienteExiste.setNombre(cliente.getNombre());
            clienteExiste.setApellido(cliente.getApellido());
            clienteExiste.setCelular(cliente.getCelular());
            clienteExiste.setTipoDocumento(cliente.getTipoDocumento());
            clienteExiste.setDocumento(cliente.getDocumento());

            log.info("actualizarCliente= " + clienteExiste);
            return clienteRepositorio.save(clienteExiste);
        } catch (RuntimeException e) {
            log.error("actualizarCliente= " + clienteExiste);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminarCliente(Integer codigoCliente) {
        clienteRepositorio.findById(codigoCliente).orElseThrow(RuntimeException::new);
        log.info("eliminarCliente= " + codigoCliente);
        clienteRepositorio.deleteById(codigoCliente);
    }

    @Override
    public ClienteByCreditoResponse listaCreditosCliente(Integer codigoCliente) {
        //consultar el cliente
        Cliente cliente = clienteRepositorio.findById(codigoCliente).orElseThrow();
        //obtener los creditos desde la interfaz feignClient que esta en el paquete client
        List<CreditoDTO> listaCredito = creditoCliente.listarCreditoCliente(codigoCliente);

        return ClienteByCreditoResponse.builder()
                .nombreCliente(cliente.getNombre())
                .documento(cliente.getDocumento())
                .celular(cliente.getCelular())
                .listaCredito(listaCredito)
                .build();
    }
}
