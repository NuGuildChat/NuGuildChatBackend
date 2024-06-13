package org.amoseman.nuguildchatbackend.pojo.message;

public class MessageUpdate {
    private final long id;
    private final String contents;

    public MessageUpdate(long id, String contents) {
        this.id = id;
        this.contents = contents;
    }

    public long getID() {
        return id;
    }

    public String getContents() {
        return contents;
    }
}
