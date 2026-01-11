package DAMAccesoADatos.entity;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "LetterGift")
public class LetterGift implements  Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLetterGift;

    @Column(nullable = false, length = 50)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_letter")
    private Letter letter;


    @ManyToOne
    @JoinColumn(name = "id_gift")
    private Gift gift;

    public LetterGift() {
    }

    public LetterGift(Integer quantity, Letter letter, Gift gift) {
        this.quantity = quantity;
        this.letter = letter;
        this.gift = gift;
    }
}
