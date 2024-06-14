package org.amoseman.nuguildchatbackend.dao;

import com.google.common.collect.ImmutableList;
import org.amoseman.nuguildchatbackend.dao.exception.message.MessageDoesNotExistException;
import org.amoseman.nuguildchatbackend.pojo.message.Message;
import org.amoseman.nuguildchatbackend.pojo.message.MessageRecord;
import org.amoseman.nuguildchatbackend.pojo.message.MessageUpdate;

public interface MessageDAO {
    void create(Message message);
    void update(MessageUpdate messageUpdate) throws MessageDoesNotExistException;
    void delete(long id) throws MessageDoesNotExistException;
    MessageRecord get(long id) throws MessageDoesNotExistException;
    ImmutableList<MessageRecord> getAll(long channelID);
}
