package com.example.BackSpringBoot.service;

import com.example.BackSpringBoot.email.EmailSender;
import com.example.BackSpringBoot.exception.UserNotFoundException;
import com.example.BackSpringBoot.model.ClientSite;
import com.example.BackSpringBoot.model.DossierEquivalence;
import com.example.BackSpringBoot.model.DossierHomologation;
import com.example.BackSpringBoot.model.DossierHomologationRequest;
import com.example.BackSpringBoot.registration.EmailValidator;
import com.example.BackSpringBoot.repository.DossierHomologationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DossierHomologationService {

    private final DossierHomologationRepository dossierHomologationRepository;
    private final EmailValidator emailValidator;
    private final EmailSender emailSender;

    String zipFilePath = "C:\\Users\\Admin\\Downloads\\Isipfe.zip" ;

    @Autowired
    public DossierHomologationService(DossierHomologationRepository dossierHomologationRepository, EmailValidator emailValidator, EmailSender emailSender) {
        this.dossierHomologationRepository = dossierHomologationRepository;
        this.emailValidator = emailValidator;
        this.emailSender = emailSender;
    }

    public DossierHomologation addDossierHomologation(DossierHomologation dossierHomologation){
        return dossierHomologationRepository.save(dossierHomologation);
    }
    public List<DossierHomologation> findallDossierHomologations(){
        return dossierHomologationRepository.findAll();
    }
    public DossierHomologation updateDossierHomologation(DossierHomologation dossierHomologation){
        return  dossierHomologationRepository.save(dossierHomologation) ;
    }
    public DossierHomologation findDossierHomologationById(Long id){
        return dossierHomologationRepository.findById(id).orElseThrow(()-> new UserNotFoundException("le dossier Homologation ayant l id" + id +"est introuvable")) ;
    }
    public void deleteDossierHomologation(Long id){
        dossierHomologationRepository.deleteDossierHomologationById(id);
    }
    public void EnvoiMail(DossierHomologationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        if(request.getPkcCientSite().getClstReference() == "SIEMENS CHATILLON"){
            emailSender.send(
                    request.getEmail(),
                    buildEmailForSIEMENS(request.getDshReference(), request.getPkcCientSite().getClstEmail(),request.getAppOwner(), request.getPkEquivalence().getPkArticleInitial().getArtDesignation(), request.getPkEquivalence().getPkArticleInitial().getArtReference(), request.getPkEquivalence().getPkArticleInitial().getFabricant().getFbcReference(), request.getPkEquivalence().getPkArticleInitial().getArtCouleur(), request.getPkEquivalence().getPkArticleEquivalent().getArtReference(), request.getPkEquivalence().getPkArticleEquivalent().getFabricant().getFbcReference(), request.getPkEquivalence().getPkArticleEquivalent().getArtCouleur()),
                    request,
                    zipFilePath);
        } else {
            emailSender.send(
                    request.getEmail(),
                    buildEmail(request.getDshReference(), request.getPkcCientSite().getClstEmail(),request.getAppOwner(), request.getPkEquivalence().getPkArticleInitial().getArtDesignation(), request.getPkEquivalence().getPkArticleInitial().getArtReference(), request.getPkEquivalence().getPkArticleInitial().getFabricant().getFbcReference(), request.getPkEquivalence().getPkArticleInitial().getArtCouleur(), request.getPkEquivalence().getPkArticleEquivalent().getArtReference(), request.getPkEquivalence().getPkArticleEquivalent().getFabricant().getFbcReference(), request.getPkEquivalence().getPkArticleEquivalent().getArtCouleur()),
                    request,
                    zipFilePath);
        }
    }

    public DossierHomologationRequest createDossierHomologationRequest(DossierHomologation dossierHomologation) {
        // Retrieve the necessary information from the dossierHomologation object
        String appOwner = dossierHomologation.getAppOwner();
        String dshReference = dossierHomologation.getDshReference();
        String email = dossierHomologation.getPkClientSite().getClstEmail();
        ClientSite pkClientSite = dossierHomologation.getPkClientSite();
        DossierEquivalence pkEquivalence = dossierHomologation.getPkEquivalence();

        // Create and return the DossierHomologationRequest object
        return new DossierHomologationRequest(appOwner, dshReference, email, pkClientSite, pkEquivalence);
    }

    private String buildEmailForSIEMENS(String dshRef, String clstEmail, String appOwner, String artDesInit, String artRefInit, String artFabInit, String artCoulInit, String artRefEq, String artFabEq, String artCoulEq) {
        return "<div>\n" +
                "<p> "+dshRef+".zip</p>\n" +
                "<p>Bonjour "+appOwner+",</p>\n" +
                "<p>***********************************************************************************************</p>\n" +
                "<p>ETUDE #</p>\n" +
                "<p>ACHATS/STOCKAGE #</p>\n" +
                "<p>EQUIVALENCE #</p>\n" +
                "<p>SUPPRIME #</p>\n" +
                "<p>REFUSE #</p>\n" +
                "<p style=\"color: rgb(219, 24, 24);\">(Ne gardez que le type que vous retenez, dans l'email de réponse)</p>\n" +
                "<p>Commentaires : </p><br>\n" +
                "<p>***********************************************************************************************</p>\n" +
                "<p>Email Client/Site : <a href=\"${clstEmail}\">"+ clstEmail +"</a></p>\n" +
                "<p>Validé par :"+ appOwner +"</p>\n" +
                "<p>Désignation Composant : "+artDesInit+" </p>\n" +
                "<p>Composant initial : "+ artRefInit +"/" + artFabInit+ "/"+ artCoulInit+ "</p>\n" +
                "<p>Composant Equiv. :"+ artRefEq +"/" + artFabEq+ "/"+ artCoulEq+ "</p>\n" +
                "</div>";
    }

    private String buildEmail(String dshRef, String clstEmail, String appOwner, String artDesInit, String artRefInit, String artFabInit, String artCoulInit, String artRefEq, String artFabEq, String artCoulEq) {
        return "<div>\n" +
                "<p> "+dshRef+".zip</p>\n" +
                "<p>Bonjour "+appOwner+",</p>\n" +
                "<p>***********************************************************************************************</p>\n" +
                "<p>Email Client/Site : <a href=\"${clstEmail}\">"+ clstEmail +"</a></p>\n" +
                "<p>Validé par :"+ appOwner +"</p>\n" +
                "<p>Désignation Composant : "+artDesInit+" </p>\n" +
                "<p>Composant initial : "+ artRefInit +"/" + artFabInit+ "/"+ artCoulInit+ "</p>\n" +
                "<p>Composant Equiv. :"+ artRefEq +"/" + artFabEq+ "/"+ artCoulEq+ "</p>\n" +
                "</div>";
    }
}
