package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.IReserveDAO;
import br.ufscar.dc.dsw.domain.Customer;
import br.ufscar.dc.dsw.domain.Reserve;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.IReserveService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class ReserveService implements IReserveService {

    @Autowired
    IReserveDAO dao;

    @Transactional(readOnly = true)
    @Override
    public List<Reserve> findByCustomer(Customer customer) {
        return dao.findByCustomer(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Reserve> findByRental(Rental rental) {
        return dao.findByRental(rental);
    }

    @Override
    public void save(Reserve reserve) {
        dao.save(reserve);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Reserve> listAll() {
        return dao.findAll();
    }

    @Override
    public Reserve findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void delete(Reserve reserve) {
        dao.delete(reserve);
    }
}
