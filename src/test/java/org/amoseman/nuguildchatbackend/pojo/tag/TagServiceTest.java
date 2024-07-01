package org.amoseman.nuguildchatbackend.pojo.tag;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagServiceTest {
    private TagService service;

    @BeforeEach
    void setUp() {
        service = new TagService(TagDAO.SINGLETON);
        Tag alice = new Tag("alice", ImmutableList.of());
        assertTrue(service.put(alice));
        Tag bob = new Tag("bob", ImmutableList.of("alice"));
        assertTrue(service.put(bob));
        Tag clyde = new Tag("clyde", ImmutableList.of("bob"));
        assertTrue(service.put(clyde));
    }

    @Test
    void match() {
        Tag alice = service.get("alice");
        Tag bob = service.get("bob");
        Tag clyde = service.get("clyde");

        assertTrue(service.match(clyde, alice));
        assertTrue(service.match(bob, alice));
        assertFalse(service.match(alice, clyde));

        // attempt to create loop should fail
        Tag aliceUpdate = new Tag("alice", ImmutableList.of("clyde"));
        assertFalse(service.put(aliceUpdate));
    }

    @Test
    void stats() {
        Tag alice = service.get("alice");
        Tag bob = service.get("bob");
        Tag clyde = service.get("clyde");

        TagStats aliceStats = service.stats(alice);
        TagStats bobStats = service.stats(bob);
        TagStats clydeStats = service.stats(clyde);

        assertEquals(0, aliceStats.treeDepth());
        assertEquals(0, aliceStats.childCount());
        assertEquals(1, bobStats.treeDepth());
        assertEquals(1, bobStats.childCount());
        assertEquals(2, clydeStats.treeDepth());
        assertEquals(2, clydeStats.childCount());
    }
}