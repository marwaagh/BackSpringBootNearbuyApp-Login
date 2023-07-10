package com.example.BackSpringBoot.LOVElements;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FilterContext implements Serializable {

    public final static int FILTER_EQ = 0;
    public final static int FILTER_LT = 1;
    public final static int FILTER_GT = 2;
    public final static int FILTER_LE = 3;
    public final static int FILTER_GE = 4;
    public final static int FILTER_LIKE = 5;
    public final static int FILTER_NEQ = 6;
    public final static int FILTER_NLIKE = 7;
    public final static int FILTER_IN = 8;
    public final static int FILTER_SQL = 9;

    private String filterId;
    private String propertyName;
    private String propertyValue;
    private int filterType;
    private boolean systemFilter = false;
    private String filterDataType;
}
