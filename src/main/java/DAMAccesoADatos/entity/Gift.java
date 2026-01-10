package DAMAccesoADatos.entity;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "gift")

public class Gift implements  Serializable {
    /*
    ---------Columnas---------
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gift")
    private Integer idGift;

    @Column(nullable = false, length = 50)
    private String name;


    @Column(nullable = false, length = 50)
    private String reference;

    @Column(nullable = false, length = 3)
    private int minimumAge;

    @Column(nullable = false, length = 100)
    private String description;

    @OneToMany(mappedBy = "gift")
    private Set<LetterGift> letterGiftSet;

        /*
----------Constructores--------------
    */

    public Gift() {
    }

    public Gift(String name, String reference, int minimumAge, String description) {
        this.name = name;
        this.reference = reference;
        this.minimumAge = minimumAge;
        this.description = description;
    }

    /*
    ----------- Getters y setters--------------
     */

    public Integer getidGift() {
        return idGift;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "idKid=" + idGift +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}
