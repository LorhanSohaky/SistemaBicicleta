package br.ufscar.dc.dsw.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.domain.Customer;
import br.ufscar.dc.dsw.domain.Reserve;

public interface IReserveService {

    List<Reserve> findByCustomer(Customer customer);

    List<Reserve> findByRental(Rental rental);

    void save(Reserve reserve);

}
