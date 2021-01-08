package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Admin;
import java.util.List;

public interface IAdminService {
    List<Admin> listAll();
    void save(Admin admin);
}
