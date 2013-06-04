package com.tesco.tablet.library;

/**
 * Created by apple on 02/06/13.
 */
public class Constants {


    public static final String EMAIL;
    public static final String PASSWORD;
    public static final String SEARCHITEM;
    public static final String APPNAME;
    public static final String SUPERDEPARTMENT;
    public static final String DEPARTMENT;
    public static final String NOFAVOURITES;
    public static final String NOUSUALS;
    public static final String LASTORDER;

    // In Order to refresh the constant values to be used by other classes, we defined them in the following way
    // Refer http://stackoverflow.com/questions/1693091/public-static-final-variable-in-an-imported-java-class

    // User can update these parameters as per the Test Cases
    static
    {
        EMAIL = "llt33@hsc.com";
        PASSWORD = "qwerty";
        SEARCHITEM = "bananas";
        APPNAME = "Tesco";
        SUPERDEPARTMENT = "Fresh Food";
        DEPARTMENT = "Fresh Fruit";

    }

    // Variables used by different test classes
    static
    {
        NOFAVOURITES = "No products are available.";
        NOUSUALS = "No usual products";
        LASTORDER = "No products are available.";
    }

}
