package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EmployeeDTO;
import facades.EmployeeFacade;
import facades.FacadeExample;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employee")
public class EmployeeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final EmployeeFacade FACADE =  EmployeeFacade.getEmployeeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("/getAll") //matcher facade method getAll og test den så i demo.http. //test den så med rest-assured
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllEmployees() {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();

        //getAll fra databasen til facaden til konvertering til DTO og oversat til json og ud til klien?
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createEmployee(String jsonInput){
        EmployeeDTO employee = GSON.fromJson(jsonInput, EmployeeDTO.class);
        EmployeeDTO returned = FACADE.createEmployee(employee);
        return Response.ok().entity(GSON.toJson(returned)).build();
    }

//    @Path("/populator")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response getAll() {
//        return Response.ok().entity(GSON.toJson(FACADE.main???)).build();
//
//
//    }
}
