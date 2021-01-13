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
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private IRentalService rentalService;

    @GetMapping("/")
    public String listRentals(ModelMap model) {
        List<Rental> rentals = rentalService.listAll();
        model.addAttribute("rentals", rentals);

        return "rental/list";
    }

    @GetMapping("/home")
    public String renderHome(ModelMap model, Principal principal) {
        return "rental/home";
    }
}
