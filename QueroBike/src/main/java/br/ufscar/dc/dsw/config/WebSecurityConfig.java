// FROM https://github.com/delanobeder/DSW1/blob/master/Modulo08/LivrariaMVC-v3/src/main/java/br/ufscar/dc/dsw/config/WebSecurityConfig.java
package br.ufscar.dc.dsw.config;

import br.ufscar.dc.dsw.security.AbstractUserDetailsServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public Argon2PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new AbstractUserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                // Controladores REST    
                .antMatchers("/clientes", "/locadoras", "/locacoes").permitAll()
                .antMatchers("/clientes/{\\d+}", "/locadoras/{\\d+}").permitAll()
                .antMatchers("/locacoes/{\\d+}").permitAll()
                .antMatchers("/locadoras/cidades/{\\w+}").permitAll()
                .antMatchers("/locacoes/clientes/{\\d+}").permitAll()
                .antMatchers("/locacoes/locadoras/{\\d+}").permitAll()
                // Demais linhas
                .antMatchers("/rentals/home").hasAuthority("rental")
                .antMatchers("/customers/**").hasAuthority("customer")
                .antMatchers("/admins/**").hasAuthority("admin")
                .antMatchers("/", "/rentals/", "/login/**", "/webjars/**", "/resources/**", "/assets/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .failureUrl("/login?error=loginError")
                .defaultSuccessUrl("/logged")
                .and()
                .logout()
                .logoutSuccessUrl("/login");
    }
}
