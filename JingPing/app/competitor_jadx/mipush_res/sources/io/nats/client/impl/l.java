package io.nats.client.impl;

import io.nats.client.ConnectionListener;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;
    public final /* synthetic */ Object G;
    public final /* synthetic */ Object H;

    public /* synthetic */ l(Object obj, Object obj2, Object obj3, int i) {
        this.E = i;
        this.F = obj;
        this.G = obj2;
        this.H = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                ((NatsConnection) this.F).lambda$processConnectionEvent$14((ConnectionListener) this.G, (ConnectionListener.Events) this.H);
                break;
            default:
                ((NatsConsumer) this.F).lambda$drain$0((Duration) this.G, (CompletableFuture) this.H);
                break;
        }
    }
}
