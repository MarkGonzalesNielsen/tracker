package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ProjectHoursDTO;
import entities.ProjectHours;
import facades.ProjectHoursFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/projectHours")
public class ProjectHoursResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final ProjectHoursFacade FACADE = ProjectHoursFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }


    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    //@RolesAllowed("admin")
    public Response updateProjectHours(@PathParam("id") Long id, String a) {
        ProjectHoursDTO projectHoursDTO = GSON.fromJson(a, ProjectHoursDTO.class);
        projectHoursDTO.setId(id);
        ProjectHoursDTO result = FACADE.updateProjectHours(projectHoursDTO);
        return Response.ok().entity(GSON.toJson(result)).build();
    }


    @DELETE
    @Path("delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    // @RolesAllowed("admin")
    public Response deleteProjectHours(@PathParam("id") int id) {
        FACADE.deleteProjectHours(id);
        return Response.ok().entity(GSON.toJson(id)).build();
    }



}