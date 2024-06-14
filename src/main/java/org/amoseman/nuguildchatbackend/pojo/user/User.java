package org.amoseman.nuguildchatbackend.pojo.user;

public class User {
    private final String displayName;
    private final String pronouns;

    public User(String displayName, String pronouns) {
        this.displayName = displayName;
        this.pronouns = pronouns;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPronouns() {
        return pronouns;
    }
}
