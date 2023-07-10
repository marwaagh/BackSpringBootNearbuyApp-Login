package com.example.BackSpringBoot.LOVElements;

public class NiveauValidDSH {
        public static LOVElement EN_ATTENTE_ENVOI = new LOVElement( "NIVEAU_VALID_DEQ", "ae", "EN ATTENTE ENVOI", "En attente envoi", "0" ,"To be sent" );
        public static LOVElement EN_ATTENTE_VALIDATION = new LOVElement( "NIVEAU_VALID_DEQ", "av", "EN ATTENTE VALIDATION", "En attente validation", "1" ,"Pending validation");
        public static LOVElement VALIDE_AVEC_RESERVE = new LOVElement( "NIVEAU_VALID_DEQ", "vr", "VALIDE AVEC RESERVE", "Validé avec réserve", "2","Validated With Reservations" );
        public static LOVElement VALIDE_SANS_RESERVE = new LOVElement( "NIVEAU_VALID_DEQ", "vs", "VALIDE SANS RESERVE", "Validé sans réserve", "3","Validated without Reservation" );
        public static LOVElement REFUSE = new LOVElement( "NIVEAU_VALID_DEQ", "rf", "REFUSE", "Refusé", "4","Refused" );
        public static LOVElement SANS_TRAITEMENT = new LOVElement( "NIVEAU_VALID_DEQ", "st", "SANS TRAITEMENT", "Sans Traitement", "5","No treatment" );

        public static LOVElement[] List = new LOVElement[]{ NiveauValidDSH.EN_ATTENTE_ENVOI, NiveauValidDSH.EN_ATTENTE_VALIDATION, NiveauValidDSH.VALIDE_AVEC_RESERVE, NiveauValidDSH.VALIDE_SANS_RESERVE, NiveauValidDSH.REFUSE , NiveauValidDSH.SANS_TRAITEMENT};
}
