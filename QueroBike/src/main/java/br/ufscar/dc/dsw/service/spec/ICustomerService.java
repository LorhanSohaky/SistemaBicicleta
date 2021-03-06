package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Customer;
import java.util.List;

public interface ICustomerService {

    List<Customer> listAll();

    Customer findById(int id);

    void save(Customer customer);

    void delete(Customer customer);
}
