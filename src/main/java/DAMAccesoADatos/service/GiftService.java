package DAMAccesoADatos.service;


import DAMAccesoADatos.entity.Gift;
import DAMAccesoADatos.entity.Kid;
import DAMAccesoADatos.entity.Letter;
import DAMAccesoADatos.repository.GiftRepository;
import DAMAccesoADatos.repository.KidRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class GiftService {

    private final EntityManagerFactory emf;

    public GiftService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Gift createGift(String name, String reference, int minimumAge, String description){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Gift gift = new Gift(name, reference, minimumAge, description);
        try {
            tx.begin();
            GiftRepository repo = new GiftRepository(em);
            repo.save(gift);
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
        return gift;
    }

    public void updateGiftAge(Integer id, Integer age){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            GiftRepository repo = new GiftRepository(em);
            repo.updateMinimumAge(id, age);
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

    }

    public void updateGiftAge(Integer id, String description){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            GiftRepository repo = new GiftRepository(em);
            repo.updateDescription(id, description);
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
    }

    public List<Gift> listByCity(String city){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Gift> gifts;
        try{
            tx.begin();
            GiftRepository repo = new GiftRepository(em);
            gifts = repo.findByCity(city);
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
        return gifts;
    }

    public List<Gift> listByString(String string){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Gift> gifts;
        try{
            tx.begin();
            GiftRepository repo = new GiftRepository(em);
            gifts = repo.findByString(string);
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
        return gifts;
    }

    public List<Gift> listAll(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Gift> gifts;
        try{
            tx.begin();
            GiftRepository repo = new GiftRepository(em);
            gifts = repo.findAll();
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
        return gifts;
    }
    public Gift listById(Integer id){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Gift gift;
        try{
            tx.begin();
            GiftRepository repo = new GiftRepository(em);
            gift = repo.fingById(id);
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
        return gift;
    }

}
