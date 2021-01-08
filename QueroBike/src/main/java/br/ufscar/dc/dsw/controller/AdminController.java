package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.IRentalService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admins")
public class AdminController {
    
    @Autowired
    private IRentalService rentalService;

    @GetMapping("/login")
    public String renderLogin(ModelMap model) {
        return "admin/login";
    }

    @GetMapping("/home")
    public String renderHome(ModelMap model, Principal principal) {
        return "admin/home";
    }
    
    @GetMapping("/rentals")
    public String renderRentalsList(ModelMap model) {
        List<Rental> rentals = rentalService.listAll();
        model.addAttribute("rentals", rentals);
        return "admin/rentals/list";
    }
}
