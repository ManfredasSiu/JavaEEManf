package usecases;

import entities.BussinesGroup;
import entities.Customer;
import interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;
import persistence.CustomersDAO;
import persistence.GroupDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Map;

@Model
@ViewScoped
public class CustomerGroups implements Serializable {

   @Inject
   private GroupDAO groupDAO;

   @Inject
   private CustomersDAO customersDAO;

   @Getter @Setter
   private Integer cusId;

   @Getter @Setter
   private BussinesGroup bussinesGroup;

    @PostConstruct
    void init (){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String str = requestParameters.get("groupId");
        System.out.println("Check This " + str);
        Integer customerId = Integer.parseInt(str);
        this.bussinesGroup = groupDAO.findOne(customerId);
    }

    @Transactional
    @LoggedInvocation
    public String AddCustomer(){
        Customer cus = customersDAO.findOne(cusId);
        cus.setGroup(bussinesGroup);
        try{
            customersDAO.update(cus);
        } catch (OptimisticLockException e) {
            return "groups?faces-redirect=true&groupId=" + this.bussinesGroup.getId() + "&error=optimistic-lock-exception";
        }

        return "groups?faces-redirect=true&groupId=" + this.bussinesGroup.getId();
    }
}
