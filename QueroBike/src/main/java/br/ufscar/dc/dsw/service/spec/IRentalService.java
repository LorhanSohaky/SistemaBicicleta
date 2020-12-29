package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Rental;
import java.util.List;

public interface IRentalService {
    List<Rental> listAll();
    void save(Rental rental);
}
