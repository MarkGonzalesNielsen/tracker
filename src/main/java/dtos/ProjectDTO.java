package dtos;

import entities.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectDTO {
    private long id;
    private String name;
    private String description;


    public ProjectDTO(Project rm) {
        this.id = rm.getId();
        this.name = rm.getName();
        this.description = rm.getDescription();
    }

    public ProjectDTO() {
    }

    public ProjectDTO(ProjectDTO projectDTO) {
        this.name = name;
        this.description = description;
    }

    public ProjectDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<ProjectDTO> getDtos(List<Project> rms) {
        List<ProjectDTO> rmdtos = new ArrayList();
        rms.forEach(rm -> rmdtos.add(new ProjectDTO(rm)));
        return rmdtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDTO that = (ProjectDTO) o;
        return id == that.id && name.equals(that.name) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
