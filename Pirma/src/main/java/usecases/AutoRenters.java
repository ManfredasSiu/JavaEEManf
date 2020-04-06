package usecases;

import entities.Auto;
import entities.Customer;
import lombok.Getter;
import lombok.Setter;
import persistence.AutoDAO;
import persistence.CustomersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class AutoRenters {
    @Inject
    private CustomersDAO customersDAO;

    @Inject
    private AutoDAO autoDAO;

    @Getter @Setter
    private Auto auto;

    @Getter @Setter
    private Integer id;

    @Transactional
    public String AddAuto(){
        return "autos?faces-redirect=true&autoId=" + this.auto.getId();
    }

    @PostConstruct
    private void init (){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer autoId = Integer.parseInt(requestParameters.get("autoId"));
        this.auto = autoDAO.findOne(autoId);
    }
}
