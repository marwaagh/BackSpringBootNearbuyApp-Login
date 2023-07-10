package com.example.BackSpringBoot.LOVElements;

public class OuiNonSans {

    public static LOVElement OUI = new LOVElement( "OUI_NON_SANS", "o", "OUI", "Oui", "0" ,"Yes");

    public static LOVElement NON = new LOVElement( "OUI_NON_SANS", "n", "NON", "Non", "1" ,"No");

    public static LOVElement SANS = new LOVElement( "OUI_NON_SANS", "s", "SANS", "Sans", "2","Without" );


    public static LOVElement[] List = new LOVElement[]{ OuiNonSans.OUI, OuiNonSans.NON, OuiNonSans.SANS };

}