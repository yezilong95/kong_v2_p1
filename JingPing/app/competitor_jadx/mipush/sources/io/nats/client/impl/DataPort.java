package io.nats.client.impl;

import io.nats.client.Options;
import io.nats.client.support.NatsUri;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public interface DataPort {
    default void afterConstruct(Options options) {
    }

    void close() throws IOException;

    default void connect(NatsConnection natsConnection, NatsUri natsUri, long j) throws IOException {
        connect(natsUri.toString(), natsConnection, j);
    }

    void connect(String str, NatsConnection natsConnection, long j) throws IOException;

    void flush() throws IOException;

    default void forceClose() throws IOException {
        close();
    }

    int read(byte[] bArr, int i, int i2) throws IOException;

    void shutdownInput() throws IOException;

    void upgradeToSecure() throws IOException;

    void write(byte[] bArr, int i) throws IOException;
}
