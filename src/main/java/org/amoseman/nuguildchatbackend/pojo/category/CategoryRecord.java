package org.amoseman.nuguildchatbackend.pojo.category;

import com.google.common.collect.ImmutableList;

public class CategoryRecord extends Category {
    private final long id;
    private final long created;
    private final long updated;
    private final ImmutableList<Long> channels;

    public CategoryRecord(String name, String adminUsername, long id, long created, long updated, ImmutableList<Long> channels) {
        super(name, adminUsername);
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.channels = channels;
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

    public ImmutableList<Long> getChannels() {
        return channels;
    }
}
