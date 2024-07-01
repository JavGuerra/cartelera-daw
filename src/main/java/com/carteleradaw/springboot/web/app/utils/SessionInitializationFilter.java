package com.carteleradaw.springboot.web.app.utils;

import com.carteleradaw.springboot.web.app.services.IAddressService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class SessionInitializationFilter implements Filter {

    private final IAddressService addressService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

            if (session.getAttribute("selectedCity") == null)
                session.setAttribute("selectedCity", "");

            if (session.getAttribute("citiesNames") == null)
                session.setAttribute("citiesNames", addressService.getCitiesNames());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
