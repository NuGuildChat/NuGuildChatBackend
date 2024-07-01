package org.amoseman.nuguildchatbackend.pojo.tag;

import com.google.common.collect.ImmutableList;

/**
 * Represents a tag.
 */
public class Tag {
    private final String name;
    private final ImmutableList<String> children;

    /**
     * Instantiate a tag.
     * @param name the unique name of the tag.
     * @param children the names of the children tags.
     */
    public Tag(String name, ImmutableList<String> children) {
        this.name = name;
        this.children = children;
    }

    /**
     * Instantiate a tag.
     * @param name the unique name of the tag.
     */
    public Tag(String name) {
        this.name = name;
        this.children = ImmutableList.of();
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

    public String getName() {
        return name;
    }

    public ImmutableList<String> getChildren() {
        return children;
    }
}
