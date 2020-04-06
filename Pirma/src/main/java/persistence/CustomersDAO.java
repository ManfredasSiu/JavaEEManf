package persistence;

import entities.Customer;
import entities.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CustomersDAO {

    @Inject
    private EntityManager em;

    public List<Customer> loadAll() {
        return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Customer customer){
        this.em.persist(customer);
    }

    public Customer update(Customer customer){
        return em.merge(customer);
    }

    public Customer findOne(Integer id) {
        return em.find(Customer.class, id);
    }
}
