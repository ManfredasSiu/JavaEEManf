package usecases;

import entities.Customer;
import entities.Team;
import interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;
import persistence.CustomersDAO;
import persistence.TeamsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Customers {
    @Inject
    private CustomersDAO customersDAO;

    @Getter
    @Setter
    private Customer customerToCreate = new Customer();

    @Getter
    private List<Customer> allCustomers;

    @PostConstruct
    public void init(){
        loadAllTeams();
    }

    @Transactional
    @LoggedInvocation
    public String createCustomer(){
        this.customersDAO.persist(customerToCreate);
        return "index?faces-redirect=true";
    }


    private void loadAllTeams(){
        this.allCustomers = customersDAO.loadAll();
    }
}
