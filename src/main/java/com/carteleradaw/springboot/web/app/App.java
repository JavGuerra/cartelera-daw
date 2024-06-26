package com.carteleradaw.springboot.web.app;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Aplicación Cartelera DAW.
 * Proyecto final FPGS DAW.
 * Fecha de última modificación: junio 2024
 * Versión: 1.0
 *
 * @author Javier Guerra
 */
@RequiredArgsConstructor
@SpringBootApplication
public class App {

    public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(App.class, args);
	}
}
