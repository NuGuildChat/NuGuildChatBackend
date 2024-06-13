package org.amoseman.nuchatbackend.dao;

import com.google.common.collect.ImmutableList;
import org.amoseman.nuchatbackend.dao.exception.channel.ChannelDoesNotExistException;
import org.amoseman.nuchatbackend.dao.exception.channel.ChannelNameInUseException;
import org.amoseman.nuchatbackend.dao.exception.user.UserDoesNotExistException;
import org.amoseman.nuchatbackend.pojo.channel.Channel;
import org.amoseman.nuchatbackend.pojo.channel.ChannelRecord;
import org.amoseman.nuchatbackend.pojo.channel.ChannelUpdate;

public interface ChannelDAO {
    void create(Channel channel) throws ChannelNameInUseException;
    void update(ChannelUpdate channel) throws ChannelDoesNotExistException;
    void delete(long id) throws ChannelDoesNotExistException;
    ChannelRecord get(long id) throws ChannelDoesNotExistException;
    ImmutableList<ChannelRecord> getIfOpen();
    ImmutableList<ChannelRecord> getIfMember(String userUUID) throws UserDoesNotExistException;
}
