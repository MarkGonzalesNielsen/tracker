package facades;

import dtos.ProjectDTO;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProjectFacade {

    public ProjectFacade() {
    }

    public static ProjectFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ProjectFacade();
        }
        return instance;
    }

    private static ProjectFacade instance;
    private static EntityManagerFactory emf;

    public List<ProjectDTO> getAllProjects() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p", Project.class);
        List<Project> rms = query.getResultList();
        return ProjectDTO.getDtos(rms);
    }

    public ProjectDTO createProject(ProjectDTO pn) {
        Project project = new Project(pn.getName(), pn.getDescription());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(project);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ProjectDTO(project);
    }

    public static ProjectFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ProjectFacade();
        }
        return instance;
    }

    //Til createProjectTest
    public long getProjectCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long ProjectCount = (long) em.createQuery("SELECT COUNT(p) FROM Project p").getSingleResult();
            return ProjectCount;
        } finally {
            em.close();
        }
    }
}
