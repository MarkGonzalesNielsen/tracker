//package rest;
//
//import dtos.BoatDTO;
//import entities.Boat;
//import entities.RenameMe;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.parsing.Parser;
//import org.glassfish.grizzly.http.server.HttpServer;
//import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
//import org.glassfish.jersey.server.ResourceConfig;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import utils.EMF_Creator;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.ws.rs.core.UriBuilder;
//import java.net.URI;
//import java.util.List;
//
//import static io.restassured.RestAssured.given;
//import static io.restassured.path.json.config.JsonParserType.GSON;
//import static org.glassfish.jersey.internal.guava.Predicates.equalTo;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class BoatResourceTest {
//
//    //7777 Interne Grizzly server
//    private static final int SERVER_PORT = 7777;
//    private static final String SERVER_URL = "http://localhost/api";
//    private static Boat m1, m2;
//
//
//    //Bygger URL'en op som httpserver/Grizzly kan bruge
//    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
//    private static HttpServer httpServer;
//    private static EntityManagerFactory emf;
//
//    //Starter grizzly serveren op
//    static HttpServer startServer() {
//        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
//        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//        //This method must be called before you request the EntityManagerFactory
//        EMF_Creator.startREST_TestWithDB();
//        emf = EMF_Creator.createEntityManagerFactoryForTest();
//
//        httpServer = startServer();
//        //Setup RestAssured
//        RestAssured.baseURI = SERVER_URL;
//        RestAssured.port = SERVER_PORT;
//        RestAssured.defaultParser = Parser.JSON;
//    }
//
//    //Lukker serveren ned
//    @AfterAll
//    public static void closeTestServer() {
//        //System.in.read();
//
//        //Don't forget this, if you called its counterpart in @BeforeAll
//        EMF_Creator.endREST_TestWithDB();
//        httpServer.shutdownNow();
//    }
//
//    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
//    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
//    //Instatiere både.
//    @BeforeEach
//    public void setUp()
//    {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Boat.deleteAllRows").executeUpdate();
//            m1 = new Boat(1,"Anna");
//            m2 = new Boat(1, "valborg");
//
//            em.persist(m1);
//            em.persist(m2);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//
//    //er der hul igennem?
//    @Test
//    public void testServerIsUp() {
//        System.out.println("Testing is server UP");
//        given().when().get("/boat").then().statusCode(200);
//    }
//
//    //burde virke
//    //"" fordi den er i et tomt array i JSON
//    @Test
//    void getAllBoats() {
//        List<BoatDTO> boatDTOS;
//
//        boatDTOS = given()
//                .contentType("application/json")
//                .when()
//                .get("/boat/all")
//                .then()
//                .extract().body().jsonPath().getList("", BoatDTO.class);
//
//        BoatDTO m1DTO = new BoatDTO(m1);
//        BoatDTO m2DTO = new BoatDTO(m2);
//        assertThat(boatDTOS, containsInAnyOrder(m1DTO, m2DTO));
//    }
//
//    @Test
//    void createBoat() {
//    }
//
//    //virker ikke
//    @Test
//    void deleteBoat() {
//        given()
//                .contentType(ContentType.JSON)
//                .pathParam("id", m2.getId())
//                .delete("/boat/{id}")
//                .then()
//                .statusCode(200)
//                .body("id",equalTo(m2.getId()));
//    }
//
//    @Test
//    void updateBoat() {
//
//    }
//}