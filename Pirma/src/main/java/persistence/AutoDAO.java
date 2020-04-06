package persistence;

import entities.Auto;
import entities.Customer;
import entities.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class AutoDAO {
    @Inject
    private EntityManager em;

    public void persist(Auto auto){
        this.em.persist(auto);
    }

    public Auto findOne(Integer id) {
        return em.find(Auto.class, id);
    }

    public List<Auto> loadAll() {
        return em.createNamedQuery("Auto.findAll", Auto.class).getResultList();
    }
}
