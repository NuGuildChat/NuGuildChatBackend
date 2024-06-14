package org.amoseman.nuchatbackend.api.resources;

import io.dropwizard.auth.Auth;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amoseman.nuchatbackend.dao.exception.channel.ChannelDoesNotExistException;
import org.amoseman.nuchatbackend.dao.exception.channel.ChannelExistsException;
import org.amoseman.nuchatbackend.dao.exception.channel.ChannelModificationException;
import org.amoseman.nuchatbackend.dao.exception.channel.ChannelNameInUseException;
import org.amoseman.nuchatbackend.dao.exception.user.UserAuthorizationException;
import org.amoseman.nuchatbackend.dao.exception.user.UserDoesNotExistException;
import org.amoseman.nuchatbackend.pojo.channel.Channel;
import org.amoseman.nuchatbackend.pojo.channel.ChannelUpdate;
import org.amoseman.nuchatbackend.pojo.user.User;
import org.amoseman.nuchatbackend.service.ChannelService;
import org.amoseman.nuchatbackend.service.auth.UserPrincipal;

@Path("/channel")
@Produces(MediaType.APPLICATION_JSON)
public class ChannelResource {
    private final ChannelService channelService;

    public ChannelResource(ChannelService channelService) {
        this.channelService = channelService;
    }

    @POST
    public Response createChannel(Channel channel) {
        try {
            channelService.create(channel);
            return Response.ok().build();
        }
        catch (ChannelNameInUseException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    public Response updateChannel(@Auth UserPrincipal userPrincipal, ChannelUpdate channel) {
        try {
            channelService.update(userPrincipal.getName(), channel);
            return Response.ok().build();
        }
        catch (ChannelDoesNotExistException | UserAuthorizationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getChannel(@Auth UserPrincipal userPrincipal, @PathParam("uuid") long id) {
        try {
            return Response.ok(channelService.get(userPrincipal.getName(), id)).build();
        }
        catch (UserAuthorizationException | ChannelDoesNotExistException e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("/open")
    public Response getOpenChannels() {
        return Response.ok(channelService.getIfOpen()).build();
    }

    @GET
    public Response getChannelsIfMember(@Auth UserPrincipal userPrincipal) {
        try {
            return Response.ok(channelService.getIfMember(userPrincipal.getName())).build();
        }
        catch (UserDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
