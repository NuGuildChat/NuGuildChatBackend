package org.amoseman.nuguildchatbackend.pojo.tag;

import com.google.common.collect.ImmutableList;

public interface TagDAO {
    void put(Tag tag);
    void remove(Tag tag);
    ImmutableList<Tag> list();
}
