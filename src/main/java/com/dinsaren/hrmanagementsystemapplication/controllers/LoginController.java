package com.dinsaren.hrmanagementsystemapplication.controllers;

import com.dinsaren.hrmanagementsystemapplication.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, @RequestParam(value = "status", required = false, defaultValue = "") Integer status, User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/";
        }

        if (status == null) {
            model.addAttribute("error_status", 0);
        } else {
            model.addAttribute("error_status", 1);
        }
        return "login";
    }
}
