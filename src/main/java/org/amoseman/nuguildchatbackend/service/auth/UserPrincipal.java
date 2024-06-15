package org.amoseman.nuguildchatbackend.service.auth;

import java.security.Principal;

public class UserPrincipal implements Principal {
    private final String username;

    public UserPrincipal(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object another) {
        if (null == another) {
            return false;
        }
        if (!(another instanceof UserPrincipal other)) {
            return false;
        }
        return username.equals(other.username);
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String getName() {
        return username;
    }
}
