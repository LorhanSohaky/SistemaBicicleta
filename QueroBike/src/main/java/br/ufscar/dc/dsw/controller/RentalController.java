package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.IRentalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class RentalController {

    @Autowired
    private IRentalService rentalService;

    private boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    @PostMapping("locadoras")
    public ResponseEntity<Rental> createRental(@RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Gson gson = new Gson();
                Rental rental = gson.fromJson(json.toString(), Rental.class);
                rentalService.save(rental);
                return ResponseEntity.ok(rental);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @PutMapping("locadoras/{id}")
    public ResponseEntity<Rental> updateRental(@RequestBody JSONObject json, @PathVariable("id") int id) {
        Rental rental = rentalService.findById(id);
        try {
            if (isJSONValid(json.toString())) {
                Gson gson = new Gson();

                Rental newRental = gson.fromJson(json.toString(), Rental.class);
                
                rental.setCity(newRental.getCity());
                rental.setCnpj(newRental.getCnpj());
                rental.setComplement(newRental.getComplement());
                rental.setDescription(newRental.getDescription());
                rental.setEmail(newRental.getEmail());
                rental.setName(newRental.getName());
                rental.setNeighborhood(newRental.getNeighborhood());
                rental.setPassword(newRental.getPassword());
                rental.setPostalCode(newRental.getPostalCode());
                rental.setStreetName(newRental.getStreetName());
                rental.setStreetNumber(newRental.getStreetNumber());

                rentalService.save(rental);
                return ResponseEntity.ok(rental);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @GetMapping("/locadoras")
    public ResponseEntity<List<Rental>> listRentals() {
        List<Rental> rentals = rentalService.listAll();

        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/locadoras/{id}")
    public ResponseEntity<Rental> getRental(@PathVariable("id") int id) {

        Rental rental = rentalService.findById(id);

        return ResponseEntity.ok(rental);
    }

    @DeleteMapping("/locadoras/{id}")
    public ResponseEntity<Boolean> deleteRental(@PathVariable("id") int id) {
        Rental rental = rentalService.findById(id);
        if (rental == null) {
            return ResponseEntity.notFound().build();
        } else {
            rentalService.delete(rental);
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/locadoras/cidades/{name}")
    public ResponseEntity<List<Rental>> listRentals(@PathVariable("name") String name) {
        List<Rental> rentals = rentalService.listAllFromCity(name);

        return ResponseEntity.ok(rentals);
    }
}
