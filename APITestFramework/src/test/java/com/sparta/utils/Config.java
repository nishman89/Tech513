package com.sparta.utils;

import java.util.ResourceBundle;

public class Config {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("config");

    public static String getBasePostcodeUri(){
        return  bundle.getString("postcodes.uri");
    }
}
