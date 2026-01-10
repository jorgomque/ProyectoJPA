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

    public Assistant findByName(String name){
        String jpql = "SELECT a FROM Assistant a WHERE a.name=:name";
        Query qr = em.createQuery(jpql);
        qr.setParameter("name",name);
        List<Assistant>assistants = (List<Assistant>) qr.getResultList();
        if (assistants.isEmpty()){
            return null;
        }
        return assistants.get(0);
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
