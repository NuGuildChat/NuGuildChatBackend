package org.amoseman.nuguildchatbackend.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;
import jakarta.validation.constraints.NotEmpty;

public class NuGuildChatConfiguration extends Configuration {
    @NotEmpty
    private String databaseURL;
    @NotEmpty
    private String databaseUsername;
    @NotEmpty
    private String databasePassword;

    @JsonProperty

    public String getDatabaseURL() {
        return databaseURL;
    }

    @JsonProperty
    public String getDatabaseUsername() {
        return databaseUsername;
    }

    @JsonProperty
    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }

    @JsonProperty
    public String getDatabasePassword() {
        return databasePassword;
    }

    @JsonProperty
    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }
}
