package org.amoseman.nuguildchatbackend.pojo.user;

public class Signup {
    private final String username;
    private final String password;

    public Signup(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
