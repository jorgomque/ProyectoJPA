package DAMAccesoADatos.repository;


import DAMAccesoADatos.entity.Assistant;
import DAMAccesoADatos.entity.Letter;
import jakarta.persistence.EntityManager;
import java.util.List;

public class LetterRepository {

    private final EntityManager em;

    public LetterRepository(EntityManager em) {
        this.em = em;
    }


    public void save(Letter letter){
        em.persist(letter);
    }

    public Letter fingById(Integer id){
        return em.find(Letter.class, id);
    }

    public List<Letter> findAll(){
        return em.createQuery("Select l from Letter l", Letter.class).getResultList();
    }

    public void removeAssistantFromLetters(Assistant assistant){
        em.createQuery("update Letter  l set l.assistant = null where l.assistant = :assistant")
                .setParameter("assistant", assistant).executeUpdate();
    }


}
