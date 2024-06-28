package integradorrecaudo.email.components;

import integradorrecaudo.email.service.EmailService;
import org.springframework.scheduling.annotation.Scheduled;
import integradorrecaudo.procesos.service.ProcesosService;
import integradorrecaudo.procesos.dto.ProcesosDTO;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class EmailSenderProcesos {

    private final ProcesosService procesosService;

    private final TemplateEngine templateEngine;

    private final EmailService emailService;

    @Scheduled(cron = "00 00 06 * * ?")
    public void sendDailyReportProcesos() {
        try {

            List<ProcesosDTO> procesosLista;

            List<ProcesosDTO> listaProcesos = procesosService.consultarTodosProcesos();
            procesosLista = listaProcesos;

            boolean error = false;
            for (int i = 0; i < listaProcesos.toArray().length; i++) {
                boolean errores = listaProcesos.get(i).procesos().contains("Sin vales") || listaProcesos.get(i).procesos().contains("Sin estadisticas")
                        || listaProcesos.get(i).procesos().contains("Sin porcentaje") || listaProcesos.get(i).procesos().contains("Sin sobrantes") || listaProcesos.get(i).procesos().contains("Sin creditos activos");
                if (errores) {
                    error = errores;
                }
            }
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedNow = now.format(formatter);

            String subject = error ? "[Urgente] Alerta de falla en la ejecución de procesos Listo " + formattedNow : "[Informe Diario] Procesos listo ejecutados correctamente " + formattedNow;

            boolean hasError = procesosLista.stream().anyMatch(procesosDTO -> procesosDTO.procesos().contains("Sin sobrantes") || procesosDTO.procesos().contains("Sin vales") || procesosDTO.procesos().contains("Sin estadisticas") || procesosDTO.procesos().contains("Sin porcentaje"));
            String template = hasError ? "procesosEjecucion/correoProcesosConError" : "procesosEjecucion/correoProcesosSinError";


            Context context = new Context();
            context.setVariable("procesos", procesosLista);
            context.setVariable("horaActual", formattedNow);
            String htmlContent = templateEngine.process(template, context);


            if (error) {

                emailService.sendEmail(subject, htmlContent);
            } else {
                emailService.sendEmail(subject, htmlContent);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
