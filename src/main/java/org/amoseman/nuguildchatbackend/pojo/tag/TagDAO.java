package org.amoseman.nuguildchatbackend.pojo.tag;

import com.google.common.collect.ImmutableList;

public interface TagDAO {
    TagDAO SINGLETON = new TagDAO() {
        @Override
        public void put(Tag tag) {

        }

        @Override
        public void remove(Tag tag) {

        }

        @Override
        public ImmutableList<Tag> list() {
            return ImmutableList.of();
        }
    };

    void put(Tag tag);
    void remove(Tag tag);
    ImmutableList<Tag> list();
}
