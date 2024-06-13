package org.amoseman.nuguildchatbackend.pojo.user;

public class User {
    private final String name;
    private final String pronouns;

    public User(String name, String pronouns) {
        this.name = name;
        this.pronouns = pronouns;
    }

    public String getName() {
        return name;
    }

    public String getPronouns() {
        return pronouns;
    }
}
