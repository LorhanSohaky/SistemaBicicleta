package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Admin;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IAdminDAO extends CrudRepository<Admin, Integer> {

    Admin findById(int id);

    @Override
    List<Admin> findAll();

    @Override
    Admin save(Admin admin);
}
