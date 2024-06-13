package org.amoseman.nuguildchatbackend.service.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.amoseman.nuguildchatbackend.dao.sql.DatabaseConnection;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.jooq.Record;
import org.jooq.Result;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class UserAuthenticator implements Authenticator<BasicCredentials, UserPrincipal> {
    private final DatabaseConnection connection;

    public UserAuthenticator(DatabaseConnection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<UserPrincipal> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        Result<Record> result = connection.create()
                .select()
                .from(table("users"))
                .where(field("uuid").eq(basicCredentials.getUsername()))
                .fetch();
        if (result.isEmpty()) {
            return Optional.empty();
        }
        Record record = result.get(0);
        String hash = record.get(field("hash"), String.class);
        String salt = record.get(field("salt"), String.class);
        Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13)
                .withIterations(2)
                .withMemoryAsKB(66536)
                .withParallelism(1)
                .withSalt(salt.getBytes(StandardCharsets.UTF_8));
        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(builder.build());
        byte[] hashAttempt = new byte[64];
        generator.generateBytes(basicCredentials.getPassword().getBytes(StandardCharsets.UTF_8), hashAttempt);
        if (!hash.equals(new String(hashAttempt))) {
            return Optional.empty();
        }
        return Optional.of(new UserPrincipal(basicCredentials.getUsername()));
    }
}
