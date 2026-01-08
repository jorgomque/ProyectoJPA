package DAMAccesoADatos.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "kid")

public class Kid implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
    ---------Columnas---------
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kid")
    private Integer idKid;

    @Column(nullable = false, length = 50)
    private String name;


    @Column(nullable = false, length = 50)
    private String surname;

    @OneToOne
    @JoinColumn(name = "id_letter")
    private Letter letter;

    /*
----------Constructores--------------
    */
    public Kid() {
    }

    public Kid(String name, String surname, Letter letter) {
        this.name = name;
        this.surname = surname;
        this.letter = letter;
    }


    /*
----------- Getters y setters-------------
    */

    public Integer getIdKid() {
        return idKid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Kid{" +
                "idKid=" + idKid +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", letter=" + letter.getIdLetter() +
                '}';
    }
}
