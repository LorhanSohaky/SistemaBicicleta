package br.ufscar.dc.dsw.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "rental")
public class Rental extends User<Integer> implements Serializable {

    @NotBlank
    @Size(min = 14, max = 14)
    @Column(nullable = false, length = 14)
    private String cnpj;

    @Size(max = 256)
    @Column(nullable = true, length = 256)
    private String description;

    @NotBlank
    @Size(min = 8, max = 8)
    @Column(nullable = false, length = 8)
    private String postalCode;

    @NotBlank
    @Size(min = 2, max = 128)
    @Column(nullable = false, length = 128)
    private String streetName;

    @NotBlank
    @Size(min = 2, max = 128)
    @Column(nullable = false, length = 128)
    private String neighborhood;

    @Size(min = 2, max = 128)
    @Column(nullable = true, length = 128)
    private String complement;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(nullable = false, length = 128)
    private String streetNumber;

    @ManyToOne
    @JoinColumn(name = "fk_city", referencedColumnName = "id")
    private City city;

    public Rental() {
        super("rental");
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
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
        return "Rental{" + "id=" + this.getId() + ", name=" + this.getName() + ", cnpj=" + cnpj + '}';
    }
}
