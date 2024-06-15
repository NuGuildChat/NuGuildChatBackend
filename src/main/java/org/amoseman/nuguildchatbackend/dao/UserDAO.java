package org.amoseman.nuguildchatbackend.dao;

import org.amoseman.nuguildchatbackend.dao.exception.user.UserDoesNotExistException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserExistsException;
import org.amoseman.nuguildchatbackend.pojo.user.Signup;
import org.amoseman.nuguildchatbackend.pojo.user.UserRecord;
import org.amoseman.nuguildchatbackend.pojo.user.UserUpdate;

import java.util.List;

public interface UserDAO {
    void create(Signup signup) throws UserExistsException;

    void update(UserUpdate user) throws UserDoesNotExistException;
    void delete(String username) throws UserDoesNotExistException;
    UserRecord get(String username) throws UserDoesNotExistException;
    List<UserRecord> getAll();
}
