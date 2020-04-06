package persistence;

import entities.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class PlayersDAO {
    @Inject
    private EntityManager em;

    public void persist(Player player){
        this.em.persist(player);
    }
}