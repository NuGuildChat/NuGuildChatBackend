package org.amoseman.nuguildchatbackend.api.resources;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amoseman.nuguildchatbackend.dao.UserDAO;
import org.amoseman.nuguildchatbackend.dao.exception.account.AccountExistsException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserExistsException;
import org.amoseman.nuguildchatbackend.pojo.user.User;

import java.util.UUID;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
    private final AccountDAO accountDAO;
    private final UserDAO userDAO;

    public AccountResource(AccountDAO accountDAO, UserDAO userDAO) {
        this.accountDAO = accountDAO;
        this.userDAO = userDAO;
    }

    @POST
    public Response signup(String name, String password) {
        long now = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();
        Account account = new Account(uuid, password);
        User user = new User(uuid, now, now, name);
        try {
            accountDAO.create(account);
        }
        catch (AccountExistsException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            userDAO.create(user);
        }
        catch (UserExistsException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(uuid).build();
    }
}
