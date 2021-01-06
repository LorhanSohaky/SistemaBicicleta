package br.ufscar.dc.dsw.security;

import br.ufscar.dc.dsw.domain.Rental;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class RentalDetails implements UserDetails {

    private Rental rental;

    public RentalDetails(Rental rental) {
        this.rental = rental;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(rental.roleType);
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return rental.getPassword();
    }

    @Override
    public String getUsername() {
        return rental.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Rental getRental() {
        return rental;
    }

}
