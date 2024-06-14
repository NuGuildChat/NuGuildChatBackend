package org.amoseman.nuchatbackend.service;

import org.amoseman.nuchatbackend.dao.UserDAO;
import org.amoseman.nuchatbackend.dao.exception.user.UserAuthorizationException;
import org.amoseman.nuchatbackend.dao.exception.user.UserDoesNotExistException;
import org.amoseman.nuchatbackend.dao.exception.user.UserExistsException;
import org.amoseman.nuchatbackend.dao.exception.user.UserModificationException;
import org.amoseman.nuchatbackend.pojo.user.UserRecord;
import org.amoseman.nuchatbackend.pojo.user.UserUpdate;
import org.amoseman.nuchatbackend.service.auth.UserPrincipal;

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
