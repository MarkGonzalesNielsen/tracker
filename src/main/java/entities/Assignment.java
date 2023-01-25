package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(name = "Assignment.deleteAllRows", query = "DELETE from Assignment ")
public class Assignment {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String familyName;
    private Date createDate;
    private String contactInfo;

    @ManyToMany(mappedBy = "assignmentList")
    private List<Member> memberList;

    @ManyToOne(cascade = CascadeType.ALL)
    private Dinnerevent dinnerevent;

    public Assignment() {

    }

    public Assignment(long id, String familyName, Date createDate, String contactInfo) {
        this.id = id;
        this.familyName = familyName;
        this.createDate = createDate;
        this.contactInfo = contactInfo;
    }

    // til at få andet data ud?
//    public Assignment(long id, String familyName, Date createDate, String contactInfo) {
//
//    }

//    public List<Member> getMember() {
//        return memberList;
//    }

    public List<Member> getMembers() {
        return memberList;
    }

    // uden id?
    public Assignment(String familyName, Date createDate, String contactInfo) {
        this.familyName = familyName;
        this.createDate = createDate;
        this.contactInfo = contactInfo;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
