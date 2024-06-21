package com.carteleradaw.springboot.web.app.entities.enums;

/**
 * Tipo de clasificación.
 */
public enum Classification {

    ALL_AGES("TP"),
    OLDER7("+7"),
    OLDER12("+12"),
    OLDER16("+16"),
    OLDER18("+18");

    private final String classification;

    Classification(String classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return classification;
    }
}
