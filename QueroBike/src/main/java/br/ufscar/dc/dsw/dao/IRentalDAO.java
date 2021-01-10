package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Rental;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IRentalDAO extends CrudRepository<Rental, Integer> {

    Rental findById(int id);

    @Override
    List<Rental> findAll();

    @Override
    Rental save(Rental rental);

    @Query("SELECT r FROM Rental r WHERE r.email = :email")
    public Rental getUserByEmail(@Param("email") String email);
}
