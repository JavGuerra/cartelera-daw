package com.carteleradaw.springboot.web.app.utils;

public abstract class Utils {

    /**
     * Comprueba si la cadena introducida es válida.
     * @param str cadena.
     * @return true si es válido / false en caso contrario.
     */
    public static boolean stringIsEmpty (String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Compruba si el número introducido es un número positivo válido.
     * @param num número.
     * @return true si es válido / false en caso contrario.
     */
    public static boolean invalidPosNumber (Long num) {
        return num == null || num <= 0;
    }

    /**
     * Pone en mayúsculas la primera letra de una cadena.
     * @param str cadena,
     * @return La cadena formateada, alternativamente, la misma cadena recibida.
     */
    public static String firstCharUpercase (String str) {
        if (stringIsEmpty(str)) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
