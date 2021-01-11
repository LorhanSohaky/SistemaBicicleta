package br.ufscar.dc.dsw.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @GetMapping("/home")
    public String renderHome(ModelMap model, Principal principal) {
        return "customer/home";
    }
}
