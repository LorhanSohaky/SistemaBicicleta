package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Admin;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IAdminDAO extends CrudRepository<Admin, Integer> {

    Admin findById(int id);

    List<Admin> findAll();

    Admin save(Admin admin);
}
