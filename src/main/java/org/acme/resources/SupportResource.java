package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.models.Support;
import org.acme.services.SupportService;

@RolesAllowed({"support","user"})
@Path("/Support")
public class SupportResource {

    @Inject
    SupportService supportService;

    @Path("/getById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSupportById(@PathParam("id") int id){
        try {
            Support support=supportService.getSupportById(id);
            return Response.ok(support).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

