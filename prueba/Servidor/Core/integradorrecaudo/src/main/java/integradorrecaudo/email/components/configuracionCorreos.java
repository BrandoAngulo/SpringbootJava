package integradorrecaudo.email.components;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
@Component
@Data
public class configuracionCorreos {

    @Value("${correo}")
    private String [] destinatarios;


    @Value("${correos-envio}")
    private String  correoEnvio;

}


