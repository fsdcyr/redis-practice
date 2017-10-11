package com.fsdcyr.domain;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * Created by fsdcyr on 2017/10/11
 */
public class ProtostuffSerializer {
    private Schema<Club> schema = RuntimeSchema.createFrom(Club.class);

    public byte[] serialize(final Club club) {
        final LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            return serializeInternal(club, schema, buffer);
        } catch (final Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    public Club deserialize(final byte[] bytes) {
        try {
            Club club = deserializeInternal(bytes, schema.newMessage(), schema);
            if (club != null) {
                return club;
            }
        } catch (final Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        return null;
    }

    private <T> byte[] serializeInternal(final T source, final Schema<T> schema, final LinkedBuffer buffer) {
        return ProtobufIOUtil.toByteArray(source, schema, buffer);
    }

    private <T> T deserializeInternal(final byte[] bytes, final T result, final Schema<T> schema) {
        ProtobufIOUtil.mergeFrom(bytes, result, schema);
        return result;
    }
}
