package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ICityDAO;
import br.ufscar.dc.dsw.dao.IRentalDAO;
import br.ufscar.dc.dsw.domain.City;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.IRentalService;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private IRentalService rentalService;

    @Autowired
    private ICityDAO cityDAO;

    @Autowired
    private IRentalDAO rentalDAO;

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

    @GetMapping("/rentals/register")
    public String renderRegisterRental(Rental rental) {
        return "admin/rentals/register";
    }

    @PostMapping("/rentals/register")
    public String registerRental(@Valid Rental rental, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "admin/rentals/register";
        }
        
        rentalService.save(rental);
        attr.addFlashAttribute("sucess", "Locadora cadastrada com sucesso");
        return "redirect:/admins/rentals";
    }

    @GetMapping("/rentals/delete")
    public String deleteRental(@RequestParam String id) {
        int intergerId = Integer.parseInt(id);
        rentalDAO.deleteById(intergerId);

        return "redirect:/admins/rentals";
    }

    @ModelAttribute("cities")
    public List<City> listCities() {
        return cityDAO.findAll();
    }
}
