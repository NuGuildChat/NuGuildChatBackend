package org.amoseman.nuguildchatbackend.pojo.user;

public class UserRecord extends User {
    private final String username;
    private final long created;
    private final long updated;

    public UserRecord(String display_name, String pronouns, String username, long created, long updated) {
        super(display_name, pronouns);
        this.username = username;
        this.created = created;
        this.updated = updated;
    }

    public String getUUID() {
        return username;
    }

    public long getCreated() {
        return created;
    }

    public long getUpdated() {
        return updated;
    }
}
