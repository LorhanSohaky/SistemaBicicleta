package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.IRentalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/login")
    public String login(ModelMap model) {
        return "rental/login";
    }
}
