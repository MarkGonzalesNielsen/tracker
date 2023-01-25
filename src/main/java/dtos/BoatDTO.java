package dtos;

import entities.Boat;
import java.util.ArrayList;
import java.util.List;

public class BoatDTO {

    private int id;
    private String navn;

    public BoatDTO() {
    }


    public BoatDTO(Boat rm){
        this.id = rm.getId();
        this.navn = rm.getNavn();
    }


    public BoatDTO(int id, String navn) {
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

    public static List<BoatDTO> getDtos(List<Boat> rms)
    {
        List<BoatDTO> rmdtos = new ArrayList<>();
        rms.forEach(rm -> rmdtos.add(new BoatDTO(rm)));
        return rmdtos;
    }



}
