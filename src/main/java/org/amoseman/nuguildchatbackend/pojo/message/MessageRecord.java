package org.amoseman.nuguildchatbackend.pojo.message;

public class MessageRecord extends Message {
    private final long id;
    private final long created;
    private final long updated;

    public MessageRecord(String authorUUID, long channelID, String contents, long id, long created, long updated) {
        super(authorUUID, channelID, contents);
        this.id = id;
        this.created = created;
        this.updated = updated;
    }

    public long getId() {
        return id;
    }

    public long getCreated() {
        return created;
    }

    public long getUpdated() {
        return updated;
    }
}
