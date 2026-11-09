package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;

/* JADX INFO: loaded from: classes.dex */
public class AccountLimits {
    private final long maxAckPending;
    private final boolean maxBytesRequired;
    private final long maxConsumers;
    private final long maxMemory;
    private final long maxStorage;
    private final long maxStreams;
    private final long memoryMaxStreamBytes;
    private final long storageMaxStreamBytes;

    public AccountLimits(JsonValue jsonValue) {
        this.maxMemory = JsonValueUtils.readLong(jsonValue, ApiConstants.MAX_MEMORY, 0L);
        this.maxStorage = JsonValueUtils.readLong(jsonValue, ApiConstants.MAX_STORAGE, 0L);
        this.maxStreams = JsonValueUtils.readLong(jsonValue, ApiConstants.MAX_STREAMS, 0L);
        this.maxConsumers = JsonValueUtils.readLong(jsonValue, ApiConstants.MAX_CONSUMERS, 0L);
        this.maxAckPending = JsonValueUtils.readLong(jsonValue, ApiConstants.MAX_ACK_PENDING, 0L);
        this.memoryMaxStreamBytes = JsonValueUtils.readLong(jsonValue, ApiConstants.MEMORY_MAX_STREAM_BYTES, 0L);
        this.storageMaxStreamBytes = JsonValueUtils.readLong(jsonValue, ApiConstants.STORAGE_MAX_STREAM_BYTES, 0L);
        this.maxBytesRequired = JsonValueUtils.readBoolean(jsonValue, ApiConstants.MAX_BYTES_REQUIRED);
    }

    public long getMaxAckPending() {
        return this.maxAckPending;
    }

    public long getMaxConsumers() {
        return this.maxConsumers;
    }

    public long getMaxMemory() {
        return this.maxMemory;
    }

    public long getMaxStorage() {
        return this.maxStorage;
    }

    public long getMaxStreams() {
        return this.maxStreams;
    }

    public long getMemoryMaxStreamBytes() {
        return this.memoryMaxStreamBytes;
    }

    public long getStorageMaxStreamBytes() {
        return this.storageMaxStreamBytes;
    }

    public boolean isMaxBytesRequired() {
        return this.maxBytesRequired;
    }
}
