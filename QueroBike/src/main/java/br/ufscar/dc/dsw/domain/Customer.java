package br.ufscar.dc.dsw.domain;

import java.util.Date;

public class Customer {

    private int id;
    private String email;
    private String password;
    private String salt;
    private String cpf;
    private String name;
    private String phone;
    private String gender;
    private Date birthdate;

    public Customer(int id) {
        this.id = id;
    }

    public Customer(String email, String password, String salt, String cpf, String name, String phone, String gender, Date birthdate) {
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.cpf = cpf;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public Customer(int id, String cpf, String name, String phone, String gender, Date birthdate, String email, String password, String salt) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.salt = salt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
