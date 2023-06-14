package com.example.BackSpringBoot.email;

import com.example.BackSpringBoot.model.DossierHomologationRequest;

public interface EmailSender {
    void send(String to, String email, DossierHomologationRequest dossierHomologationRequest, String zipFilePath);
}
