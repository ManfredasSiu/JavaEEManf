package persistence;

import entities.Customer;
import entities.CustomerAuto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CustomerAutoDAO {

    @Inject
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(CustomerAuto customer){
        this.em.persist(customer);
    }

    public CustomerAuto update(CustomerAuto customerAuto){
        return em.merge(customerAuto);
    }
}
