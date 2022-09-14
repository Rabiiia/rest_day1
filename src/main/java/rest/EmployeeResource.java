package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.EmployeeFacade;
import facades.FacadeExample;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employee")
public class EmployeeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final EmployeeFacade FACADE =  EmployeeFacade.getEmployeeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllEmployees() {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();

        //getAll fra databasen til facaden til konvertering til DTO og oversat til json og ud til klien?
    }
}
