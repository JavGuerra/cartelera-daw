package com.carteleradaw.springboot.web.app.entities.enums;

public enum Gender {

    COMEDY("Comedia"),
    DRAMA("Drama"),
    ACTION("Acción"),
    SCI_FI("Ci-Fi"),
    FANTASY("Fantasia"),
    MUSICAL("Musical"),
    TERROR("Terror"),
    SUSPENSE("Suspense"),
    ROMANCE("Romántica"),
    CHILDISH("Infantil"),
    BIOGRAPHICAL("Biografía"),
    FAMILY("Familiar");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return gender;
    }
}