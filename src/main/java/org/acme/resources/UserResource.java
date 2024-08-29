package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dtos.MessageDto;
import org.acme.services.MessageService;
import org.acme.services.UserService;

@Path("User")
public class UserResource {
    @Inject
    MessageService messageService;
    @Inject
    UserService userService;

    @Path("/SendMessage")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response sendMessageToSupport(MessageDto messageDto){
        try {
           messageService.sentMessage(messageDto);
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
