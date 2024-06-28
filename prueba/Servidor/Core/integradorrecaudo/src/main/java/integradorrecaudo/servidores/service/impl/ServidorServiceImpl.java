package integradorrecaudo.servidores.service.impl;

import integradorrecaudo.servidores.dto.ServidorDTO;
import integradorrecaudo.servidores.entity.Servidor;
import integradorrecaudo.servidores.mapper.ServidorMapper;
import integradorrecaudo.servidores.repository.ServidorRepository;
import integradorrecaudo.servidores.service.ServidorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServidorServiceImpl implements ServidorService {

    private final ServidorRepository servidorRepository;

    @Override
    public List<ServidorDTO> consultarTodos() {
        List<Servidor> servidores = servidorRepository.findAll();

        return servidores.stream().map(ServidorMapper.SERVIDOR::toServidorDTO).toList();
    }

    @Override
    public String ejecutarShell() {
        try {

            Process p = Runtime.getRuntime().exec("classpath:/shells/test.sh");

            InputStream is = p.getInputStream();
            StringBuilder sb = new StringBuilder();

            Runtime.getRuntime()
                    .exec(String.format("/bin/sh -c ls %s", "/home/listoapps/apps/shells/"));

            int i = 0;
            while ((i = is.read()) != -1) {
                sb.append((char) i);
            }


        } catch (IOException e) {
            e.getMessage();
        }
        return "Exito";
    }
}
