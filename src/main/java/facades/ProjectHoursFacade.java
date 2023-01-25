package facades;

import dtos.ProjectDTO;
import dtos.ProjectHoursDTO;
import entities.Project;
import entities.ProjectHours;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProjectHoursFacade {

    public ProjectHoursFacade() {
    }

    private static ProjectHoursFacade instance;
    private static EntityManagerFactory emf;

    public static ProjectHoursFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ProjectHoursFacade();
        }
        return instance;
    }

    public ProjectHoursDTO updateProjectHours(ProjectHoursDTO pn) {
        EntityManager em = emf.createEntityManager();
        ProjectHours a = (em.find(ProjectHours.class, pn.getId()));
        try {
            a.setHoursSpendt(pn.getHoursSpendt()); a.setUserStory(pn.getUserStory()); a.setDescription(pn.getDescription());
            em.getTransaction().begin();
            a = em.merge(a);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ProjectHoursDTO(a);

    }


    public void deleteProjectHours(int pn) {
        EntityManager em = emf.createEntityManager();
        ProjectHours a = (em.find(ProjectHours.class, (long) pn));
        try {
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<ProjectHoursDTO> getAllProjectHours() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<ProjectHours> query = em.createQuery("SELECT p FROM ProjectHours p", ProjectHours.class);
        List<ProjectHours> rms = query.getResultList();
        return ProjectHoursDTO.getHourDtos(rms);
    }




}
