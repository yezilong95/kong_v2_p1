package io.nats.client.impl;

import io.nats.client.AuthHandler;
import io.nats.client.Connection;
import io.nats.client.Options;
import io.nats.client.Statistics;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class NatsImpl {
    public static Connection createConnection(Options options, boolean z) throws InterruptedException, IOException {
        NatsConnection natsConnection = new NatsConnection(options);
        natsConnection.connect(z);
        return natsConnection;
    }

    public static Statistics createEmptyStats() {
        return new NatsStatistics();
    }

    public static AuthHandler credentials(String str) {
        return new FileAuthHandler(str);
    }

    public static AuthHandler staticCredentials(byte[] bArr) {
        return new MemoryAuthHandler(bArr);
    }

    public static AuthHandler credentials(String str, String str2) {
        return new FileAuthHandler(str, str2);
    }

    public static AuthHandler staticCredentials(char[] cArr, char[] cArr2) {
        return new StringAuthHandler(cArr, cArr2);
    }
}
