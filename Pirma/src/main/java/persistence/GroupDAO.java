package persistence;

import entities.BussinesGroup;
import entities.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class GroupDAO {

    @Inject
    private EntityManager em;

    public List<BussinesGroup> loadAll() {
        return em.createNamedQuery("BussinesGroup.findAll", BussinesGroup.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(BussinesGroup bussinesGroup){
        this.em.persist(bussinesGroup);
    }

    public BussinesGroup findOne(Integer id) {
        return em.find(BussinesGroup.class, id);
    }}