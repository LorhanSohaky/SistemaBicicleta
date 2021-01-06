package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.IRentalService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String renderLogin(ModelMap model) {
        return "rental/login";
    }

    @PostMapping("/login")
    public String login(@Valid Rental rental, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "rental/login";
        }
        
        System.out.println("password = " + rental.getPassword());
        
        rentalService.save(rental);
        
        return "redirect:/";
    }
}
