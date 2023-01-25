package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.DinnereventDTO;
import facades.DinnereventFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/event")
public class DinnereventResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final DinnereventFacade FACADE = DinnereventFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<DinnereventDTO> rns = FACADE.getAll();
        return Response.ok().entity(GSON.toJson(rns)).build();
    }

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
   // @RolesAllowed("admin")
    public Response createEvent(DinnereventDTO dinnereventDTO) {
        dinnereventDTO = FACADE.createEvent(dinnereventDTO);
        return Response.ok().entity(GSON.toJson(dinnereventDTO)).build();

    }

    @DELETE
    @Path("deleteevent/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    //@RolesAllowed("admin")
    public Response deleteEvent(@PathParam("id") int id) {
        FACADE.deleteEvent(id);
        return Response.ok().entity(GSON.toJson(id)).build();
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
   // @RolesAllowed("admin")
    public Response updateEvent(@PathParam("id") Long id, String a) {
        DinnereventDTO dinnereventDTO = GSON.fromJson(a, DinnereventDTO.class);
        dinnereventDTO.setId(id);
        DinnereventDTO result = FACADE.updateEvent(dinnereventDTO);
        return Response.ok().entity(GSON.toJson(result)).build();
    }



}