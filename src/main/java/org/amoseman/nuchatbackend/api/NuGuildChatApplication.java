package org.amoseman.nuchatbackend.api;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Environment;
import org.amoseman.nuchatbackend.api.resources.ChannelResource;
import org.amoseman.nuchatbackend.api.resources.MessageResource;
import org.amoseman.nuchatbackend.dao.ChannelDAO;
import org.amoseman.nuchatbackend.dao.MessageDAO;
import org.amoseman.nuchatbackend.dao.sql.DatabaseConnection;
import org.amoseman.nuchatbackend.dao.sql.SQLMessageDAO;
import org.amoseman.nuchatbackend.service.ChannelService;
import org.amoseman.nuchatbackend.service.MessageService;
import org.amoseman.nuchatbackend.service.auth.UserAuthenticator;
import org.amoseman.nuchatbackend.service.auth.UserPrincipal;

import java.security.SecureRandom;

public class NuGuildChatApplication extends Application<NuGuildChatConfiguration> {
    @Override
    public void run(NuGuildChatConfiguration configuration, Environment environment) throws Exception {
        SecureRandom random = new SecureRandom();
        DatabaseConnection connection = new DatabaseConnection(configuration.getDatabaseURL());

        UserAuthenticator userAuthenticator = new UserAuthenticator(connection);
        environment.jersey().register(
                new AuthDynamicFeature(
                        new BasicCredentialAuthFilter.Builder<UserPrincipal>()
                                .setAuthenticator(userAuthenticator)
                                .buildAuthFilter()
                ));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(UserPrincipal.class));

        MessageDAO messageDAO = new SQLMessageDAO(connection);
        ChannelDAO channelDAO = null; // todo

        // todo: message service
        MessageService messageService = new MessageService(messageDAO);
        ChannelService channelService = new ChannelService(channelDAO);

        MessageResource messageResource = new MessageResource(messageService);
        ChannelResource channelResource = new ChannelResource(channelService);

        environment.jersey().register(messageResource);
        environment.jersey().register(channelResource);
    }
}
