package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;

/* JADX INFO: loaded from: classes.dex */
public class AccountTier {
    private final int consumers;
    private final AccountLimits limits;
    private final long memory;
    private final long reservedMemory;
    private final long reservedStorage;
    private final long storage;
    private final int streams;

    public AccountTier(JsonValue jsonValue) {
        this.memory = JsonValueUtils.readInteger(jsonValue, ApiConstants.MEMORY, 0);
        this.storage = JsonValueUtils.readInteger(jsonValue, ApiConstants.STORAGE, 0);
        this.reservedMemory = JsonValueUtils.readInteger(jsonValue, ApiConstants.RESERVED_MEMORY, 0);
        this.reservedStorage = JsonValueUtils.readInteger(jsonValue, ApiConstants.RESERVED_STORAGE, 0);
        this.streams = JsonValueUtils.readInteger(jsonValue, ApiConstants.STREAMS, 0);
        this.consumers = JsonValueUtils.readInteger(jsonValue, ApiConstants.CONSUMERS, 0);
        this.limits = new AccountLimits(JsonValueUtils.readObject(jsonValue, ApiConstants.LIMITS));
    }

    public int getConsumers() {
        return this.consumers;
    }

    public AccountLimits getLimits() {
        return this.limits;
    }

    @Deprecated
    public int getMemory() {
        return (int) this.memory;
    }

    public long getMemoryBytes() {
        return this.memory;
    }

    public long getReservedMemory() {
        return (int) this.reservedMemory;
    }

    public long getReservedMemoryBytes() {
        return this.reservedMemory;
    }

    public long getReservedStorage() {
        return this.reservedStorage;
    }

    public long getReservedStorageBytes() {
        return this.reservedStorage;
    }

    @Deprecated
    public int getStorage() {
        return (int) this.storage;
    }

    public long getStorageBytes() {
        return this.storage;
    }

    public int getStreams() {
        return this.streams;
    }
}
