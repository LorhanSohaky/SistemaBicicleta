package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ICustomerDAO extends CrudRepository<Customer, Integer> {

    Customer findById(int id);

    @Override
    List<Customer> findAll();

    @Override
    Customer save(Customer customer);

    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    public Customer getUserByEmail(@Param("email") String email);
}
