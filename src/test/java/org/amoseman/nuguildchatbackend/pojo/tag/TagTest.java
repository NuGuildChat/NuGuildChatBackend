package org.amoseman.nuguildchatbackend.pojo.tag;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagTest {

    @Test
    void match() {
        Tag alice = new Tag("alice");
        Tag bob = new Tag("bob", ImmutableList.of(alice));
        Tag clyde = new Tag("clyde", ImmutableList.of(bob));

        assertTrue(alice.match(alice));
        assertTrue(clyde.match(alice));
        assertFalse(bob.match(clyde));
    }
}