package br.ufscar.dc.dsw.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admins")
public class AdminController {

    @GetMapping("/login")
    public String renderLogin(ModelMap model) {
        return "admin/login";
    }

    @GetMapping("/home")
    public String renderHome(ModelMap model, Principal principal) {
        return "admin/home";
    }
}
