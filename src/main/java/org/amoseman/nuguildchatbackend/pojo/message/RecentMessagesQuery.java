package org.amoseman.nuguildchatbackend.pojo.message;

public class RecentMessagesQuery {
    private long limit;
    private long offset;

    public RecentMessagesQuery(long limit, long offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public long getLimit() {
        return limit;
    }

    public long getOffset() {
        return offset;
    }
}
