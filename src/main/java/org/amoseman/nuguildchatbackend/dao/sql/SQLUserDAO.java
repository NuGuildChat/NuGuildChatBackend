package org.amoseman.nuguildchatbackend.dao.sql;

import org.amoseman.nuguildchatbackend.dao.UserDAO;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserDoesNotExistException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserExistsException;
import org.amoseman.nuguildchatbackend.pojo.user.Signup;
import org.amoseman.nuguildchatbackend.pojo.user.UserRecord;
import org.amoseman.nuguildchatbackend.pojo.user.UserUpdate;
import org.amoseman.nuguildchatbackend.service.auth.UserAuthenticator;
import org.jooq.Record;
import org.jooq.Result;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;

public class SQLUserDAO implements UserDAO {
    private final SecureRandom random;
    private final DatabaseConnection connection;

    public SQLUserDAO(SecureRandom random, DatabaseConnection connection) {
        this.random = random;
        this.connection = connection;
    }

    private boolean usernameInUse(String username) {
        return 1 == connection.create()
                .select()
                .from(table("users"))
                .where(field("username").eq(username))
                .fetch()
                .size();
    }

    @Override
    public void create(Signup signup) throws UserExistsException {
        long now = System.currentTimeMillis();
        if (usernameInUse(signup.getUsername())) {
            throw new UserExistsException();
        }
        String salt = new String(UserAuthenticator.generateSalt(random));
        connection.create()
                .insertInto(
                        table("users"),
                        field("username"),
                        field("display_name"),
                        field("pronouns"),
                        field("created"),
                        field("updated"),
                        field("password_hash"),
                        field("password_salt")
                )
                .values(
                        signup.getUsername(),
                        signup.getUsername(),
                        "",
                        now,
                        now,
                        UserAuthenticator.hash(signup.getPassword(), salt),
                        salt
                )
                .execute();
    }

    @Override
    public void update(UserUpdate user) throws UserDoesNotExistException {
        long now = System.currentTimeMillis();
        if (!usernameInUse(user.getUsername())) {
            throw new UserDoesNotExistException();
        }
        connection.create()
                .update(table("users"))
                .set(field("display_name"), user.getDisplayName())
                .set(field("pronouns"), user.getPronouns())
                .set(field("updated"), now)
                .execute();
    }

    @Override
    public void delete(String username) throws UserDoesNotExistException {
        int result = connection.create()
                .deleteFrom(table("users"))
                .where(field("username").eq(username))
                .execute();
        if (0 == result) {
            throw new UserDoesNotExistException();
        }
    }

    @Override
    public UserRecord get(String username) throws UserDoesNotExistException {
        Result<Record> result = connection.create()
                .select()
                .from(table("users"))
                .where(field("username").eq(username))
                .fetch();
        if (result.isEmpty()) {
            throw new UserDoesNotExistException();
        }
        Record record = result.get(0);
        return recordAsUser(record);
    }

    @Override
    public List<UserRecord> getAll() {
        Result<Record> result = connection.create()
                .select()
                .from(table("users"))
                .fetch();
        List<UserRecord> users = new ArrayList<>();
        result.forEach(record -> users.add(recordAsUser(record)));
        return users;
    }

    private UserRecord recordAsUser(Record record) {
        return new UserRecord(
                record.get(field("display_name"), String.class),
                record.get(field("pronouns"), String.class),
                record.get(field("username"), String.class),
                record.get(field("created"), Long.class),
                record.get(field("updated"), Long.class)
        );
    }
}
