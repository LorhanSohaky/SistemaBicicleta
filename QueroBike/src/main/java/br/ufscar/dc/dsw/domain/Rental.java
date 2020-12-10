package br.ufscar.dc.dsw.domain;

public class Rental {

    private int id;
    private String name;
    private String cnpj;
    private String email;
    private String password;
    private String salt;
    private String description;
    private String postalCode;
    private String streetName;
    private String neighborhood;
    private String complent;
    private String streetNumber;
    private City city;

    public Rental(int id) {
        this.id = id;
    }

    public Rental(int id, String name, String cnpj, String description, String postalCode, String streetName, String neighborhood, String complent, String streetNumber, City city) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.description = description;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.neighborhood = neighborhood;
        this.complent = complent;
        this.streetNumber = streetNumber;
        this.city = city;
    }

    public Rental(int id, String name, String cnpj, String description, String postalCode, String streetName, String neighborhood, String complent, String streetNumber, String email, City city) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.description = description;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.neighborhood = neighborhood;
        this.complent = complent;
        this.streetNumber = streetNumber;
        this.city = city;
        this.email = email;
    }

    public Rental(String name, String cnpj, String description, String postalCode, String streetName, String neighborhood, String complent, String streetNumber, String email, String password, String salt, City city) {
        this.name = name;
        this.cnpj = cnpj;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.description = description;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.neighborhood = neighborhood;
        this.complent = complent;
        this.streetNumber = streetNumber;
        this.city = city;
    }

    public Rental(int id, String name, String cnpj, String description, String postalCode, String streetName, String neighborhood, String complent, String streetNumber, String email, String password, String salt, City city) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.description = description;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.neighborhood = neighborhood;
        this.complent = complent;
        this.streetNumber = streetNumber;
        this.city = city;
    }

    public Rental(int it, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

}
