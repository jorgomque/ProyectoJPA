package DAMAccesoADatos.repository;




import DAMAccesoADatos.entity.Gift;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class GiftRepository {

    private final EntityManager em;

    public GiftRepository(EntityManager em) {
        this.em = em;
    }


    public void save(Gift assistant){
        em.persist(assistant);
    }

    public Gift fingById(Integer id){
        return em.find(Gift.class, id);
    }



    public List<Gift> findAll(){
        return em.createQuery("Select g from Gift g", Gift.class).getResultList();
    }

    public List<Gift> findByString(String chars){
        String jpql = "SELECT g FROM Gift g WHERE g.description like :chars";
        Query qr = em.createQuery(jpql);
        qr.setParameter("chars", "%" + chars + "%");
        return (List<Gift>) qr.getResultList();
    }

    public List<Gift> findByCity(String city){
        String jpql = "SELECT  g FROM Gift g JOIN g.letterGiftSet lg JOIN lg.letter l WHERE l.city = :city";
        Query qr = em.createQuery(jpql);
        qr.setParameter("city", city);
        return (List<Gift>) qr.getResultList();
    }


    public void updateMinimumAge(Integer id, Integer age){
        Gift gift = em.find(Gift.class, id);
        gift.setMinimumAge(age);
        em.merge(gift);

    }

    public void updateDescription(Integer id, String description){
        Gift gift = em.find(Gift.class, id);
        gift.setDescription(description);
        em.merge(gift);

    }



}

