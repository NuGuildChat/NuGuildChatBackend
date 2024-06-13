package org.amoseman.nuchatbackend.pojo.message;

public class Message {
    private final String authorUUID;
    private final long channelID;
    private final String contents;

    public Message(String authorUUID, long channelID, String contents) {
        this.authorUUID = authorUUID;
        this.channelID = channelID;
        this.contents = contents;
    }

    public String getAuthorUUID() {
        return authorUUID;
    }

    public long getChannelID() {
        return channelID;
    }

    public String getContents() {
        return contents;
    }
}
