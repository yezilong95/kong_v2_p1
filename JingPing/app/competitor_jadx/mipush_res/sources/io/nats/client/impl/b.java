package io.nats.client.impl;

import io.nats.client.Consumer;
import io.nats.client.ForceReconnectOptions;
import io.nats.client.impl.NatsConnection;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;
    public final /* synthetic */ Object G;

    public /* synthetic */ b(Object obj, int i, Object obj2) {
        this.E = i;
        this.F = obj;
        this.G = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                ((NatsConnection) this.F).lambda$executeCallback$13((NatsConnection.ErrorListenerCaller) this.G);
                break;
            case 1:
                ((NatsConnection) this.F).lambda$processException$11((Exception) this.G);
                break;
            case 2:
                ((NatsConnection) this.F).lambda$processError$12((String) this.G);
                break;
            case 3:
                ((NatsConnection) this.F).lambda$processSlowConsumer$10((Consumer) this.G);
                break;
            default:
                NatsConnection.lambda$forceReconnectImpl$0((ForceReconnectOptions) this.F, (DataPort) this.G);
                break;
        }
    }
}
