package org.amoseman.nuguildchatbackend.service;

import com.google.common.collect.ImmutableList;
import org.amoseman.nuguildchatbackend.dao.ChannelDAO;
import org.amoseman.nuguildchatbackend.dao.exception.channel.ChannelDoesNotExistException;
import org.amoseman.nuguildchatbackend.dao.exception.channel.ChannelNameInUseException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserAuthorizationException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserDoesNotExistException;
import org.amoseman.nuguildchatbackend.pojo.channel.Channel;
import org.amoseman.nuguildchatbackend.pojo.channel.ChannelRecord;
import org.amoseman.nuguildchatbackend.pojo.channel.ChannelUpdate;

public class ChannelService {
    private final ChannelDAO channelDAO;

    public ChannelService(ChannelDAO channelDAO) {
        this.channelDAO = channelDAO;
    }

    public void create(Channel channel) throws ChannelNameInUseException {
        channelDAO.create(channel);
    }
    public void update(String userUUID, ChannelUpdate channel) throws ChannelDoesNotExistException, UserAuthorizationException {
        ChannelRecord current = channelDAO.get(channel.getID());
        if (!userUUID.equals(current.getAdminUUID())) {
            throw new UserAuthorizationException();
        }
        channelDAO.update(channel);
    }
    public void delete(String userUUID, long id) throws ChannelDoesNotExistException, UserAuthorizationException {
        ChannelRecord current = channelDAO.get(id);
        if (!userUUID.equals(current.getAdminUUID())) {
            throw new UserAuthorizationException();
        }
        channelDAO.delete(id);
    }
    public ChannelRecord get(String userUUID, long id) throws ChannelDoesNotExistException, UserAuthorizationException {
        ChannelRecord channel = channelDAO.get(id);
        if (channel.isClosed() && !channel.getMembers().contains(userUUID)) {
            throw new UserAuthorizationException();
        }
        return channel;
    }

    public ImmutableList<ChannelRecord> getIfOpen() {
        return channelDAO.getIfOpen();
    }

    public ImmutableList<ChannelRecord> getIfMember(String userUUID) throws UserDoesNotExistException {
        return channelDAO.getIfMember(userUUID);
    }
}
