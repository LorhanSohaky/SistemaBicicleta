// FROM https://github.com/delanobeder/DSW1/blob/master/Modulo08/LivrariaMVC-v3/src/main/java/br/ufscar/dc/dsw/config/WebSecurityConfig.java
package br.ufscar.dc.dsw.config;

import br.ufscar.dc.dsw.security.AbstractUserDetailsServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(2)
public class RentalSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login/**", "/webjars/**", "/resources/**", "/assets/**","/rental/processing").permitAll()
                .antMatchers("/rental/home").hasRole("rental")
                .and()
                .formLogin()
                .loginPage("/rentals/login")
                .loginProcessingUrl("/rental/processing")
                .failureUrl("/rentals/login?error=loginError")
                .defaultSuccessUrl("/rentals/home")
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }
}
