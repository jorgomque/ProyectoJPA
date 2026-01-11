package DAMAccesoADatos.service;


import DAMAccesoADatos.entity.Gift;
import DAMAccesoADatos.entity.Kid;
import DAMAccesoADatos.entity.Letter;
import DAMAccesoADatos.entity.LetterGift;
import DAMAccesoADatos.repository.KidRepository;
import DAMAccesoADatos.repository.LetterRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
public class KidService {
    private final EntityManagerFactory emf;

    public KidService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Kid createKid(Kid kid, Letter letter,  List<Integer> giftsId, List<Integer> quantityOfGifts){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        //the letter includes the assistant
        kid.setLetter(letter);
        letter.setKid(kid);
        //For each gift we add a line to the mid-table
        for (int i = 0; i < giftsId.size(); i++) {
            Gift gift = em.find(Gift.class, giftsId.get(i));
            LetterGift lg = new LetterGift(quantityOfGifts.get(i), letter, gift);
            letter.getLetterGiftSet().add(lg);
        }

        try {
            tx.begin();
            KidRepository kidRepo = new KidRepository(em);
            LetterRepository letterRepo = new LetterRepository(em);
            kidRepo.save(kid);
            letterRepo.save(letter);
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
        return kid;
    }
}
