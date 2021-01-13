package br.ufscar.dc.dsw.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "customer")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends User<Integer> implements Serializable {

    @NotBlank
    @Size(min = 11, max = 11)
    @Column(nullable = false, unique = true)
    private String cpf;

    @NotBlank
    @Size(min = 12, max = 13)
    @Column(nullable = false, unique = false)
    private String phone;

    @Size(min = 1, max = 10)
    @Column(nullable = false, unique = false)
    private String gender;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false, unique = false)
    private Date birthdate;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Reserve> reserves;

    public Customer() {
        super("customer");
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public List<Reserve> getReserves() {
        return reserves;
    }

    public void setReserves(List<Reserve> reserves) {
        this.reserves = reserves;
    }
}
