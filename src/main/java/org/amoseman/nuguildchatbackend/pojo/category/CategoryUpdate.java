package org.amoseman.nuguildchatbackend.pojo.category;

import com.google.common.collect.ImmutableList;

public class CategoryUpdate {
    private final long id;
    private final String name;
    private final ImmutableList<Long> channels;

    public CategoryUpdate(long id, String name, ImmutableList<Long> channels) {
        this.id = id;
        this.name = name;
        this.channels = channels;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ImmutableList<Long> getChannels() {
        return channels;
    }
}
