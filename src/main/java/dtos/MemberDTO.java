package dtos;

import entities.Member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MemberDTO {
    private long id;
    private String address;
    private int phone;
    private String email;
    private Date birthYear;
    private int account;

    public MemberDTO(Member rm){
        this.id = rm.getId();
        this.address = rm.getAddress();
        this.phone = rm.getPhone();
        this.email = rm.getEmail();
        this.birthYear = rm.getBirthYear();
        this.account = rm.getAccount();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Date birthYear) {
        this.birthYear = birthYear;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public static List<MemberDTO> getDtos(List<Member> rms) {
        List<MemberDTO> rmdtos = new ArrayList<>();
        rms.forEach(rm -> rmdtos.add(new MemberDTO(rm)));
        return rmdtos;
    }
}
