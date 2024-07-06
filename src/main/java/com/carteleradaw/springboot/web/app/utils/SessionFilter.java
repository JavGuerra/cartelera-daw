package com.carteleradaw.springboot.web.app.utils;

import com.carteleradaw.springboot.web.app.services.IAddressService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class SessionFilter implements Filter {

    private final IAddressService addressService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(true);

        if (session!= null) {
            // Gestión de ciudad
            if (session.getAttribute("selectedCity") == null)
                session.setAttribute("selectedCity", "");

            if (session.getAttribute("citiesNames") == null)
                session.setAttribute("citiesNames", addressService.getCitiesNames());

            // Gestión de cookie
            if (session.getAttribute("cookieWarning") == null)
                session.setAttribute("cookieWarning", true);

            // Gestión de notificaciones
            if (session.getAttribute("message") == null)
                session.setAttribute("message", "");

            if (session.getAttribute("messageType") == null)
                session.setAttribute("messageType", "");
                // Valores: "danger", otro cualquiera = "info".

            if (session.getAttribute("messageActivated") == null)
                session.setAttribute("messageActivated", false);

            if ((boolean) session.getAttribute("messageActivated")) {
                session.setAttribute("message", "");
                session.setAttribute("messageActivated", false);
            }

            if (!Objects.equals((String) session.getAttribute("message"), ""))
                session.setAttribute("messageActivated", true);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
