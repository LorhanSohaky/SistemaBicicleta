package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Rental;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IRentalDAO extends CrudRepository<Rental, Integer> {

    Rental findById(int id);

    List<Rental> findAll();

    Rental save(Rental rental);
}
