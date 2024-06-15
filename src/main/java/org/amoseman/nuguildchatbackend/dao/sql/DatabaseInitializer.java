package org.amoseman.nuguildchatbackend.dao.sql;

import org.amoseman.nuguildchatbackend.api.NuGuildChatConfiguration;
import org.amoseman.nuguildchatbackend.service.auth.UserAuthenticator;

import static org.jooq.impl.DSL.constraint;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.SQLDataType.*;

public class DatabaseInitializer {
    private final DatabaseConnection connection;
    private final NuGuildChatConfiguration configuration;

    public DatabaseInitializer(DatabaseConnection connection, NuGuildChatConfiguration configuration) {
        this.connection = connection;
        this.configuration = configuration;
    }

    public void init() {
        createMessageTable();
        createChannelTable();
        createUserTable();
    }

    private void createMessageTable() {
        connection.create()
                .createTableIfNotExists("messages")
                .column(field("id"), BIGINT.identity(true))
                .column(field("author"), VARCHAR(configuration.getMaxUsernameLength()))
                .column(field("channel"), BIGINT)
                .column(field("created"), BIGINT)
                .column(field("updated"), BIGINT)
                .column(field("contents"), VARCHAR(configuration.getMaxMessageLength()))
                .constraints(
                        constraint("PK_MESSAGES").primaryKey("id")
                )
                .execute();
    }

    private void createChannelTable() {
        int membersLength = configuration.getMaxChannelMembers() * configuration.getMaxUsernameLength() + (Math.max(0, configuration.getMaxChannelMembers() - 1));
        connection.create()
                .createTableIfNotExists("channels")
                .column(field("id"), BIGINT.identity(true))
                .column(field("name"), VARCHAR(configuration.getMaxChannelNameLength()))
                .column(field("admin"), VARCHAR(configuration.getMaxUsernameLength()))
                .column(field("closed"), BOOLEAN)
                .column(field("created"), BIGINT)
                .column(field("updated"), BIGINT)
                .column(field("members"), VARCHAR(membersLength))
                .constraints(
                        constraint("PK_CHANNELS").primaryKey("id")
                )
                .execute();

    }

    private void createUserTable() {
        connection.create()
                .createTableIfNotExists("users")
                .column(field("username"), VARCHAR(configuration.getMaxUsernameLength()))
                .column(field("password_hash"), VARCHAR(UserAuthenticator.HASH_LENGTH))
                .column(field("password_salt"), VARCHAR(UserAuthenticator.SALT_LENGTH))
                .column(field("display_name"), VARCHAR(configuration.getMaxUsernameLength()))
                .column(field("pronouns"), VARCHAR(configuration.getMaxPronounsLength()))
                .column(field("created"), BIGINT)
                .column(field("updated"), BIGINT)
                .constraints(
                        constraint("PK_USERS").primaryKey("username")
                )
                .execute();

    }
}
