package io.nats.client.impl;

import io.nats.client.support.NatsConstants;
import io.nats.client.support.NatsUri;

/* JADX INFO: loaded from: classes.dex */
public class ServerPoolEntry {
    public int failedAttempts;
    public boolean isGossiped;
    public long lastAttempt;
    public NatsUri nuri;

    public ServerPoolEntry(NatsUri natsUri, boolean z) {
        this.nuri = natsUri;
        this.isGossiped = z;
    }

    public String toString() {
        return this.nuri + NatsConstants.SPACE + this.isGossiped + "/" + this.failedAttempts;
    }
}
