package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.service.spec.*;

import br.ufscar.dc.dsw.service.spec.IReserveService;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ReserveController {

    @Autowired
    private IReserveService reserveService;

    @Autowired
    private IRentalService rentalService;

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/locacoes")
    public ResponseEntity<List<Reserve>> listReserves() {
        List<Reserve> reserves = reserveService.listAll();

        return ResponseEntity.ok(reserves);
    }

    @GetMapping("/locacoes/{id}")
    public ResponseEntity<Reserve> getReserve(@PathVariable("id") int id) {

        Reserve reserve = reserveService.findById(id);

        return ResponseEntity.ok(reserve);
    }

    @GetMapping("/locacoes/locadoras/{id}")
    public ResponseEntity<List<Reserve>> getReserveFromRental(@PathVariable("id") int id) {
        Rental rental = rentalService.findById(id);
        if (rental == null) {
            return ResponseEntity.notFound().build();
        } else {
            List<Reserve> reserves = reserveService.findByRental(rental);

            return ResponseEntity.ok(reserves);
        }
    }

    @GetMapping("/locacoes/clientes/{id}")
    public ResponseEntity<List<Reserve>> getReserveFromCustomer(@PathVariable("id") int id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        } else {
            List<Reserve> reserves = reserveService.findByCustomer(customer);

            return ResponseEntity.ok(reserves);
        }
    }
}
