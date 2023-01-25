package dtos;

import entities.Project;
import entities.ProjectHours;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectHoursDTO {
    private long id;
    private String hoursSpendt;
    private String userStory;
    private String description;


    public ProjectHoursDTO(ProjectHours rm) {
        this.id = rm.getId();
        this.hoursSpendt = rm.getHoursSpendt();
        this.userStory = rm.getUserStory();
        this.description = rm.getDescription();
    }

    public ProjectHoursDTO(String hoursSpendt, String userStory, String description) {
        this.hoursSpendt = hoursSpendt;
        this.userStory = userStory;
        this.description = description;
    }

    public ProjectHoursDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHoursSpendt() {
        return hoursSpendt;
    }

    public void setHoursSpendt(String hoursSpendt) {
        this.hoursSpendt = hoursSpendt;
    }

    public String getUserStory() {
        return userStory;
    }

    public void setUserStory(String userStory) {
        this.userStory = userStory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<ProjectHoursDTO> getDtos(List<ProjectHours> rms) {
        List<ProjectHoursDTO> rmdtos = new ArrayList();
        rms.forEach(rm -> rmdtos.add(new ProjectHoursDTO(rm)));
        return rmdtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectHoursDTO that = (ProjectHoursDTO) o;
        return id == that.id && hoursSpendt.equals(that.hoursSpendt) && userStory.equals(that.userStory) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hoursSpendt, userStory, description);
    }

    public static List<ProjectHoursDTO> getHourDtos(List<ProjectHours> rms) {
        List<ProjectHoursDTO> rmdtos = new ArrayList();
        rms.forEach(rm -> rmdtos.add(new ProjectHoursDTO(rm)));
        return rmdtos;
    }




}
