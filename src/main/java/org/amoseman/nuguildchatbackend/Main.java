package org.amoseman.nuguildchatbackend;

import org.amoseman.nuguildchatbackend.api.NuGuildChatApplication;

public class Main {
    public static void main(String[] args) throws Exception {
        new NuGuildChatApplication().run("server", "config.yaml");
    }
}