package org.amoseman.nuguildchatbackend.api.resources;

import io.dropwizard.auth.Auth;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amoseman.nuguildchatbackend.dao.exception.channel.ChannelDoesNotExistException;
import org.amoseman.nuguildchatbackend.dao.exception.message.MessageDoesNotExistException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserAuthorizationException;
import org.amoseman.nuguildchatbackend.pojo.message.Message;
import org.amoseman.nuguildchatbackend.pojo.message.MessageUpdate;
import org.amoseman.nuguildchatbackend.service.MessageService;
import org.amoseman.nuguildchatbackend.service.auth.UserPrincipal;

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
    private final MessageService messageService;

    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @POST
    public Response createMessage(@Auth UserPrincipal principal, Message message) {
        try {
            messageService.create(principal, message);
            return Response.ok().build();
        }
        catch (UserAuthorizationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    public Response updateMessage(@Auth UserPrincipal principal, MessageUpdate message) {
        try {
            messageService.update(principal, message);
            return Response.ok().build();
        }
        catch (MessageDoesNotExistException | UserAuthorizationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{channel}")
    public Response getMessages(@Auth UserPrincipal principal, @PathParam("channel") long channelID) {
        try {
            return Response.ok(messageService.getAll(principal, channelID)).build();
        }
        catch (UserAuthorizationException | ChannelDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
