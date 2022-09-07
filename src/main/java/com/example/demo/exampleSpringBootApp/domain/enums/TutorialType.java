package com.example.demo.exampleSpringBootApp.domain.enums;

public enum TutorialType {
    PUBLIC,PRIVATE;

    public static String getRegExp() {
        return "PUBLIC|PRIVATE";
    }
}
