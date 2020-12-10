package br.ufscar.dc.dsw.domain;

import java.util.Date;

public class Reserve {

    private int id;
    private Rental rental;
    private Customer customer;
    private Date moment;

    public Reserve(Rental rental, Customer customer, Date moment) {
        this.rental = rental;
        this.customer = customer;
        this.moment = moment;
    }

    public Reserve(int id, Rental rental, Date moment) {
        this.id = id;
        this.rental = rental;
        this.moment = moment;
    }

    public Reserve(int id, Customer customer, Date moment) {
        this.id = id;
        this.customer = customer;
        this.moment = moment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

}
