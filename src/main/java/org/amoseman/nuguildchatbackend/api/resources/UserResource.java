package org.amoseman.nuguildchatbackend.api.resources;

import io.dropwizard.auth.Auth;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserAuthorizationException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserDoesNotExistException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserExistsException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserModificationException;
import org.amoseman.nuguildchatbackend.pojo.user.UserUpdate;
import org.amoseman.nuguildchatbackend.service.UserService;
import org.amoseman.nuguildchatbackend.service.auth.UserPrincipal;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    public Response signup(UserPrincipal user) {
        try {
            userService.create(user);
        }
        catch (UserExistsException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }

    @PUT
    public Response update(@Auth UserPrincipal principal, UserUpdate update) {
        try {
            userService.update(principal, update);
            return Response.ok().build();
        }
        catch (UserModificationException | UserAuthorizationException | UserDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{username}")
    public Response get(@Auth UserPrincipal principal, @PathParam("username") String username) {
        try {
            return Response.ok(userService.get(username)).build();
        }
        catch (UserDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    public Response get(@Auth UserPrincipal principal) {
        return Response.ok(userService.getAll()).build();
    }

    @DELETE
    @Path("/{username}")
    public Response delete(@Auth UserPrincipal principal, @PathParam("username") String username) {
        try {
            userService.delete(principal, username);
            return Response.ok().build();
        }
        catch (UserAuthorizationException | UserDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
