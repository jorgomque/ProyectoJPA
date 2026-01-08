package DAMAccesoADatos.entity;


import jakarta.persistence.*;
import java.io.Serializable;

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

        /*
----------Constructores--------------
    */

    public Gift() {
    }

    public Gift(String name, String reference) {
        this.name = name;
        this.reference = reference;
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

    @Override
    public String toString() {
        return "Gift{" +
                "idKid=" + idGift +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}
