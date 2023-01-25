package facades;

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
