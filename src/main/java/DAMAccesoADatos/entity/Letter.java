package DAMAccesoADatos.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Letter")
public class Letter implements Serializable {
    /*
    ---------Columnas---------
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_letter")
    private Integer idLetter;

    @Column(nullable = false, length = 50)
    private String address;


    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 50)
    private String hour;

    @Column(nullable = false,length = 50)
    private int day;


    @ManyToOne
    @JoinColumn(name="id_assistant")
    private Assistant assistant;


    @OneToOne (mappedBy = "letter" )
    private Kid kid;

    @OneToMany(mappedBy = "letter", cascade = CascadeType.ALL   )
    private Set<LetterGift> letterGiftSet = new HashSet<>();

    /*
----------Constructores--------------
    */
    public Letter() {
    }

    public Letter(String adress, String city, String hour, int day, Assistant assistant) {
        this.address = adress;
        this.city = city;
        this.hour = hour;
        this.day = day;
        this.assistant = assistant;
        // the kid is setted with the setter
    }
        /*
    ----------- Getters y setters-------------
     */

    public Integer getIdLetter() {
        return idLetter;
    }

    public String getAdress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }

    public Set<LetterGift> getLetterGiftSet() {
        return letterGiftSet;
    }

    public void setLetterGiftSet(Set<LetterGift> letterGiftSet) {
        this.letterGiftSet = letterGiftSet;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "idLetter=" + idLetter +
                ", adress='" + address + '\'' +
                ", city='" + city + '\'' +
                ", hour='" + hour + '\'' +
                ", day=" + day +
                ", assistant=" + assistant.getName() +
                ", kid=" + kid.getName() +
                '}';
    }
}
