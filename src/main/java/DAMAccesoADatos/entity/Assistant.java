package DAMAccesoADatos.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Assistant")
public class Assistant implements  Serializable{

    private static final long serialVersionUID = 1L;


    /*
    ---------Columnas---------
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assistant")
    private Integer idAssistant;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "assistant")
    private Set<Letter> letters;

    /*
    ----------Constructores--------------
     */

    public Assistant() {
    }

    public Assistant(String name, Set<Letter> letters) {
        this.name = name;
        this.letters = letters;
    }


    /*
    ----------- Getters y setters-------------
     */
    public Integer getIdAssistant() {
        return idAssistant;
    }

    public String getName() {
        return name;
    }

    public Set<Letter> getLetters() {
        return letters;
    }

    @Override
    public String toString() {
        return "Assistant{" +
                "idAssistant=" + idAssistant +
                ", name='" + name + '\'' +
                ", letters=" + letters +
                '}';
    }
}
