package br.ufscar.dc.dsw.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "rental")
public class Rental implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank
    @Size(min = 3, max = 128, message = "{size.rental.name}")
    @Column(nullable = false, length = 128)
    private String name;

    @NotBlank
    @Size(min = 14, max = 14, message = "{size.rental.cnpj}")
    @Column(nullable = false, length = 14)
    private String cnpj;

    @NotBlank
    @Email
    @Size(min = 2, max = 128, message = "{size.rental.email}")
    @Column(nullable = false, length = 128)
    private String email;

    @NotBlank
    @Size(min = 5, max = 128, message = "{size.rental.password}")
    @Column(nullable = false, length = 128)
    private String password;

    @Size(max = 256, message = "{size.rental.description}")
    @Column(nullable = true, length = 256)
    private String description;

    @NotBlank
    @Size(min = 8, max = 8, message = "{size.rental.postalCode}")
    @Column(nullable = false, length = 8)
    private String postalCode;

    @NotBlank
    @Size(min = 2, max = 128, message = "{size.rental.streetName}")
    @Column(nullable = false, length = 128)
    private String streetName;

    @NotBlank
    @Size(min = 2, max = 128, message = "{size.rental.neighborhood}")
    @Column(nullable = false, length = 128)
    private String neighborhood;

    @Size(min = 2, max = 128, message = "{size.rental.complent}")
    @Column(nullable = true, length = 128)
    private String complent;

    @NotBlank
    @Size(min = 1, max = 128, message = "{size.rental.streetNumber}")
    @Column(nullable = false, length = 128)
    private String streetNumber;

    @ManyToOne
    @JoinColumn(name = "fk_city", referencedColumnName = "id")
    private City city;
    
    public final String roleType = "rental";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getComplent() {
        return complent;
    }

    public void setComplent(String complent) {
        this.complent = complent;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Rental{" + "id=" + id + ", name=" + name + ", cnpj=" + cnpj + '}';
    }
}
