package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Auto.findAll", query = "select a from Auto as a")
})
@Table(name = "Auto")//test
@Getter
@Setter
public class Auto implements Serializable {

    public Auto(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String model;

    private float price;

    @ManyToMany (mappedBy = "autos")
    private List<Customer> renters;

    @OneToMany(mappedBy = "auto")
    private List<CustomerAuto> renter;
}
