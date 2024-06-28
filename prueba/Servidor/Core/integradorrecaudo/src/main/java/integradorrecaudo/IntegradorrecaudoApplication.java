package integradorrecaudo;


import java.util.TimeZone;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {ThymeleafAutoConfiguration.class})
@EnableScheduling
public class IntegradorrecaudoApplication {


    @PostConstruct
    public void init() {
        // configurando Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
    }

    public static void main(String[] args) {

        SpringApplication.run(IntegradorrecaudoApplication.class, args);
    }



}
