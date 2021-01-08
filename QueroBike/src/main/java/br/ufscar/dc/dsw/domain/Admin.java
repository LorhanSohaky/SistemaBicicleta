package br.ufscar.dc.dsw.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin extends User<Integer> implements Serializable {

    public Admin() {
        super("admin");
    }

}
