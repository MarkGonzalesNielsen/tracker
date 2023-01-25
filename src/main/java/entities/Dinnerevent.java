package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(name = "Dinnerevent.deleteAllRows", query = "DELETE from Dinnerevent ")

public class Dinnerevent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String dish;
    private String lacation;
    private int pricePerPerson;
    private Date time;

    @OneToMany(mappedBy = "dinnerevent")
    private List<Assignment> assignmentList;

    public Dinnerevent() {
    }

    public Dinnerevent(String lacation, String dish, int pricePerPerson, Date time) {
        this.lacation = lacation;
        this.dish = dish;
        this.pricePerPerson = pricePerPerson;
        this.time = time;
    }

    public Dinnerevent(long id, String dish, String lacation, int pricePerPerson, Date time) {
        this.id = id;
        this.dish = dish;
        this.lacation = lacation;
        this.pricePerPerson = pricePerPerson;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLacation() {
        return lacation;
    }

    public void setLacation(String lacation) {
        this.lacation = lacation;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(int pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

}
