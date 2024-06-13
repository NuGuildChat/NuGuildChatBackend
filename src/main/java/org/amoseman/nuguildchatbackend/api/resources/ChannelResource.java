package org.amoseman.nuguildchatbackend.api.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amoseman.nuguildchatbackend.dao.exception.channel.ChannelDoesNotExistException;
import org.amoseman.nuguildchatbackend.dao.exception.channel.ChannelExistsException;
import org.amoseman.nuguildchatbackend.dao.exception.channel.ChannelModificationException;
import org.amoseman.nuguildchatbackend.dao.exception.channel.ChannelNameInUseException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserDoesNotExistException;
import org.amoseman.nuguildchatbackend.pojo.channel.Channel;
import org.amoseman.nuguildchatbackend.pojo.user.User;
import org.amoseman.nuguildchatbackend.service.ChannelService;

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
        catch (ChannelExistsException | ChannelNameInUseException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    public Response updateChannel(Channel channel) {
        try {
            channelDAO.update(channel);
            return Response.ok().build();
        }
        catch (ChannelDoesNotExistException | ChannelModificationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{uuid}")
    public Response getChannel(@PathParam("uuid") String uuid) {
        try {
            return Response.ok(channelDAO.get(uuid)).build();
        }
        catch (ChannelDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/open")
    public Response getOpenChannels() {
        return Response.ok(channelDAO.getIfOpen()).build();
    }

    @GET
    public Response getChannelsIfMember(User user) {
        try {
            return Response.ok(channelDAO.getIfMember(user)).build();
        }
        catch (UserDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
