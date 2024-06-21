package org.amoseman.nuguildchatbackend.pojo.category;

public class Category {
    private String name;
    private String adminUsername;

    public Category(String name, String adminUsername) {
        this.name = name;
        this.adminUsername = adminUsername;
    }

    public String getName() {
        return name;
    }

    public String getAdminUsername() {
        return adminUsername;
    }
}
