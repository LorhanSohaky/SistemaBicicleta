package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.ICustomerDAO;
import br.ufscar.dc.dsw.domain.Customer;
import br.ufscar.dc.dsw.service.spec.ICustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class CustomerService implements ICustomerService {

    @Autowired
    ICustomerDAO dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        dao.save(customer);
    }

    @Transactional(readOnly = true)
    public Customer findById(int id) {
        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Customer> listAll() {
        return dao.findAll();
    }
}
