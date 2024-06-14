package org.amoseman.nuchatbackend.api.resources;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amoseman.nuchatbackend.dao.UserDAO;
import org.amoseman.nuchatbackend.dao.exception.account.AccountExistsException;
import org.amoseman.nuchatbackend.dao.exception.user.UserExistsException;
import org.amoseman.nuchatbackend.pojo.user.User;
import org.amoseman.nuchatbackend.pojo.user.UserRecord;
import org.amoseman.nuchatbackend.service.auth.UserPrincipal;

import java.util.UUID;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserDAO userDAO;

    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @POST
    public Response signup(UserPrincipal user) {
        try {
            userDAO.create(user);
        }
        catch (UserExistsException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok().build();
    }
}
