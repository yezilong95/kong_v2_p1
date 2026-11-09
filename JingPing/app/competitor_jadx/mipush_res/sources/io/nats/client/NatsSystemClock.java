package io.nats.client;

/* JADX INFO: loaded from: classes.dex */
public final class NatsSystemClock {
    private static NatsSystemClockProvider PROVIDER = new NatsSystemClockProvider() { // from class: io.nats.client.NatsSystemClock.1
    };

    public static long currentTimeMillis() {
        return PROVIDER.currentTimeMillis();
    }

    public static long nanoTime() {
        return PROVIDER.nanoTime();
    }

    public static void setProvider(NatsSystemClockProvider natsSystemClockProvider) {
        if (natsSystemClockProvider == null) {
            natsSystemClockProvider = new NatsSystemClockProvider() { // from class: io.nats.client.NatsSystemClock.2
            };
        }
        PROVIDER = natsSystemClockProvider;
    }
}
