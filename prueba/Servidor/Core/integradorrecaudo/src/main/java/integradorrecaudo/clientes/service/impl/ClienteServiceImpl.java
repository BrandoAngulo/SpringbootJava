package integradorrecaudo.clientes.service.impl;

import integradorrecaudo.clientes.dto.ClienteDTO;
import integradorrecaudo.clientes.dto.NombreBD;
import integradorrecaudo.clientes.dto.RegistrarActualizarClienteDTO;
import integradorrecaudo.clientes.entity.Cliente;
import integradorrecaudo.clientes.mapper.ClienteMapper;
import integradorrecaudo.clientes.repository.ClienteRepository;
import integradorrecaudo.clientes.service.ClienteService;
import integradorrecaudo.utilidades.exceptiones.NoFoundException;
import integradorrecaudo.utilidades.exceptiones.RequestException;
import integradorrecaudo.utilidades.exceptiones.mensajes.EMensajesExcepciones;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    @Override
    public ClienteDTO registrar(RegistrarActualizarClienteDTO registrarActualizarClienteDto) {
        log.info("registrar: ".concat(registrarActualizarClienteDto.toString()));


        if (clienteRepository.findByDocumento(registrarActualizarClienteDto.email()).isPresent()) {
            throw new RequestException(EMensajesExcepciones.DOCUMENTO_CLIENTE_DUPLICADO);
        }

        try {
            Cliente cliente = ClienteMapper.CLIENTE.registrarActualizarClienteDTOToCliente(registrarActualizarClienteDto);

            return ClienteMapper.CLIENTE.toClienteDTO(
                    clienteRepository.save(cliente));

        } catch (RuntimeException ex) {
            ex.printStackTrace();
            log.error("registrar: ".concat(ex.getMessage()));
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public ClienteDTO consultarXId(Integer id) {


        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClienteDTO> consultarTodos() {

        try {
            List<Cliente> clientes = clienteRepository.findAll();
            log.info("consular clientes: {}", clientes);
            return clientes.stream().map(ClienteMapper.CLIENTE::toClienteDTO).toList();
        } catch (RuntimeException ex) {
            log.error("consular clientes: {}", ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Transactional
    @Override
    public ClienteDTO actualizar(RegistrarActualizarClienteDTO registrarActualizarClienteDTO) {
        log.info("actualizarCliente: ".concat(registrarActualizarClienteDTO.toString()));

        if (clienteRepository.findById(registrarActualizarClienteDTO.id()).isEmpty()) {
            throw new NoFoundException(EMensajesExcepciones.USUARIO_NO_ENCONTRADO);
        }

        try {
            Cliente clienteModificado = ClienteMapper.CLIENTE.
                    registrarActualizarClienteDTOToCliente(registrarActualizarClienteDTO);

            return ClienteMapper.CLIENTE.toClienteDTO(
                    clienteRepository.save(clienteModificado));

        } catch (RuntimeException ex) {
            log.error("actualizarCliente: ".concat(ex.getMessage()));
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Transactional
    @Override
    public boolean cambiarEstado(Integer id) {
        log.info("modificarEstadoCliente: ".concat(id.toString()));
        Cliente clienteBD = clienteRepository.findById(id).orElseThrow(
                () -> new NoFoundException(EMensajesExcepciones.CLIENTE_NO_ENCONTRADO));
        try {
            clienteBD.setActivo(!clienteBD.isActivo());
            return clienteRepository.save(clienteBD).isActivo();
        } catch (RuntimeException ex) {
            log.error("modificarEstadoCliente: ".concat(ex.getMessage()));
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Set<NombreBD> consultarBasesDatos() {
        try {
            Set<NombreBD> nombresBD = clienteRepository.consultarBasesDatos();
            log.info("consular bases de datos: {}", nombresBD);
            return nombresBD;
        } catch (RuntimeException ex) {
            log.error("consular bases de datos: {}", ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }

    }
}
