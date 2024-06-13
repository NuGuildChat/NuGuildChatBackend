package org.amoseman.nuguildchatbackend.pojo.message;

public class Message {
    private final String authorUUID;
    private final String channelUUID;
    private final String contents;

    public Message(String authorUUID, String channelUUID, String contents) {
        this.authorUUID = authorUUID;
        this.channelUUID = channelUUID;
        this.contents = contents;
    }

    public String getAuthorUUID() {
        return authorUUID;
    }

    public String getChannelUUID() {
        return channelUUID;
    }

    public String getContents() {
        return contents;
    }
}
