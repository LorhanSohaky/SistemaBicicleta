package br.ufscar.dc.dsw.security;

import br.ufscar.dc.dsw.dao.IRentalDAO;
import br.ufscar.dc.dsw.dao.IUserDAO;
import br.ufscar.dc.dsw.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AbstractUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IRentalDAO rentalDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = rentalDAO.getUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new AbstractUserDetails(user);
    }

}
