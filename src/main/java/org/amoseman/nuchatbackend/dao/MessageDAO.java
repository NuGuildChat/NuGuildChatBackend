package org.amoseman.nuchatbackend.dao;

import com.google.common.collect.ImmutableList;
import org.amoseman.nuchatbackend.dao.exception.message.MessageDoesNotExistException;
import org.amoseman.nuchatbackend.pojo.message.Message;
import org.amoseman.nuchatbackend.pojo.message.MessageRecord;
import org.amoseman.nuchatbackend.pojo.message.MessageUpdate;

public interface MessageDAO {
    void create(Message message);
    void update(MessageUpdate messageUpdate) throws MessageDoesNotExistException;
    void delete(long id) throws MessageDoesNotExistException;
    ImmutableList<MessageRecord> getAll(String channelUUID);
}
