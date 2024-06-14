package org.amoseman.nuguildchatbackend.dao.sql;

import com.google.common.collect.ImmutableList;
import org.amoseman.nuguildchatbackend.dao.MessageDAO;
import org.amoseman.nuguildchatbackend.dao.exception.message.MessageDoesNotExistException;
import org.amoseman.nuguildchatbackend.pojo.message.Message;
import org.amoseman.nuguildchatbackend.pojo.message.MessageRecord;
import org.amoseman.nuguildchatbackend.pojo.message.MessageUpdate;
import org.jooq.Record;
import org.jooq.Result;

import java.util.*;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class SQLMessageDAO implements MessageDAO {
    private final DatabaseConnection connection;

    public SQLMessageDAO(DatabaseConnection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Message message) {
        long now = System.currentTimeMillis();
        connection.create()
                .insertInto(
                        table("messages"),
                        field("created"),
                        field("updated"),
                        field("author"),
                        field("channel"),
                        field("contents")
                )
                .values(
                        now,
                        now,
                        message.getAuthorUUID(),
                        message.getChannelID(),
                        message.getContents()
                )
                .execute();
    }

    @Override
    public void update(MessageUpdate message) throws MessageDoesNotExistException {
        long now = System.currentTimeMillis();
        int result = connection.create()
                .update(table("messages"))
                .set(field("updated"), now)
                .set(field("contents"), message.getContents())
                .where(field("id").eq(message.getID()))
                .execute();
        if (0 == result) {
            throw new MessageDoesNotExistException();
        }
    }

    @Override
    public void delete(long id) throws MessageDoesNotExistException {
        int result = connection.create()
                .deleteFrom(table("messages"))
                .where(field("id").eq(id))
                .execute();
        if (result == 0) {
            throw new MessageDoesNotExistException();
        }
    }

    @Override
    public ImmutableList<MessageRecord> getAll(String channelUUID) {
        Result<Record> result = connection.create()
                .select()
                .from(table("messages"))
                .where(field("channel").eq(channelUUID))
                .fetch();
        List<MessageRecord> messages = new ArrayList<>();
        result.forEach(record -> messages.add(recordAsMessage(record)));
        messages.sort(Comparator.comparingLong(MessageRecord::getCreated));
        return ImmutableList.copyOf(messages);
    }

    private MessageRecord recordAsMessage(Record record) {
        return new MessageRecord(
                record.get(field("author"), String.class),
                record.get(field("channel"), String.class),
                record.get(field("contents"), String.class),
                record.get(field("id"), Long.class),
                record.get(field("created"), Long.class),
                record.get(field("updated"), Long.class)
        );
    }
}
