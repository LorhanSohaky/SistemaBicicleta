package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ICityDAO;
import br.ufscar.dc.dsw.dao.ICustomerDAO;
import br.ufscar.dc.dsw.dao.IRentalDAO;
import br.ufscar.dc.dsw.domain.City;
import br.ufscar.dc.dsw.domain.Customer;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.ICustomerService;
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

    @Autowired
    private ICustomerDAO customerDAO;

    @Autowired
    private ICustomerService customerService;

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
        attr.addFlashAttribute("sucess", "Locadora cadastrada com sucesso!");
        return "redirect:/admins/rentals";
    }

    @GetMapping("/rentals/edit")
    public String renderEditRental(@RequestParam String id, ModelMap model) {
        int integerId = Integer.parseInt(id);
        model.addAttribute("rental", rentalDAO.findById(integerId));

        return "admin/rentals/edit";
    }

    @PostMapping("/rentals/edit")
    public String editRental(@Valid Rental rental, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "admin/rentals/edit";
        }

        rentalService.save(rental);
        attr.addFlashAttribute("sucess", "Locadora atualizada com sucesso!");
        return "redirect:/admins/rentals";
    }

    @GetMapping("/rentals/delete")
    public String deleteRental(@RequestParam String id) {
        int integerId = Integer.parseInt(id);
        rentalDAO.deleteById(integerId);

        return "redirect:/admins/rentals";
    }

    @GetMapping("/customers")
    public String renderCustomersList(ModelMap model) {
        List<Customer> customers = customerService.listAll();
        model.addAttribute("customers", customers);
        return "admin/customers/list";
    }

    @GetMapping("/customers/edit")
    public String renderEditCustomer(@RequestParam String id, ModelMap model) {
        int integerId = Integer.parseInt(id);
        model.addAttribute("customer", customerDAO.findById(integerId));

        return "admin/customers/edit";
    }

    @PostMapping("/customers/edit")
    public String editCustomer(@Valid Customer customer, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "admin/customers/edit";
        }

        customerService.save(customer);
        attr.addFlashAttribute("sucess", "Cliente atualizado com sucesso!");
        return "redirect:/admins/customers";
    }

    @GetMapping("/customers/delete")
    public String deleteCustomer(@RequestParam String id) {
        int integerId = Integer.parseInt(id);
        customerDAO.deleteById(integerId);

        return "redirect:/admins/customers";
    }

    @ModelAttribute("cities")
    public List<City> listCities() {
        return cityDAO.findAll();
    }
}
