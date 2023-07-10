package com.example.BackSpringBoot.LOVElements;


public class NiveauValidDEQ

{

    public static LOVElement EN_ATTENTE_PROPOSITION = new LOVElement( "NIVEAU_VALID_DEQ", "ap", "EN ATTENTE PROPOSITION", "En attente proposition", "0","Pending proposition" );

    public static LOVElement EN_ATTENTE_VALIDATION = new LOVElement( "NIVEAU_VALID_DEQ", "av", "EN ATTENTE VALIDATION", "En attente validation", "1","Pending validation" );

    public static LOVElement VALIDE_AVEC_RESERVE = new LOVElement( "NIVEAU_VALID_DEQ", "vr", "VALIDE AVEC RESERVE", "Validé avec réserve", "2","Validated with Reservations" );

    public static LOVElement VALIDE_SANS_RESERVE = new LOVElement( "NIVEAU_VALID_DEQ", "vs", "VALIDE SANS RESERVE", "Validé sans réserve", "3" ,"Validated Without Reservation");

    public static LOVElement REFUSE = new LOVElement( "NIVEAU_VALID_DEQ", "rf", "REFUSE", "Refusé", "4","Refused" );


    public static LOVElement[] List = new LOVElement[]{ NiveauValidDEQ.EN_ATTENTE_PROPOSITION, NiveauValidDEQ.EN_ATTENTE_VALIDATION, NiveauValidDEQ.VALIDE_AVEC_RESERVE, NiveauValidDEQ.VALIDE_SANS_RESERVE, NiveauValidDEQ.REFUSE };

}

