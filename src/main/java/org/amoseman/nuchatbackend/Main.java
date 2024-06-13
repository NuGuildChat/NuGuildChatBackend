package org.amoseman.nuchatbackend;

import org.amoseman.nuchatbackend.api.NuGuildChatApplication;

public class Main {
    public static void main(String[] args) throws Exception {
        new NuGuildChatApplication().run("server", "config.yaml");
    }
}