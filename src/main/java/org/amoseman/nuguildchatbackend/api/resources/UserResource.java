package org.amoseman.nuguildchatbackend.api.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amoseman.nuguildchatbackend.dao.UserDAO;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserDoesNotExistException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserExistsException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserModificationException;
import org.amoseman.nuguildchatbackend.pojo.user.User;

import java.util.UUID;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserDAO userDAO;

    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @POST
    public Response createUser(String name) {
        long now = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();
        User user = new User(uuid, now, now, name);
        try {
            userDAO.create(user);
            return Response.ok().build();
        }
        catch (UserExistsException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    public Response updateUser(User user) {
        try {
            userDAO.update(user);
            return Response.ok().build();
        }
        catch (UserModificationException | UserDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{uuid}")
    public Response getUser(@PathParam("uuid") String uuid) {
        try {
            return Response.ok(userDAO.get(uuid)).build();
        }
        catch (UserDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    public Response getAllUsers() {
        return Response.ok(userDAO.getAll()).build();
    }
}
