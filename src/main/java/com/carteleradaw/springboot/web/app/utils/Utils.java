package com.carteleradaw.springboot.web.app.utils;

import com.carteleradaw.springboot.web.app.entities.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

public abstract class Utils {

    // Cinco dígitos o vacío
    public static final String POSTAL_CODE_PATTERN = "^(\\d{5})?$";
    // Una letra, 7 dígitos y una letra
    public static final String CIF_PATTERN = "^[a-zA-Z][0-9]{7}[a-zA-Z]$";
    // Línea 1 - http, https, con www y puerto y / final o no
    // Línea 2 - Nombre fichero y ext. o ruta, con sub ruta o no, con nombre de fichero y ext., permitiendo rutas relativas
    // Línea 3 - Parámetros de URL opcionales y / final o no
    public static final String URL_PATTERN =
            "^((http(s)?://)?(www\\.)?[\\w.-]+(:[0-9]+)?)?/?" +
            "(([\\w-\\s]+\\.)?[\\w-\\s]+(/?[\\w-\\s]+)*(/?[\\w-\\s]+\\.[\\w-\\s]+)?)?" +
            "(\\?[\\w-]+=[\\w-]+(&[\\w-]+=[\\w-]+)*)?/?$";
    // Prefijo con signo + opcional, un espacio opcional y 14 dígitos
    public static final String PHONE_PATTERN = "^((\\+[0-9]{1,3}(\\s)?)?[0-9]{1,14})?$";

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
        return inputDate.format(outputFormat);
    }

    /**
     * Comprueba si exta abierta alguna sesión de usuario.
     * @return true si está abierta la sesión / false en caso contrario
     */
    public static Boolean isAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication != null && !(authentication instanceof AnonymousAuthenticationToken));
    }

    /**
     * Devuelve el usuario de la sesión
     * @return El opcional de usuario autenticado, alternativamente, el opcional de usuario vacío.
     */
    public static Optional<User> userAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (isAuth()) return Optional.ofNullable((User) authentication.getPrincipal());
        return Optional.empty();
    }
}
