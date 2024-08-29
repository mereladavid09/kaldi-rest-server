package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dtos.MessageDto;
import org.acme.models.Message;
import org.acme.services.MessageService;
import java.util.List;

@Path("/Message")
public class MessageResource {
    @Inject
    MessageService messageService;

    @Path("/getAllMessagesInChatRoom/{chatroomId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user","support"})
    public Response getAllMessagesInChatroom(@PathParam("chatroomId") int chatroomId) {
        try {
            List<Message> messages = messageService.getAllMessagesInChatroom(chatroomId);
            return Response.ok(messages).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Path("/SendMessage")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user","support"})
    public Response sendMessage(MessageDto messageDto){
        try{
            messageService.sentMessage(messageDto);
            return Response.ok("OK").build();
        }catch (Exception exception){
            exception.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
