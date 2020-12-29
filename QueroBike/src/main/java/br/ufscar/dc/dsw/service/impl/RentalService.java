package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.IRentalDAO;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.IRentalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class RentalService implements IRentalService {

    @Autowired
    IRentalDAO dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(Rental rental) {
        rental.setPassword(passwordEncoder.encode(rental.getPassword()));
        dao.save(rental);
    }

    @Transactional(readOnly = true)
    public Rental findById(int id) {
        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Rental> listAll() {
        return dao.findAll();
    }
}