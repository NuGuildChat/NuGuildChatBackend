package org.amoseman.nuguildchatbackend.dao;

import org.amoseman.nuguildchatbackend.dao.exception.user.UserDoesNotExistException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserExistsException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserModificationException;
import org.amoseman.nuguildchatbackend.pojo.user.User;
import org.amoseman.nuguildchatbackend.pojo.user.UserRecord;
import org.amoseman.nuguildchatbackend.pojo.user.UserUpdate;

import java.util.List;

public interface UserDAO {
    void create(User user) throws UserExistsException;
    void update(UserUpdate user) throws UserDoesNotExistException, UserModificationException;
    void delete(String uuid) throws UserDoesNotExistException;
    UserRecord get(String uuid) throws UserDoesNotExistException;
    List<UserRecord> getAll();
}
