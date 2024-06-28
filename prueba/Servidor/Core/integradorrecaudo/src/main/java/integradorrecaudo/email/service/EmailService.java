package integradorrecaudo.email.service;

import integradorrecaudo.email.components.configuracionCorreos;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class EmailService {


    private final configuracionCorreos configuracionCorreos;

    private final JavaMailSender mailSender;

    public void sendEmail(String subject, String htmlContent) {
        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            List<String> destinatarios = Arrays.stream(configuracionCorreos.getDestinatarios()).toList();
            String correoEnvio = configuracionCorreos.getCorreoEnvio();
            helper.setTo(destinatarios.toArray(new String[0]));
            helper.setFrom(correoEnvio);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
