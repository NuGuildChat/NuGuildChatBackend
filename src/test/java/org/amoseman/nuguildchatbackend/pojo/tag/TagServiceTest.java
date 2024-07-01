package org.amoseman.nuguildchatbackend.pojo.tag;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagServiceTest {

    @Test
    void match() {
        TagService service = new TagService(TagDAO.SINGLETON);
        Tag alice = new Tag("alice", ImmutableList.of());
        assertTrue(service.put(alice));
        Tag bob = new Tag("bob", ImmutableList.of("alice"));
        assertTrue(service.put(bob));
        Tag clyde = new Tag("clyde", ImmutableList.of("bob"));
        assertTrue(service.put(clyde));

        assertTrue(service.match(clyde, alice));
        assertTrue(service.match(bob, alice));
        assertFalse(service.match(alice, clyde));

        // attempt to create loop should fail
        Tag aliceUpdate = new Tag("alice", ImmutableList.of("clyde"));
        assertFalse(service.put(aliceUpdate));
    }
}