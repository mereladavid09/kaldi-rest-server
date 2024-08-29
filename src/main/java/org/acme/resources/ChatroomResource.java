package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.acme.dtos.ChatroomDto;
import org.acme.models.Chatroom;
import org.acme.models.Support;
import org.acme.models.User;
import org.acme.respons.JoinChatroomResponse;
import org.acme.services.ChatroomService;
import org.acme.services.MessageService;
import org.acme.services.SupportService;
import org.acme.services.UserService;

import java.util.List;

@Path("Chatroom")
public class ChatroomResource {
    @Inject
    ChatroomService chatroomService;

    @Inject
    MessageService messageService;

    @Inject
    UserService userService;

    @Inject
    SupportService supportService;

    @POST
    @Path("/CreateChatroom")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("user")
    public Response createChatroom(ChatroomDto chatroomDto){
        try {
            chatroomService.createChatroom(chatroomDto);
            return Response.ok(200).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/joinChatroom")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("support")
    public Response joinChatroom(ChatroomDto chatroomDto){
        try {
            chatroomService.joinChatroom(chatroomDto);
            JoinChatroomResponse joinChatroomResponse= new JoinChatroomResponse();
            joinChatroomResponse.setUser(userService.getUserById(chatroomDto.getUserId()));
            joinChatroomResponse.setMessage(messageService.getAllMessagesInChatroom(chatroomDto.getId()));
            return Response.ok(joinChatroomResponse).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/getUnanswered")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("support")
    public Response getUnansweredChatrooms(){
        try {
            List<Chatroom> chatrooms = chatroomService.getUnansweredChatrooms();
            return Response.ok(chatrooms).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GET
    @Path("/getBySupportId")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("support")
    public Response getChatroomsBySupportId(@Context SecurityContext securityContext){
        try {
            Support support = supportService.getSupportByUsername(securityContext.getUserPrincipal().getName());
            List<Chatroom> chatrooms = chatroomService.getChatroomBySupportId(support.getId());
            return Response.ok(chatrooms).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GET
    @Path("/getByUserId")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response getChatroomsByUserId(@Context SecurityContext securityContext){
        try {
            User user = userService.getUserByUsername(securityContext.getUserPrincipal().getName());
            List<Chatroom> chatrooms = chatroomService.getChatroomByUserId(user.getId());
            return Response.ok(chatrooms).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
