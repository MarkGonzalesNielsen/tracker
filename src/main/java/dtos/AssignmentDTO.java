package dtos;

import entities.Assignment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssignmentDTO {
    private long id;
    private String familyName;
    private Date createDate;
    private String contactInfo;



    public AssignmentDTO(Assignment rm){
        this.id = rm.getId();
        this.familyName = rm.getFamilyName();
        this.createDate = rm.getCreateDate();
        this.contactInfo = rm.getContactInfo();
    }

    public AssignmentDTO() {
    }


    public AssignmentDTO(AssignmentDTO assignmentDTO){
        this.familyName = assignmentDTO.getFamilyName();
        this.createDate = assignmentDTO.getCreateDate();
        this.contactInfo = assignmentDTO.getContactInfo();
    }

    public AssignmentDTO(long id, String familyName, Date createDate, String contactInfo) {
        this.id = id;
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


    public static List<AssignmentDTO> getDtos(List<Assignment> rms) {
        List<AssignmentDTO> rmdtos = new ArrayList<>();
        rms.forEach(rm -> rmdtos.add(new AssignmentDTO(rm)));
        return rmdtos;
    }
}
