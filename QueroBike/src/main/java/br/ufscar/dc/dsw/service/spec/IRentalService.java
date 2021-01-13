package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Rental;
import java.util.List;

public interface IRentalService {

    List<Rental> listAll();

    Rental findById(int id);

    void save(Rental rental);
    void delete(Rental rental);

    List<Rental> listAllFromCity(String cityName);
}
