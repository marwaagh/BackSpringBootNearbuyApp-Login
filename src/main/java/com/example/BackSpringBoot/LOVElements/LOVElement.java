package com.example.BackSpringBoot.LOVElements;

public class LOVElement {
    public String domain;
    public String key;
    public String value;
    public String value2;
    public String value3;
    public String value4;
    public String fontColor;
    public String backColor;

    public LOVElement()
    {
    }



    public LOVElement( String domain, String key, String value, String value2, String value3 )
    {
        this.domain = domain;
        this.key = key;
        this.value = value;
        this.value2 = value2;
        this.value3 = value3;
    }
    public LOVElement( String domain, String key, String value, String value2, String value3 ,String value4 )
    {
        this.domain = domain;
        this.key = key;
        this.value = value;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
    }
    public LOVElement( String domain, String key, String value, String value2, String value3, String fontColor, String backColor )
    {
        this.domain = domain;
        this.key = key;
        this.value = value;
        this.value2 = value2;
        this.value3 = value3;
        this.fontColor = fontColor;
        this.backColor = backColor;
    }

    public LOVElement( String domain, String key, String value, String value2, String value3, String fontColor, String backColor, String value4)
    {
        this.domain = domain;
        this.key = key;
        this.value = value;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.fontColor = fontColor;
        this.backColor = backColor;
    }
    public static LOVElement getLovElementFromListAndKey( LOVElement[] list, String key )
    {
        LOVElement result = null;

        for (int i = 0; i < list.length; i++)
        {
            if( list[i].key.equals( key ) )
            {
                result = list[i];
                break;
            }
        }

        return result;
    }

    public static LOVElement getLovElementFromListAndvalue2( LOVElement[] list, String key )
    {
        LOVElement result = null;

        for (int i = 0; i < list.length; i++)
        {
            if( list[i].value2.equals( key ) )
            {
                result = list[i];
                break;
            }
        }

        return result;
    }
}
