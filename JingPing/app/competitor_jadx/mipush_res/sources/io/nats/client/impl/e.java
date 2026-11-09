package io.nats.client.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ e(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    @Override // java.lang.Runnable
    public final void run() throws ExecutionException, InterruptedException, URISyntaxException, TimeoutException, IOException {
        switch (this.E) {
            case 0:
                ((NatsConnection) this.F).lambda$tryToConnect$4();
                break;
            case 1:
                ((NatsConnection) this.F).lambda$tryToConnect$5();
                break;
            case 2:
                ((NatsConnection) this.F).lambda$handleCommunicationIssue$6();
                break;
            default:
                ((MessageManager) this.F).lambda$initOrResetHeartbeatTimer$1();
                break;
        }
    }
}
