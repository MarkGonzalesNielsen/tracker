package facades;

import edu.emory.mathcs.backport.java.util.Arrays;
import dtos.ProjectDTO;
import entities.Project;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectFacadeTest {

    private static EntityManagerFactory emf;
    private static ProjectFacade facade;
    private Project m1, m2;


    @BeforeAll
    public static void setUpClass()
    {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = ProjectFacade.getInstance(emf);

    }

    @BeforeEach
    void setUp()
    {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Project.deleteAllRows").executeUpdate();
            m1 = new Project("Ok","ok");
            m2 = new Project("ok" , "ok");

            em.persist(m1);
            em.persist(m2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

//    @Test
//    void getAllProjects()  {
//        List<ProjectDTO> boatDTOList = facade.getAllProjects();
//        int expected = 2;
//        int actual = boatDTOList.size();
//        assertEquals(expected, actual);
//        assertThat(boatDTOList, containsInAnyOrder(new ProjectDTO(m1), new ProjectDTO(m2)));
//    }

    @Test
    void createProject() {
        facade.createProject(new ProjectDTO("ok", "ok"));
        assertEquals(3, facade.getProjectCount());
    }



}
