package org.amoseman.nuguildchatbackend.service.auth;

import java.security.Principal;

public class UserPrincipal implements Principal {
    private final String uuid;

    public UserPrincipal(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object another) {
        // todo: implement
        return false;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String getName() {
        return uuid;
    }
}
