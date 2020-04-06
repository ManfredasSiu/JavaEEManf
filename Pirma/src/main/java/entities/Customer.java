package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "select c from Customer as c")
})
@Table(name = "Customer")
@Getter
@Setter
public class Customer implements Serializable {

    public Customer(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullname;

    @ManyToMany
    @JoinTable(
            name = "customer_auto",
            joinColumns = @JoinColumn(name ="customer_id"),
            inverseJoinColumns = @JoinColumn(name = "auto_id")
    )
    private List<Auto> autos;

    @OneToMany(mappedBy = "customer")
    private List<CustomerAuto> rented;
}
