package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static java.awt.AWTEventMulticaster.add;

@Entity
//Til setUp I Test
@NamedQuery(name = "Boat.deleteAllRows", query = "DELETE from Boat ")
public class Boat {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String navn;

    public Boat() {
    }


    public Boat(int id, String navn) {
        this.id = id;
        this.navn = navn;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }


}