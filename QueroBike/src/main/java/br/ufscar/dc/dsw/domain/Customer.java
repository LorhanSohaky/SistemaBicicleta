package br.ufscar.dc.dsw.domain;

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
    private String CPF;

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
    private List<Reserve> reserve;

    public Customer() {
        super("customer");
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
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

    public List<Reserve> getReserve() {
        return reserve;
    }

    public void setReserve(List<Reserve> reserve) {
        this.reserve = reserve;
    }
}
