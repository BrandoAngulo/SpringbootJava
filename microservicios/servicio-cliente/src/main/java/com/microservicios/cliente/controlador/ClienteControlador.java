package com.microservicios.cliente.controlador;

import com.microservicios.cliente.modelo.Cliente;
import com.microservicios.cliente.servicio.ServicioImpl.ClienteServicioImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
public class ClienteControlador {

    private ClienteServicioImpl clienteServicio;

    @GetMapping("/lista-clientes")
    public List<Cliente> listaClientes() {
        return clienteServicio.listaClientes();
    }

    @GetMapping("/buscar-cliente/{codigoCliente}")
    public Cliente buscarCliente(@PathVariable Integer codigoCliente) {
        return clienteServicio.buscarCliente(codigoCliente);
    }

    @PostMapping("/crear-cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente crearCliente(@RequestBody Cliente cliente){
        return clienteServicio.crearCliente(cliente);
    }

    @PutMapping("/actualizar-cliente/{codigoCliente}")
    public Cliente actualizarCliente(@PathVariable Integer codigoCliente, @RequestBody Cliente cliente){
        return clienteServicio.actualizarCliente(codigoCliente, cliente);
    }

    @DeleteMapping("/eliminar-Cliente/{codigoCliente}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer codigoCliente) {
        clienteServicio.eliminarCliente(codigoCliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
@GetMapping("/lista-credito-cliente/{codigoCliente}")
    public ResponseEntity<?> listaCreditosCliente(@PathVariable Integer codigoCliente){
        return ResponseEntity.ok(clienteServicio.listaCreditosCliente(codigoCliente));
    }
}
