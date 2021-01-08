package br.ufscar.dc.dsw.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User<ID extends Serializable> implements Serializable {

    @Id
    @GeneratedValue
    private ID id;

    @NotBlank
    @Size(min = 3, max = 128, message = "{size.user.name}")
    @Column(nullable = false, length = 128)
    private String name;

    @NotBlank
    @Email
    @Size(min = 2, max = 128, message = "{size.user.email}")
    @Column(nullable = false, length = 128)
    private String email;

    @NotBlank
    @Size(min = 5, max = 128, message = "{size.user.password}")
    @Column(nullable = false, length = 128)
    private String password;

    @NotBlank
    @Size(min = 1, max = 14, message = "{size.user.role}")
    @Column(nullable = false, length = 14)
    private String role;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public User(String role) {
        this.role = role;
    }

}
