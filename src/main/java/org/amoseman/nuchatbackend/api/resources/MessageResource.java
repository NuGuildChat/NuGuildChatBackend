package org.amoseman.nuchatbackend.api.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amoseman.nuchatbackend.dao.MessageDAO;
import org.amoseman.nuchatbackend.dao.exception.message.MessageDoesNotExistException;
import org.amoseman.nuchatbackend.pojo.message.Message;
import org.amoseman.nuchatbackend.pojo.message.MessageUpdate;

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
    private final MessageDAO messageDAO;

    public MessageResource(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @POST
    public Response createMessage(Message message) {
        messageDAO.create(message);
        return Response.ok().build();
    }

    @PUT
    public Response updateMessage(MessageUpdate message) {
        try {
            messageDAO.update(message);
            return Response.ok().build();
        }
        catch (MessageDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{channel}")
    public Response getMessages(@PathParam("channel") String channelUUID) {
        return Response.ok(messageDAO.getAll(channelUUID)).build();
    }
}
