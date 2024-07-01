package org.amoseman.nuguildchatbackend.pojo.tag;

import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.Map;

public class TagService {
    private final TagDAO tagDAO;
    private final Map<String, Tag> tags;

    public TagService(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
        tags = new HashMap<>();
        tagDAO.list().forEach(tag -> tags.put(tag.getName(), tag));
    }

    public boolean put(Tag tag) {
        for (Tag child : tag.getChildren()) {
            if (!tags.containsKey(child.getName())) {
                return false;
            }
            if (child.match(tag)) { // check for loops
                return false;
            }
        }
        tags.put(tag.getName(), tag);
        tagDAO.put(tag);
        return true;
    }

    public boolean remove(Tag tag) {
        if (!tags.containsKey(tag.getName())) {
            return false;
        }
        tags.remove(tag.getName());
        tagDAO.remove(tag);
        return true;
    }

    public ImmutableList<Tag> list() {
        return ImmutableList.copyOf(tags.values());
    }
}
