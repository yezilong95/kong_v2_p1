package io.nats.client;

import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public interface JetStreamReader {
    Message nextMessage(long j) throws InterruptedException;

    Message nextMessage(Duration duration) throws InterruptedException;

    void stop();
}
