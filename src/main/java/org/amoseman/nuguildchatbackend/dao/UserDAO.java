package org.amoseman.nuguildchatbackend.dao;

import org.amoseman.nuguildchatbackend.dao.exception.user.UserDoesNotExistException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserExistsException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserModificationException;
import org.amoseman.nuguildchatbackend.pojo.user.UserRecord;
import org.amoseman.nuguildchatbackend.pojo.user.UserUpdate;
import org.amoseman.nuguildchatbackend.service.auth.UserPrincipal;

import java.util.List;

public interface UserDAO {
    void create(String username, String password) throws UserExistsException;

    void update(UserUpdate user) throws UserDoesNotExistException;
    void delete(String username) throws UserDoesNotExistException;
    UserRecord get(String username) throws UserDoesNotExistException;
    List<UserRecord> getAll();
}
