package com.carteleradaw.springboot.web.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.util.Collections;
import java.util.List;

/**
 * Configuración de permisos de rutas.
 */
@Configuration
@EnableWebSecurity
public class  SecurityConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomLogoutHandler customLogoutHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AnonymousAuthenticationToken anonymousAuthenticationToken() {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
        return new AnonymousAuthenticationToken("ANONYMOUS_USER", "ANONYMOUS_USER", authorities);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception {

        http
            //.csrf(Customizer.withDefaults())
            .csrf(csrf -> csrf.csrfTokenRepository(new HttpSessionCsrfTokenRepository()))

            .authorizeHttpRequests(authRequest -> authRequest
                .requestMatchers(HttpMethod.GET,"/", "/legal", "/privacy", "/login", "/help", "/error",
                        "/css/**", "/js/**", "/img/**", "/webjars/**", "/auth/**", "/favicon.ico").permitAll()

                .requestMatchers(HttpMethod.POST,"/setCity", "/cookie", "/logout").permitAll()

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

                .anyRequest().authenticated()
            )

            .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .successHandler(customAuthenticationSuccessHandler)
                .failureUrl("/login?error=true")
                //.defaultSuccessUrl("/", true) // Deshabilitado para que funcione el manejador personalizado.
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .addLogoutHandler(customLogoutHandler)
                //.logoutSuccessUrl("/login?logout=true")
                .permitAll()
            )

            .exceptionHandling(exceptionHandling -> exceptionHandling
                    .accessDeniedPage("/error"));

        return http.build();
    }

}