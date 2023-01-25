package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BoatDTO;
import dtos.DinnereventDTO;
import facades.BoatFacade;
import facades.DinnereventFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/boat")
public class BoatResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final BoatFacade FACADE = BoatFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();



    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }


    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllBoats() {
        List<BoatDTO> rns = FACADE.getAllBoats();
        return Response.ok().entity(GSON.toJson(rns)).build();
    }

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
  //  @RolesAllowed("admin")
    public Response createBoat(BoatDTO boatDTO) {
        boatDTO = FACADE.createBoat(boatDTO);
        return Response.ok().entity(GSON.toJson(boatDTO)).build();
    }



    @DELETE
    @Path("delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
   // @RolesAllowed("admin")
    public Response deleteBoat(@PathParam("id") int id) {
        FACADE.deleteBoat(id);
        return Response.ok().entity(GSON.toJson(id)).build();
    }

    //Consumes modtage noget JSON(ændringer) Produces sender dem tilbage
    //PathParam finder id
    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    //@RolesAllowed("admin")
    public Response updateBoat(@PathParam("id") int id, String a) {
        BoatDTO boatDTO = GSON.fromJson(a, BoatDTO.class);
        boatDTO.setId(id);
        BoatDTO result = FACADE.updateBoat(boatDTO);
        return Response.ok().entity(GSON.toJson(result)).build();
    }


}