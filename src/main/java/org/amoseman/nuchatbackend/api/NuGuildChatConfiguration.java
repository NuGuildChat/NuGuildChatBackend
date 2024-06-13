package org.amoseman.nuchatbackend.api;

import io.dropwizard.core.Configuration;

public class NuGuildChatConfiguration extends Configuration {
    private final String databaseURL;
    private final String databaseUsername;
    private final String databasePassword;

    public NuGuildChatConfiguration(String databaseURL, String databaseUsername, String databasePassword) {
        this.databaseURL = databaseURL;
        this.databaseUsername = databaseUsername;
        this.databasePassword = databasePassword;
    }

    public String getDatabaseURL() {
        return databaseURL;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }
}
