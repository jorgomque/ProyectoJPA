package DAMAccesoADatos.repository;


import DAMAccesoADatos.entity.Kid;
import jakarta.persistence.EntityManager;
import java.util.List;

public class KidRepository {
    private final EntityManager em;

    public KidRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Kid kid){
        em.persist(kid);
    }

    public Kid fingById(Integer id){
        return em.find(Kid.class, id);
    }

    public List<Kid> findAll(){
        return em.createQuery("Select k from Kid k", Kid.class).getResultList();
    }


}
