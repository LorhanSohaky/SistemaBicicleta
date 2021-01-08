package br.ufscar.dc.dsw.domain;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "reserve")
public class Reserve {
    
    @NotBlank
    @Column(nullable = false, unique = false)
    private String day;
    
    @NotBlank
    @Column(nullable = false, unique = false)
    private int time;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
	private Customer customer;

    @ManyToOne
    @JoinColumn(name = "rental_id")
	private Rental rental;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
	this.rental = rental;
    }

    public String getDay() {
	return day;
    }

    public void setDay(String day) {
	this.day = day;
    }
	
    public int getTime() {
	return time;
    }

    public void setTime(int time) {
	this.time = time;
    }

}
