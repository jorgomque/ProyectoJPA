package DAMAccesoADatos.service;

import DAMAccesoADatos.entity.Assistant;
import DAMAccesoADatos.repository.AssistantRepository;
import DAMAccesoADatos.repository.GiftRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class AssistantService {

    private final EntityManagerFactory emf;

    public AssistantService(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public Assistant createAssistant(String name){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Assistant assistant = new Assistant(name);
        try {
            tx.begin();
            AssistantRepository repo = new AssistantRepository(em);
            repo.save(assistant);
            tx.commit();
        }catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
        finally {
            em.close();
        }
        return assistant;
    }






    public List<Assistant> listAll(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Assistant> assistants;
        try{
            tx.begin();
            AssistantRepository repo = new AssistantRepository(em);
            assistants = repo.findAll();
            tx.commit();
        }catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
        finally {
            em.close();
        }
        return assistants;
    }
    public Assistant listById(Integer id){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Assistant assistant;
        try{
            tx.begin();
            AssistantRepository repo = new AssistantRepository(em);
            assistant = repo.fingById(id);
            tx.commit();
        }catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
        finally {
            em.close();
        }
        return assistant;
    }
}
