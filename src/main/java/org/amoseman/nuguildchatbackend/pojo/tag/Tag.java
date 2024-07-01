package org.amoseman.nuguildchatbackend.pojo.tag;

import com.google.common.collect.ImmutableList;

/**
 * Represents a tag.
 */
public class Tag {
    private final String name;
    private final ImmutableList<Tag> children;

    /**
     * Instantiate a tag.
     * @param name the unique name of the tag.
     * @param children the child tags of the tag.
     */
    public Tag(String name, ImmutableList<Tag> children) {
        this.name = name;
        this.children = children;
    }

    @Override
    public boolean equals(Object t) {
        if (null == t) {
            return false;
        }
        if (getClass() != t.getClass()) {
            return false;
        }
        if (this == t) {
            return true;
        }
        Tag target = (Tag) t;
        return target.name.equals(name);
    }

    /**
     * Determine if this tag is the target, or one of its children is the target.
     * @param target the target tag.
     * @return the result of the match check.
     */
    public boolean match(Tag target) {
        return searchHelper(target, this);
    }

    private boolean searchHelper(Tag target, Tag current) {
        if (current.name.equals(target.name)) {
            return true;
        }
        ImmutableList<Tag> children = current.children;
        for (Tag child : children) {
            if (searchHelper(target, child)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public ImmutableList<Tag> getChildren() {
        return children;
    }
}
