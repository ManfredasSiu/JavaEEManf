package usecases;

import entities.BussinesGroup;
import entities.Team;
import interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;
import persistence.GroupDAO;
import persistence.TeamsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Groups {

    @Inject
    private GroupDAO groupDAO;

    @Getter
    @Setter
    private BussinesGroup bussinesGroupToCreate = new BussinesGroup();

    @Getter
    private List<BussinesGroup> allGroups;

    @PostConstruct
    public void init(){
        loadAllGroups();
    }

    @Transactional
    @LoggedInvocation
    public String createGroup(){
        this.groupDAO.persist(bussinesGroupToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllGroups(){
        this.allGroups = groupDAO.loadAll();
    }
}