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

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "id_letter")
    private Letter letter;

    /*
----------Constructores--------------
    */
    public Kid() {
    }

    public Kid(String name, String surname) {
        this.name = name;
        this.surname = surname;
        // the letter is setted with the setter
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

    public Letter getLetter() {
        return letter;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return "Xiquet: " + "id del xiquet: =" + idKid + " nom: " + name  + " cognom: " + surname + " carta: " + letter.getIdLetter();
    }
}
