package org.amoseman.nuguildchatbackend.service;

import org.amoseman.nuguildchatbackend.dao.UserDAO;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserAuthorizationException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserDoesNotExistException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserExistsException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserModificationException;
import org.amoseman.nuguildchatbackend.pojo.user.UserRecord;
import org.amoseman.nuguildchatbackend.pojo.user.UserUpdate;
import org.amoseman.nuguildchatbackend.service.auth.UserPrincipal;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void create(UserPrincipal user) throws UserExistsException {
        userDAO.create(user);
    }

    public void update(UserPrincipal principal, UserUpdate user) throws UserDoesNotExistException, UserModificationException, UserAuthorizationException {
        if (!principal.getName().equals(user.getUsername())) {
            throw new UserAuthorizationException();
        }
        userDAO.update(user);
    }
    public void delete(UserPrincipal principal, String username) throws UserDoesNotExistException, UserAuthorizationException {
        if (!principal.getName().equals(username)) {
            throw new UserAuthorizationException();
        }
        userDAO.delete(username);
    }
    public UserRecord get(String username) throws UserDoesNotExistException {
        return userDAO.get(username);
    }

    public List<UserRecord> getAll() {
        return userDAO.getAll();
    }
}
