package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.acme.models.Room;
import org.acme.services.RoomService;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@RolesAllowed({"user","support"})
@Path("/room")
public class RoomResource {

    @Inject
    RoomService roomService;

    @Path("/getAllRooms")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRooms() {
        try {
            List<Room> rooms = roomService.getAllRooms();
            return Response.ok(rooms).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Path("/getRoomId/{roomName}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getRoomId(@PathParam("roomName") String roomName) {
        try {
            int roomId = roomService.getRoomId(roomName);
            return Response.ok(roomId).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Path("/getRoomNameById/{roomId}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getRoomNameById(@PathParam("roomId") int roomId) {
        try {
            String roomName = roomService.getRoomNameById(roomId);
            return Response.ok(roomName).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
