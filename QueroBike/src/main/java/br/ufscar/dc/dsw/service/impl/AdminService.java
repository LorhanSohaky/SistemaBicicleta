package br.ufscar.dc.dsw.service.impl;

import br.ufscar.dc.dsw.dao.IAdminDAO;
import br.ufscar.dc.dsw.domain.Admin;
import br.ufscar.dc.dsw.service.spec.IAdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class AdminService implements IAdminService {

    @Autowired
    IAdminDAO dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        dao.save(admin);
    }

    @Transactional(readOnly = true)
    public Admin findById(int id) {
        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Admin> listAll() {
        return dao.findAll();
    }
}
