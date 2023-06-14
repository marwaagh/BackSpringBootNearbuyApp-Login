package com.example.BackSpringBoot.email;

import com.example.BackSpringBoot.model.DossierHomologationRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email, DossierHomologationRequest dossierHomologationRequest, String zipFilePath) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true); // Set multipart flag to true
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(dossierHomologationRequest.getDshReference() +" - Client: "+ dossierHomologationRequest.getPkcCientSite().getClstReference() +" - { "+ dossierHomologationRequest.getPkcCientSite().getClstCpville() +"}");
            helper.setFrom("marwa.ghodhbane@ensit.u-tunis.tn");

            FileSystemResource file = new FileSystemResource(new File(zipFilePath));
            helper.addAttachment(dossierHomologationRequest.getDshReference() +".zip", file);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}