package org.amoseman.nuguildchatbackend.pojo.user;

public class UserUpdate {
    private final String uuid;
    private final String name;
    private final String pronouns;

    public UserUpdate(String uuid, String name, String pronouns) {
        this.uuid = uuid;
        this.name = name;
        this.pronouns = pronouns;
    }

    public String getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getPronouns() {
        return pronouns;
    }
}
