package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Customer;
import br.ufscar.dc.dsw.service.impl.CustomerService;
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
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    private boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Customer>> listCustomers() {
        List<Customer> customers = customerService.listAll();

        return ResponseEntity.ok(customers);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") int id) {

        Customer customer = customerService.findById(id);

        return ResponseEntity.ok(customer);

    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable("id") int id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        } else {
            customerService.delete(customer);
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/clientes")
    public ResponseEntity<Customer> createCustomer(@RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Gson gson = new Gson();
                Customer customer = gson.fromJson(json.toString(), Customer.class);
                customerService.save(customer);
                return ResponseEntity.ok(customer);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody JSONObject json, @PathVariable("id") int id) {
        Customer customer = customerService.findById(id);
        try {
            if (isJSONValid(json.toString())) {
                Gson gson = new Gson();
                Customer newCustomer = gson.fromJson(json.toString(), Customer.class);

                customer.setBirthdate(newCustomer.getBirthdate());
                customer.setCpf(newCustomer.getCpf());
                customer.setEmail(newCustomer.getEmail());
                customer.setGender(newCustomer.getGender());
                customer.setName(newCustomer.getName());
                customer.setPassword(newCustomer.getPassword());
                customer.setPhone(newCustomer.getPhone());

                customerService.save(customer);
                return ResponseEntity.ok(customer);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
}
