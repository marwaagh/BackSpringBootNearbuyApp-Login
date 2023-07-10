package com.example.BackSpringBoot.LOVElements;

public class TypeComposant
{
    public static LOVElement ACTIF = new LOVElement( "TYPE_COMPOSANT", "a", "ACTIF", "Actif", "0" ,"Active");
    public static LOVElement PASSIF = new LOVElement( "TYPE_COMPOSANT", "p", "PASSIF", "Passif", "1","Passive" );
    public static LOVElement PARTICULIER = new LOVElement( "TYPE_COMPOSANT", "t", "PARTICULIER", "Particulier", "2","Particular" );
    public static LOVElement COTS = new LOVElement( "TYPE_COMPOSANT", "c", "COTS", "Cots", "3","COTS" );
    public static LOVElement MECANIQUE = new LOVElement( "TYPE_COMPOSANT", "m", "MECANIQUE", "Mécanique Standard", "4","Mechanical" );

    public static LOVElement[] List = new LOVElement[]{ TypeComposant.ACTIF, TypeComposant.PASSIF, TypeComposant.PARTICULIER, TypeComposant.COTS, TypeComposant.MECANIQUE };
}
