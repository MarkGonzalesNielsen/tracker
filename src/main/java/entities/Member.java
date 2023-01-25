package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
public class Member {
    private static final long serialVersionUID = 1L;
    @Id
    private long id;
    private String address;
    private int phone;
    private String email;
    private Date birthYear;
    private int account;

    public Member() {
    }

    @ManyToMany()
    private List<Assignment> assignmentList = new ArrayList<>();

    public Member(long id, String address, int phone, String email, Date birthYear, int account) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthYear = birthYear;
        this.account = account;
    }

    public void addAssignment(Assignment assignment) {

        if (assignment != null) {
            this.assignmentList.add(assignment);
            assignment.getMembers().add(this);

        }

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
}