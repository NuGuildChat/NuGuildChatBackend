package org.amoseman.nuguildchatbackend.dao.sql;

import com.google.common.collect.ImmutableList;
import org.amoseman.nuguildchatbackend.dao.ChannelDAO;
import org.amoseman.nuguildchatbackend.dao.exception.channel.ChannelDoesNotExistException;
import org.amoseman.nuguildchatbackend.dao.exception.channel.ChannelNameInUseException;
import org.amoseman.nuguildchatbackend.dao.exception.user.UserDoesNotExistException;
import org.amoseman.nuguildchatbackend.pojo.channel.Channel;
import org.amoseman.nuguildchatbackend.pojo.channel.ChannelRecord;
import org.amoseman.nuguildchatbackend.pojo.channel.ChannelUpdate;
import org.jooq.Record;
import org.jooq.Result;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class SQLChannelDAO implements ChannelDAO {
    private final DatabaseConnection connection;

    public SQLChannelDAO(DatabaseConnection connection) {
        this.connection = connection;
    }

    private boolean isNameInUse(String name) {
        int result = connection.create()
                .select()
                .from(table("channels"))
                .where(field("name").eq(name))
                .execute();
        return result == 1;
    }

    private ChannelRecord recordAsChannel(Record record) {
        return new ChannelRecord(
                record.get(field("name"), String.class),
                record.get(field("admin"), String.class),
                record.get(field("closed"), Boolean.class),
                record.get(field("id"), Long.class),
                record.get(field("created"), Long.class),
                record.get(field("updated"), Long.class),
                ImmutableList.copyOf(record.get(field("members"), String.class).split(","))
        );
    }

    @Override
    public void create(Channel channel) throws ChannelNameInUseException {
        if (isNameInUse(channel.getName())) {
            throw new ChannelNameInUseException();
        }
        connection.create()
                .insertInto(
                        table("channels"),
                        field("name"),
                        field("admin"),
                        field("closed")
                )
                .values(channel.getName(), channel.getAdminUsername(), channel.isClosed())
                .execute();
    }

    @Override
    public void update(ChannelUpdate channel) throws ChannelDoesNotExistException {
        int result = connection.create()
                .update(table("channels"))
                .set(field("closed"), channel.isClosed())
                .set(field("members"), ChannelRecord.getMembersAsString(channel.getMembers()))
                .where(field("id").eq(channel.getID()))
                .execute();
        if (0 == result) {
            throw new ChannelDoesNotExistException();
        }
    }

    @Override
    public void delete(long id) throws ChannelDoesNotExistException {
        int result = connection.create()
                .deleteFrom(table("channels"))
                .where(field("id").eq(id))
                .execute();
        if (0 == result) {
            throw new ChannelDoesNotExistException();
        }
    }

    @Override
    public ChannelRecord get(long id) throws ChannelDoesNotExistException {
        Result<Record> result = connection.create()
                .select()
                .from(table("channels"))
                .where(field("id").eq(id))
                .fetch();
        if (result.isEmpty()) {
            throw new ChannelDoesNotExistException();
        }
        Record record = result.get(0);
        return recordAsChannel(record);
    }

    @Override
    public ImmutableList<ChannelRecord> getIfOpen() {
        Result<Record> result = connection.create()
                .select()
                .from(table("channels"))
                .where(field("closed").eq(false))
                .fetch();
        List<ChannelRecord> channels = new ArrayList<>();
        result.forEach(record -> channels.add(recordAsChannel(record)));
        return ImmutableList.copyOf(channels);
    }

    @Override
    public ImmutableList<ChannelRecord> getIfMember(String username) {
        Result<Record> result = connection.create()
                .select()
                .from(table("channels"))
                .where(field("members").contains(username))
                .fetch();
        List<ChannelRecord> channels = new ArrayList<>();
        result.forEach(record -> channels.add(recordAsChannel(record)));
        return ImmutableList.copyOf(channels);
    }
}
