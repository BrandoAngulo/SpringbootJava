package integradorrecaudo.versiones.service.impl;

import integradorrecaudo.clientes.entity.Cliente;
import integradorrecaudo.clientes.mapper.ClienteMapper;
import integradorrecaudo.utilidades.exceptiones.NoFoundException;
import integradorrecaudo.utilidades.exceptiones.mensajes.EMensajesExcepciones;
import integradorrecaudo.versiones.dto.VersionesDTO;
import integradorrecaudo.versiones.entity.Versiones;
import integradorrecaudo.versiones.mapper.VersionesMapper;
import integradorrecaudo.versiones.repository.VersionesRepository;
import integradorrecaudo.versiones.service.VersionesService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class VervionesServiceImpl implements VersionesService {

    private final VersionesRepository versionesRepository;
    private final VersionesMapper versionesMapper;

    @Override
    public List<VersionesDTO> consultarTodos() {
        List<Versiones> procesosDTOList = versionesRepository.findAll();
        return procesosDTOList.stream().map(VersionesMapper.VERSIONES::toVersionesDTO).toList();

    }

    @Override
    public VersionesDTO registrarVersiones(VersionesDTO versionesDTO) {
        log.info("registrar: {}", versionesDTO);
        try {
            String fechaActual = LocalDate.now().toString();
            Versiones versiones = VersionesMapper.VERSIONES.toRegistrarVersiones(versionesDTO);
            versiones.setActivo(Boolean.TRUE);
            versiones.setFecha(fechaActual);

            return VersionesMapper.VERSIONES.toVersionesDTO(versionesRepository.save(versiones));

        } catch (RuntimeException ex) {
            log.error("registrarVersiones: {}", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    @Override
    public VersionesDTO actualizarVersiones(VersionesDTO versionesDTO) {
        log.info("actualizarVersiones: {}", versionesDTO);
        Versiones buscarVersion = versionesRepository.findById(versionesDTO.id()).orElseThrow(() -> new NoFoundException(EMensajesExcepciones.VERSION_NO_EXISTE));
        try {

            Versiones actualizarVersiones = versionesMapper.toRegistrarVersiones(versionesDTO);

            buscarVersion.setNombreVersion(actualizarVersiones.getNombreVersion());
            buscarVersion.setJavaVersion(actualizarVersiones.getJavaVersion());
            buscarVersion.setMovilVersion(actualizarVersiones.getMovilVersion());
            buscarVersion.setPhpVersion(actualizarVersiones.getPhpVersion());
            Versiones versiones = versionesRepository.save(buscarVersion);

            return versionesMapper.toVersionesDTO(versiones);
        } catch (RuntimeException ex) {
            log.error("actualizarVersiones: {}", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    @Override
    public boolean cambiarEstado(Integer id) {
        log.info("cambiarEstado: {}", id);
        Versiones buscarVersion = versionesRepository.findById(id).orElseThrow(() -> new NoFoundException(EMensajesExcepciones.VERSION_NO_EXISTE));
        try {

            Boolean activo = buscarVersion.getActivo();
            buscarVersion.setActivo(Boolean.TRUE.equals(activo) ? Boolean.FALSE : Boolean.TRUE);
            buscarVersion = versionesRepository.save(buscarVersion);

            return buscarVersion.getActivo();

        } catch (RuntimeException ex) {
            log.error("cambiarEstado: {}", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

}
