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
    private int maxUsernameLength;
    private int maxPasswordLength;
    private int maxPronounsLength;
    private int maxMessageLength;
    private int maxChannelNameLength;
    private int maxChannelMembers;


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

    @JsonProperty
    public void setDatabaseURL(String databaseURL) {
        this.databaseURL = databaseURL;
    }

    @JsonProperty
    public int getMaxUsernameLength() {
        return maxUsernameLength;
    }

    @JsonProperty
    public void setMaxUsernameLength(int maxUsernameLength) {
        this.maxUsernameLength = maxUsernameLength;
    }

    @JsonProperty
    public int getMaxPasswordLength() {
        return maxPasswordLength;
    }

    @JsonProperty
    public void setMaxPasswordLength(int maxPasswordLength) {
        this.maxPasswordLength = maxPasswordLength;
    }

    @JsonProperty
    public int getMaxMessageLength() {
        return maxMessageLength;
    }

    @JsonProperty
    public void setMaxMessageLength(int maxMessageLength) {
        this.maxMessageLength = maxMessageLength;
    }

    @JsonProperty
    public int getMaxChannelNameLength() {
        return maxChannelNameLength;
    }

    @JsonProperty
    public void setMaxChannelNameLength(int maxChannelNameLength) {
        this.maxChannelNameLength = maxChannelNameLength;
    }

    @JsonProperty
    public int getMaxChannelMembers() {
        return maxChannelMembers;
    }

    @JsonProperty
    public void setMaxChannelMembers(int maxChannelMembers) {
        this.maxChannelMembers = maxChannelMembers;
    }

    @JsonProperty
    public int getMaxPronounsLength() {
        return maxPronounsLength;
    }

    @JsonProperty
    public void setMaxPronounsLength(int maxPronounsLength) {
        this.maxPronounsLength = maxPronounsLength;
    }
}
