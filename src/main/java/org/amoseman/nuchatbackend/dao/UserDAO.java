package org.amoseman.nuchatbackend.dao;

import org.amoseman.nuchatbackend.dao.exception.user.UserDoesNotExistException;
import org.amoseman.nuchatbackend.dao.exception.user.UserExistsException;
import org.amoseman.nuchatbackend.dao.exception.user.UserModificationException;
import org.amoseman.nuchatbackend.pojo.user.User;
import org.amoseman.nuchatbackend.pojo.user.UserRecord;
import org.amoseman.nuchatbackend.pojo.user.UserUpdate;
import org.amoseman.nuchatbackend.service.auth.UserPrincipal;

import java.util.List;

public interface UserDAO {
    void create(UserPrincipal user) throws UserExistsException;

    void update(UserUpdate user) throws UserDoesNotExistException, UserModificationException;
    void delete(String username) throws UserDoesNotExistException;
    UserRecord get(String username) throws UserDoesNotExistException;
    List<UserRecord> getAll();
}
