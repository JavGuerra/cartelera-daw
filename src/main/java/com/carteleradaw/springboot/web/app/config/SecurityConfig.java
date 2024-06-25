package com.carteleradaw.springboot.web.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de permisos de rutas.
 */
@Configuration
public class  SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Gestiona permisos en las peticiones de rutas.
     * @param http La petición.
     * @return La ruta.
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET,"/", "/favicon.ico", "/legal", "/privacy",
                    "/css/**", "/js/**", "/img/**", "/webjars/**").permitAll()

            .requestMatchers(HttpMethod.GET,"/help").authenticated()

            .requestMatchers(HttpMethod.GET,"/cinemas/create").authenticated()
            .requestMatchers(HttpMethod.GET,"/cinemas/{id}/edit").authenticated()
            .requestMatchers(HttpMethod.GET,"/cinemas/{id}/delete").authenticated()
            .requestMatchers(HttpMethod.GET,"/cinemas/**").permitAll()

            .requestMatchers(HttpMethod.GET,"/rooms/create").authenticated()
            .requestMatchers(HttpMethod.GET,"/rooms/{id}/edit").authenticated()
            .requestMatchers(HttpMethod.GET,"/rooms/{id}/delete").authenticated()
            .requestMatchers(HttpMethod.GET,"/rooms/**").permitAll()

            .requestMatchers(HttpMethod.GET,"/films/create").authenticated()
            .requestMatchers(HttpMethod.GET,"/films/{id}/edit").authenticated()
            .requestMatchers(HttpMethod.GET,"/films/{id}/delete").authenticated()
            .requestMatchers(HttpMethod.GET,"/films/**").permitAll()

            .requestMatchers(HttpMethod.GET,"/users/create").authenticated()
            .requestMatchers(HttpMethod.GET,"/users/{id}/edit").authenticated()
            .requestMatchers(HttpMethod.GET,"/users/{id}/delete").authenticated()
            .requestMatchers(HttpMethod.GET,"/users/**").authenticated()

            .anyRequest().permitAll()

            .and().formLogin().failureUrl("/login?error=true").defaultSuccessUrl("/")
            .and().logout().logoutSuccessUrl("/")
            .and().exceptionHandling().accessDeniedPage("/error")

            .and().build();
    }
}