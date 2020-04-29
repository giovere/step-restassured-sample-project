package com.swissbit.utils;

import java.io.FileInputStream;
import java.io.IOException;

public class Properties {

    public static String getProperty(String key) throws IOException {
        java.util.Properties prop = new java.util.Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }
}
