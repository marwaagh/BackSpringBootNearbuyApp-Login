package com.example.BackSpringBoot.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.office365.com");
        mailSender.setPort(587);
        mailSender.setUsername("marwa.ghodhbane@ensit.u-tunis.tn");
        mailSender.setPassword("3ILyjNJk//");

        // Additional configuration if required
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true");


        return mailSender;
    }

}
