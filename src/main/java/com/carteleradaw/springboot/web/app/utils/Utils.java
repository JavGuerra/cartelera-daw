package com.carteleradaw.springboot.web.app.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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

    /**
     * Formatea una fecha en el formato "dd de MMMM de yyyy".
     * @param inputDate La fecha en formato yyyy-MM-dd.
     * @return La fecha formateada como "dd de MMMM de yyyy".
     */
    public static String FormatDate(LocalDate inputDate) {
        DateTimeFormatter outputFormat =
                DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", Locale.getDefault());
        // Formatear la fecha de entrada al formato deseado
        return inputDate.format(outputFormat);
    }
}
