package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.City;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ICityDAO extends CrudRepository<City, Integer> {

    City findById(int id);

    @Override
    List<City> findAll();

    @Override
    City save(City city);

    @Query("SELECT c FROM City c WHERE c.name = :name AND c.state = :state")
    public List<City> find(@Param("name") String name, @Param("state") String state);
}
