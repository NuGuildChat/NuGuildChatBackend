package org.amoseman.nuguildchatbackend.pojo.channel;

public class Channel {
    private final String name;
    private final String adminUsername;
    private final boolean closed;

    public Channel(String name, String adminUsername, boolean closed) {
        this.name = name;
        this.adminUsername = adminUsername;
        this.closed = closed;
    }

    public String getName() {
        return name;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public boolean isClosed() {
        return closed;
    }
}
