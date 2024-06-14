package org.amoseman.nuguildchatbackend.pojo.channel;

import com.google.common.collect.ImmutableList;

public class ChannelRecord extends Channel {
    private final long id;
    private final long created;
    private final long updated;
    private final ImmutableList<String> members;

    public ChannelRecord(String name, String adminUsername, boolean closed, long id, long created, long updated, ImmutableList<String> members) {
        super(name, adminUsername, closed);
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.members = members;
    }

    public long getID() {
        return id;
    }

    public long getCreated() {
        return created;
    }

    public long getUpdated() {
        return updated;
    }

    public ImmutableList<String> getMembers() {
        return members;
    }

    public static String getMembersAsString(ImmutableList<String> members) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < members.size() - 1; i++) {
            builder.append(members.get(i)).append(',');
        }
        builder.append(members.get(members.size() - 1));
        return builder.toString();
    }
}
