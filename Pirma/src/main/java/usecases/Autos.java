package usecases;

import entities.Auto;
import entities.Customer;
import lombok.Getter;
import lombok.Setter;
import persistence.AutoDAO;
import persistence.CustomersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Autos {
    @Inject
    private AutoDAO autoDAO;

    @Getter
    @Setter
    private Auto autoToCreate = new Auto();

    @Getter
    private List<Auto> allAutos;

    @PostConstruct
    public void init(){
        loadAllAutos();
    }

    @Transactional
    public String createAuto(){
        this.autoDAO.persist(autoToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllAutos(){
        this.allAutos = autoDAO.loadAll();
    }
}
