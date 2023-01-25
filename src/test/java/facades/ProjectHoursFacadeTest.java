package facades;


import dtos.ProjectHoursDTO;
import entities.ProjectHours;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectHoursFacadeTest {
    private static EntityManagerFactory emf;
    private static ProjectHoursFacade projectHoursFacade;

    private static ProjectHours t1, t2;

    @BeforeAll
    public static void setUpClass()
    {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        projectHoursFacade = ProjectHoursFacade.getFacadeExample(emf);

    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        t1 = new ProjectHours("200","US-3","M");
        t2 = new ProjectHours("201","US-31","L");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("ProjectHours.deleteAllRows").executeUpdate();
            em.persist(t1);
            em.persist(t2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void updateProjectHours() throws Exception {
        t1.setDescription("OMEGA");
        ProjectHoursDTO t1New = projectHoursFacade.updateProjectHours(new ProjectHoursDTO(t2));
        assertEquals(t1New.getHoursSpendt(), t2.getHoursSpendt());
        assertNotEquals(t2.getHoursSpendt(),"200");
    }

    @Test
    void deleteProjectHours()
    {
        long t1Id = t1.getId();
        long t2Id = t2.getId();
        projectHoursFacade.deleteProjectHours((int) t1Id);
        EntityManager em = emf.createEntityManager();
        try {
            List<ProjectHours> projectHoursList = em.createQuery("select t from ProjectHours t").getResultList();
            assertEquals(1, projectHoursList.size(), "Expects 1 Trips in the DB");

            projectHoursList = em.createQuery("select t from ProjectHours t WHERE t.id = " + t1Id).getResultList();
            assertEquals(0, projectHoursList.size(), "Expects 1 persons in the DB");
            ProjectHours t = em.find(ProjectHours.class, t1Id);
            assertNull(t, "Expects that trip is removed and t is null");

            t = em.find(ProjectHours.class, t2Id);
            assertNotNull(t, "Expects that trip is removed and t is null");
        } finally {
            em.close();
        }
    }
}