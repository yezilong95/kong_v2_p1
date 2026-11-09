package io.nats.client.impl;

import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements Predicate {
    public final /* synthetic */ int a;
    public final /* synthetic */ NatsConnection b;

    public /* synthetic */ c(NatsConnection natsConnection, int i) {
        this.a = i;
        this.b = natsConnection;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.a) {
            case 0:
                return this.b.lambda$waitForConnectOrClose$16((Void) obj);
            default:
                return this.b.lambda$waitForDisconnectOrClose$15((Void) obj);
        }
    }
}
