package com.carteleradaw.springboot.web.app.entities.enums;

import lombok.RequiredArgsConstructor;

/**
 * tipo de género.
 */
@RequiredArgsConstructor
public enum Genre {

    COMEDY("Comedia"),
    DRAMA("Drama"),
    ACTION("Acción"),
    SCI_FI("Ci-Fi"),
    FANTASY("Fantasia"),
    MUSICAL("Musical"),
    TERROR("Horror"),
    SUSPENSE("Suspense"),
    ROMANCE("Romance"),
    CHILDISH("Infantil"),
    BIOGRAPHICAL("Biografía"),
    FAMILY("Familiar"),
    DOCUMENTARY("Documental");

    private final String genre;

    @Override
    public String toString() { return genre; }
}