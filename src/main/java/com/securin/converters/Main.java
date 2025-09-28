package com.securin.converters;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        XMLJSONConverterI convert= ConverterFactory.createXMLJSONConverter();
        File json = new File("src/main/java/com/securin/converters/input.json");
        File xml = new File("src/main/java/com/securin/converters/output.xml");
        convert.convertJSONtoXML(json,xml);
    }
}
