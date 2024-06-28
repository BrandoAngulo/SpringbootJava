package integradorrecaudo.zona_horaria.service.impl;

import integradorrecaudo.zona_horaria.entity.ZonaHoraria;
import integradorrecaudo.zona_horaria.repository.ZonaHorariaRepository;
import integradorrecaudo.zona_horaria.service.ZonaHorariaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ZonaHorariaServiceImpl implements ZonaHorariaService {

    private final ZonaHorariaRepository zonaHorariaRepository;

    @Override
    public List<ZonaHoraria> consultarTodos() {
        return zonaHorariaRepository.findAll();
    }
}
