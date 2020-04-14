package usecases;

import entities.Auto;
import entities.Customer;
import entities.CustomerAuto;
import entities.Team;
import interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;
import persistence.*;
import services.PriceCalc;

import javax.annotation.PostConstruct;
import javax.ejb.Init;
import javax.ejb.Local;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.swing.*;
import javax.transaction.Transactional;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Model
public class CustomerAutos {
    @Inject
    private CustomersDAO customersDAO;

    @Inject
    private CustomerAutoDAO customerAutoDAO;

    @Inject
    private AutoDAO autoDAO;

    @Getter @Setter
    private Customer customer;

    @Getter @Setter
    private Integer id;//hello

    @Getter @Setter
    private Integer year;

    @Getter @Setter
    private Integer month;

    @Getter @Setter
    private Integer day;

    LocalDate LD;

    @Transactional
    @LoggedInvocation
    public String AddAuto(){

        /*List<Auto> autos = customer.getAutos();
        autos.add(autoDAO.findOne(id));
        customer.setAutos(autos);
        customersDAO.update(customer);*/
        LD= LocalDate.of(year, month, day);
        CustomerAuto newCA = new CustomerAuto();
        Auto addtionalAuto = autoDAO.findOne(id);
        for( CustomerAuto DateCheck : addtionalAuto.getRenter()){
            if(DateCheck.getDate().equals(LD))
            {
                return "players?faces-redirect=true&customerId=" + this.customer.getId();
            }
        }
        newCA.setAuto(addtionalAuto);
        newCA.setCustomer(customer);

        newCA.setDate(LD);
        customerAutoDAO.persist(newCA);
        return "players?faces-redirect=true&customerId=" + this.customer.getId();
    }

    @PostConstruct
    private void init (){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer customerId = Integer.parseInt(requestParameters.get("customerId"));
        this.customer = customersDAO.findOne(customerId);
    }

    public List<CustomerAuto> past()
    {
        List<CustomerAuto> pastAutos = new ArrayList<CustomerAuto>();
        for (CustomerAuto CA : customer.getRented()) {
            if(CA.getDate().isBefore(LocalDate.now())) {
                if(this.customer.getGroup() != null)
                    CA.getAuto().setPrice(CA.getAuto().getPrice() - CA.getAuto().getPrice() * (this.customer.getGroup().getDiscount() / 100));
                pastAutos.add(CA);
            }
        }
        return pastAutos;
    }

    public List<CustomerAuto> future()
    {
        List<CustomerAuto> futureAutos = new ArrayList<CustomerAuto>();
        for (CustomerAuto CA : customer.getRented()) {
            if(CA.getDate().isAfter(LocalDate.now())) {
                if(this.customer.getGroup() != null)
                    CA.getAuto().setPrice(CA.getAuto().getPrice() - CA.getAuto().getPrice() * (this.customer.getGroup().getDiscount() / 100));
                futureAutos.add(CA);
            }
        }
        return futureAutos;
    }

    public List<CustomerAuto> today()
    {
        List<CustomerAuto> currentAutos = new ArrayList<CustomerAuto>();
        for (CustomerAuto CA : customer.getRented()) {
            if(CA.getDate().equals(LocalDate.now())) {
                if(this.customer.getGroup() != null)
                    CA.getAuto().setPrice(CA.getAuto().getPrice() - CA.getAuto().getPrice() * (this.customer.getGroup().getDiscount()/100));
                currentAutos.add(CA);
            }
        }
        return currentAutos;
    }

}
