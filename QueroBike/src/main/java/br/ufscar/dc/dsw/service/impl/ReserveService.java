package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.IReservelDAO;

import br.ufscar.dc.dsw.domain.Customer;
import br.ufscar.dc.dsw.domain.Reserve;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.spec.IReservelService;

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
    public List<Reserve> findByCustomer(Customer customer) {
        return dao.findByCustomer(customer);
    }

    @Transactional(readOnly = true)
    public List<Reserve> findByRental(Rental rental) {
        return dao.findByRental(rental);
    }
    
    public void save(Locacao locacao) {
        dao.save(locacao);
    }
}
