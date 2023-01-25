package rest;

import dtos.ProjectDTO;
import dtos.ProjectHoursDTO;
import entities.ProjectHours;
import entities.RenameMe;
import entities.Project;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.config.JsonParserType.GSON;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class ProjectHoursResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static ProjectHours t1, t2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        t1 = new ProjectHours("200","SkyScraper","Huge Project");
        t2 = new ProjectHours("70","ok","Small Project");
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
    void deleteProjectHours() {
    }

    @Test
    void getAllProjectHours()
    {
        List<ProjectHoursDTO> projectHoursDTOList;

        projectHoursDTOList = given()
                .contentType("application/json")
                .when()
                .get("/projectHours/all")
                .then()
                .extract().body().jsonPath().getList("", ProjectHoursDTO.class);

        ProjectHoursDTO p1 = new ProjectHoursDTO(t1);
        ProjectHoursDTO p2 = new ProjectHoursDTO(t2);
        assertThat(projectHoursDTOList, containsInAnyOrder(p1,p2));
    }
}