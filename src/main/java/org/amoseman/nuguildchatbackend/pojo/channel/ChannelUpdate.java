package org.amoseman.nuguildchatbackend.pojo.channel;

import com.google.common.collect.ImmutableList;

public class ChannelUpdate {
    private final long id;
    private final boolean closed;
    private final ImmutableList<String> members;

    public ChannelUpdate(long id, boolean closed, ImmutableList<String> members) {
        this.id = id;
        this.closed = closed;
        this.members = members;
    }

    public long getID() {
        return id;
    }

    public boolean isClosed() {
        return closed;
    }

    public ImmutableList<String> getMembers() {
        return members;
    }
}
