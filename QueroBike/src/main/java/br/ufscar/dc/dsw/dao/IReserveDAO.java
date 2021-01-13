package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.domain.Customer;
import br.ufscar.dc.dsw.domain.Reserve;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IReserveDAO extends CrudRepository<Reserve, Integer> {

    Reserve findById(int id);

    @Override
    List<Reserve> findAll();

    List<Reserve> findByCustomer(Customer customer);

    List<Reserve> findByRental(Rental rental);

    @Override
    Reserve save(Reserve reserve);

}
