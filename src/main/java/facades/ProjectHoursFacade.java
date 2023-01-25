package facades;

import dtos.ProjectHoursDTO;
import entities.ProjectHours;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

}
