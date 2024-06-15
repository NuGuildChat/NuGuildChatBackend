package org.amoseman.nuguildchatbackend.dao.sql;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.SQLDialect;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final Connection connection;
    private final SQLDialect dialect;

    public DatabaseConnection(String url) {
        try {
            this.connection = DriverManager.getConnection(url);
            this.dialect = mapDialect(url);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private SQLDialect mapDialect(String url) {
        String[] parts = url.split(":");
        if (!parts[0].equals("jdbc")) {
            throw new RuntimeException("Invalid database url");
        }
        switch (parts[1]) {
            case "mariadb" -> {
                return SQLDialect.MARIADB;
            }
            default -> throw new RuntimeException("Database not supported");
        }
    }

    public DSLContext create() {
        return DSL.using(connection, dialect);
    }
}
