package integradorrecaudo.clientes.service;

import integradorrecaudo.clientes.dto.ClienteDTO;
import integradorrecaudo.clientes.dto.NombreBD;
import integradorrecaudo.clientes.dto.RegistrarActualizarClienteDTO;

import java.util.List;
import java.util.Set;

public interface ClienteService {
    public ClienteDTO registrar(RegistrarActualizarClienteDTO registrarActualizarClienteDto);

    public ClienteDTO consultarXId(Integer id);

    public List<ClienteDTO> consultarTodos();

    public ClienteDTO actualizar(RegistrarActualizarClienteDTO registrarActualizarClienteDTO);

    public boolean cambiarEstado(Integer id);

    Set<NombreBD> consultarBasesDatos();

}
