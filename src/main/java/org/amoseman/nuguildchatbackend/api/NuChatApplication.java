package org.amoseman.nuguildchatbackend.api;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Environment;
import org.amoseman.nuguildchatbackend.api.resources.ChannelResource;
import org.amoseman.nuguildchatbackend.api.resources.MessageResource;
import org.amoseman.nuguildchatbackend.dao.ChannelDAO;
import org.amoseman.nuguildchatbackend.dao.MessageDAO;
import org.amoseman.nuguildchatbackend.dao.sql.DatabaseConnection;
import org.amoseman.nuguildchatbackend.dao.sql.SQLMessageDAO;
import org.amoseman.nuguildchatbackend.service.ChannelService;
import org.amoseman.nuguildchatbackend.service.auth.UserAuthenticator;
import org.amoseman.nuguildchatbackend.service.auth.UserPrincipal;

import java.security.SecureRandom;

public class NuChatApplication extends Application<NuChatConfiguration> {
    @Override
    public void run(NuChatConfiguration configuration, Environment environment) throws Exception {
        SecureRandom random = new SecureRandom();
        DatabaseConnection connection = new DatabaseConnection(configuration.getDatabaseURL());

        UserAuthenticator userAuthenticator = new UserAuthenticator(connection);
        environment.jersey().register(
                new AuthDynamicFeature(
                        new BasicCredentialAuthFilter.Builder<UserPrincipal>()
                                .setAuthenticator(userAuthenticator)
                                .buildAuthFilter()
                ));

        MessageDAO messageDAO = new SQLMessageDAO(connection);
        ChannelDAO channelDAO = null; // todo

        // todo: message service
        ChannelService channelService = new ChannelService(channelDAO);

        MessageResource messageResource = new MessageResource(messageDAO);
        ChannelResource channelResource = new ChannelResource(channelService);

        environment.jersey().register(messageResource);
        environment.jersey().register(channelResource);
    }
}
