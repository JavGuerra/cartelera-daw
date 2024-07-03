package com.carteleradaw.springboot.web.app.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.carteleradaw.springboot.web.app.utils.Utils.stringIsEmpty;

@Controller
public class messageController {


    @PostMapping("/message")
    public String messageAlert(@RequestParam(value = "returnUrl", required = false) String returnUrl,
                                    HttpSession session) {

        session.setAttribute("message", "");

        if (!stringIsEmpty(returnUrl)) {
            return "redirect:" + returnUrl;
        } else {
            return "redirect:/";
        }
    }

}
