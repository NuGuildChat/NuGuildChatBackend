package org.amoseman.nuguildchatbackend.pojo.user;

public class UserRecord extends User {
    private final String uuid;
    private final long created;
    private final long updated;

    public UserRecord(String name, String pronouns, String uuid, long created, long updated) {
        super(name, pronouns);
        this.uuid = uuid;
        this.created = created;
        this.updated = updated;
    }

    public String getUUID() {
        return uuid;
    }

    public long getCreated() {
        return created;
    }

    public long getUpdated() {
        return updated;
    }
}
