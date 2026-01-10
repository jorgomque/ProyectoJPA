package DAMAccesoADatos.service;


import DAMAccesoADatos.entity.Kid;
import DAMAccesoADatos.entity.Letter;
import DAMAccesoADatos.repository.KidRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
public class KidService {
    private final EntityManagerFactory emf;

    public KidService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Kid createKid(Kid kid, Letter letter, Integer assistantId, List<Integer> giftsId, List<Integer> quantityOfGifts){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Kid kid1 = new Kid(kid.getName(), kid.getSurname(), letter);
        try {
            tx.begin();
            KidRepository repo = new KidRepository(em);
            repo.save(kid);
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
        return kid1;
    }
}
