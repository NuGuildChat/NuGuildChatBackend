package org.amoseman.nuchatbackend.pojo.channel;

public class Channel {
    private final String name;
    private final String adminUUID;
    private final boolean closed;

    public Channel(String name, String adminUUID, boolean closed) {
        this.name = name;
        this.adminUUID = adminUUID;
        this.closed = closed;
    }

    public String getName() {
        return name;
    }

    public String getAdminUUID() {
        return adminUUID;
    }

    public boolean isClosed() {
        return closed;
    }
}
