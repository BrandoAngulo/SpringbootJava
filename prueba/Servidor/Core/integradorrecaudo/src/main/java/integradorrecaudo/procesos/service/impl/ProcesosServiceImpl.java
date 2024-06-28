package integradorrecaudo.procesos.service.impl;

import integradorrecaudo.procesos.dto.ProcesosDTO;
import integradorrecaudo.procesos.entity.Procesos;
import integradorrecaudo.procesos.mapper.ProcesosMapper;
import integradorrecaudo.procesos.repository.ProcesosRepository;
import integradorrecaudo.procesos.service.ProcesosService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ProcesosServiceImpl implements ProcesosService {
    private final ProcesosMapper procesosMapper;


    private final ProcesosRepository procesosRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ProcesosDTO> consultarTodosProcesos() {
        List<Procesos> procesosDTOList = procesosRepository.findAll();

        return procesosDTOList.stream()
                .filter(proceso -> proceso.getDescripcion().contains("Sin vales") ||
                        proceso.getDescripcion().contains("Sin estadisticas") ||
                        proceso.getDescripcion().contains("Sin porcentaje") ||
                        proceso.getDescripcion().contains("Sin creditos activos") ||
                        proceso.getDescripcion().contains("Sin sobrantes"))
                .map(procesosMapper::toProcesosDTO)
                .toList();

    }
}
