package services;

import interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@SessionScoped
@Named
@Getter
@Setter
public class PriceCalc implements Serializable {

    float calculated;
    float price;

    @LoggedInvocation
    public String calculate()
    {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        float discount = Float.parseFloat(requestParameters.get("discount"));
        calculated = price - (price/100*discount);
        System.out.println("Check This " + calculated);
        return "players?faces-redirect=true&customerId=" + requestParameters.get("customerId");
    }
}
