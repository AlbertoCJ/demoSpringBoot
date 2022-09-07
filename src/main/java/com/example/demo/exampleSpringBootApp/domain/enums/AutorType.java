package com.example.demo.exampleSpringBootApp.domain.enums;

public enum AutorType {
    CASUAL,DEDICATED;

    public static String getRegExp() {
        return "CASUAL|DEDICATED";
    }

}
