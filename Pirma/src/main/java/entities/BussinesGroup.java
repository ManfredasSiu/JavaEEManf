package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@NamedQueries({
        @NamedQuery(name = "BussinesGroup.findAll", query = "select k from BussinesGroup as k")
})
@Table(name = "BussinesGroup")
@Getter
@Setter
public class BussinesGroup {

    public BussinesGroup(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private float discount;

    @OneToMany(mappedBy = "group")
    private List<Customer> customers = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BussinesGroup team = (BussinesGroup) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
