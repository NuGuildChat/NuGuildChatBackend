package org.amoseman.nuguildchatbackend.pojo.user;

public class UserUpdate {
    private final String username;
    private final String displayName;
    private final String pronouns;

    public UserUpdate(String username, String displayName, String pronouns) {
        this.username = username;
        this.displayName = displayName;
        this.pronouns = pronouns;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPronouns() {
        return pronouns;
    }
}
