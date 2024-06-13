package org.amoseman.nuchatbackend;

import org.amoseman.nuchatbackend.api.NuChatApplication;

public class Main {
    public static void main(String[] args) throws Exception {
        new NuChatApplication().run("server", "config.yaml");
    }
}