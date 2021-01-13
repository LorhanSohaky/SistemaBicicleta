package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IUserDAO extends CrudRepository<User, Integer> {

    @Query("SELECT user FROM User user WHERE user.email = :email")
    public User getUserByEmail(@Param("email") String email);
}
