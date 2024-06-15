package org.amoseman.nuguildchatbackend.dao;

import com.google.common.collect.ImmutableList;
import org.amoseman.nuguildchatbackend.dao.exception.channel.ChannelDoesNotExistException;
import org.amoseman.nuguildchatbackend.dao.exception.channel.ChannelNameInUseException;
import org.amoseman.nuguildchatbackend.pojo.channel.Channel;
import org.amoseman.nuguildchatbackend.pojo.channel.ChannelRecord;
import org.amoseman.nuguildchatbackend.pojo.channel.ChannelUpdate;

public interface ChannelDAO {
    void create(Channel channel) throws ChannelNameInUseException;
    void update(ChannelUpdate channel) throws ChannelDoesNotExistException;
    void delete(long id) throws ChannelDoesNotExistException;
    ChannelRecord get(long id) throws ChannelDoesNotExistException;
    ImmutableList<ChannelRecord> getIfOpen();
    ImmutableList<ChannelRecord> getIfMember(String username);
}
