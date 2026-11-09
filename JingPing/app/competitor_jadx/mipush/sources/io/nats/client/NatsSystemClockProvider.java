package io.nats.client;

/* JADX INFO: loaded from: classes.dex */
public interface NatsSystemClockProvider {
    default long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    default long nanoTime() {
        return System.nanoTime();
    }
}
