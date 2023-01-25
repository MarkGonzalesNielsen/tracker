package dtos;

import entities.Dinnerevent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DinnereventDTO {
    private long id;
    private Date time;
    private String lacation;
    private String dish;
    private int pricePerPerson;

    public DinnereventDTO(Dinnerevent rm) {
        this.id = rm.getId();
        this.time = rm.getTime();
        this.lacation = rm.getLacation();
        this.dish = rm.getDish();
        this.pricePerPerson = rm.getPricePerPerson();
    }

    public DinnereventDTO(Date time, String lacation, String dish, int pricePerPerson) {
        this.time = time;
        this.lacation = lacation;
        this.dish = dish;
        this.pricePerPerson = pricePerPerson;
    }

    public DinnereventDTO() {
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

    public static List<DinnereventDTO> getDtos(List<Dinnerevent> rms) {
        List<DinnereventDTO> rmdtos = new ArrayList<>();
        rms.forEach(rm -> rmdtos.add(new DinnereventDTO(rm)));
        return rmdtos;
    }
}
