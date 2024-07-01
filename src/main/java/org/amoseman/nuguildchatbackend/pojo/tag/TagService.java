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

    public boolean match(Tag tag, Tag target) {
        if (!exists(tag) || !exists(target)) {
            return false;
        }
        return matchHelper(tag, target);
    }

    private boolean matchHelper(Tag current, Tag target) {
        if (current.equals(target)) {
            return true;
        }
        for (String child : current.getChildren()) {
            if (matchHelper(get(child), target)) {
                return true;
            }
        }
        return false;
    }

    public Tag get(String name) {
        return tags.get(name);
    }

    public boolean exists(String name) {
        return tags.containsKey(name);
    }

    public boolean exists(Tag tag) {
        return exists(tag.getName());
    }

    public boolean put(Tag tag) {
        for (String child : tag.getChildren()) {
            if (!exists(child)) {
                return false;
            }
            if (match(tag, get(child))) { // check for loops
                return false;
            }
        }
        tags.put(tag.getName(), tag);
        tagDAO.put(tag);
        return true;
    }

    public boolean remove(Tag tag) {
        if (!exists(tag)) {
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
