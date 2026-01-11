package DAMAccesoADatos.repository;

import DAMAccesoADatos.entity.Assistant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class AssistantRepository {

    private final EntityManager em;

    public AssistantRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Assistant assistant){
        em.persist(assistant);
    }

    public Assistant fingById(Integer id){
        return em.find(Assistant.class, id);
    }

    public List<Assistant> findAll(){
        return em.createQuery("Select a from Assistant a", Assistant.class).getResultList();
    }

    public void delete(Assistant assistant){
        Assistant managed = assistant;

        if (!em.contains(assistant)) {
            managed = em.merge(assistant);
        }
        em.remove(managed);
    }

}
