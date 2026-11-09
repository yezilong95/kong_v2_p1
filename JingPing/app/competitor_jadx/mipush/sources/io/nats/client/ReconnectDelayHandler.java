package io.nats.client;

import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public interface ReconnectDelayHandler {
    Duration getWaitTime(long j);
}
