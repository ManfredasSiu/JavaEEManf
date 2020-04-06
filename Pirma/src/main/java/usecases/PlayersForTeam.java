package usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import entities.Player;
import lombok.Getter;
import lombok.Setter;
import entities.Team;
import persistence.PlayersDAO;
import persistence.TeamsDAO;

@Model
public class PlayersForTeam implements Serializable {

    @Inject
    private TeamsDAO teamsDAO;

    @Inject
    private PlayersDAO playersDAO;

    @Getter @Setter
    private Player playerToCreate = new Player();

    @Getter @Setter
    private Team team;

    @Transactional
    public String createPlayer() {
        playerToCreate.setTeam(this.team);
        playersDAO.persist(playerToCreate);
        return "players?faces-redirect=true&teamId=" + this.team.getId();
    }

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer teamId = Integer.parseInt(requestParameters.get("teamId"));
        this.team = teamsDAO.findOne(teamId);
    }
}