package br.ufscar.dc.dsw.security;

import br.ufscar.dc.dsw.dao.IUserDAO;
import br.ufscar.dc.dsw.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AbstractUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Email:"+email);
        User user = userDAO.getUserByEmail(email);
        
        System.out.println("User:"+user);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new AbstractUserDetails(user);
    }

}
