package com.example.BackSpringBoot.LOVElements;

public class TypeArticle
{
    public static LOVElement COMPOSANT = new LOVElement( "TYPE_ARTICLE", "c", "COMPOSANT", "Composant", "0" ,"Component");
    public static LOVElement LISTE = new LOVElement( "TYPE_ARTICLE", "l", "LISTE", "Liste", "1","List" );
    public static LOVElement SERIE = new LOVElement( "TYPE_ARTICLE", "s", "SERIE", "Série", "2","Series" );

    public static LOVElement[] List = new LOVElement[]{ TypeArticle.COMPOSANT, TypeArticle.LISTE, TypeArticle.SERIE };


}
