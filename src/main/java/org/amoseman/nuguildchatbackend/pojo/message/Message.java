package org.amoseman.nuguildchatbackend.pojo.message;

public class Message {
    private final String author;
    private final long channelID;
    private final String contents;

    public Message(String author, long channelID, String contents) {
        this.author = author;
        this.channelID = channelID;
        this.contents = contents;
    }

    public String getAuthor() {
        return author;
    }

    public long getChannelID() {
        return channelID;
    }

    public String getContents() {
        return contents;
    }
}
