package com.example.BackSpringBoot.LOVElements;

public class Couleur
{

    public static LOVElement VERT = new LOVElement( "COULEUR", "v", "VERT", "Vert", "0", "#000000", "#00FF00","Green" );
    public static LOVElement ORANGE = new LOVElement( "COULEUR", "o", "ORANGE", "Orange", "1", "#000000", "#FF6600","Orange" );
    public static LOVElement JAUNE = new LOVElement( "COULEUR", "j", "JAUNE", "Jaune", "2", "#000000", "#FFFF00", "Yellow" );
    public static LOVElement ROUGE = new LOVElement( "COULEUR", "r", "ROUGE", "Rouge", "3", "#FFFFFF", "#FF0000", "Red");
    public static LOVElement SANS = new LOVElement( "COULEUR", "s", "SANS", "Sans", "4", "#000000", "#FFFFFF","Without" );

    public static LOVElement[] List = new LOVElement[]{ Couleur.VERT, Couleur.ORANGE, Couleur.JAUNE, Couleur.ROUGE, Couleur.SANS };
}
