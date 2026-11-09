package io.nats.client.impl;

import io.nats.client.Connection;
import io.nats.client.ErrorListener;
import io.nats.client.impl.NatsConnection;
import io.nats.client.support.Status;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class t implements NatsConnection.ErrorListenerCaller {
    public final /* synthetic */ int a;
    public final /* synthetic */ MessageManager b;
    public final /* synthetic */ Status c;

    public /* synthetic */ t(MessageManager messageManager, Status status, int i) {
        this.a = i;
        this.b = messageManager;
        this.c = status;
    }

    @Override // io.nats.client.impl.NatsConnection.ErrorListenerCaller
    public final void call(Connection connection, ErrorListener errorListener) {
        switch (this.a) {
            case 0:
                ((PullMessageManager) this.b).lambda$manageStatus$0(this.c, connection, errorListener);
                break;
            case 1:
                ((PullMessageManager) this.b).lambda$manageStatus$1(this.c, connection, errorListener);
                break;
            case 2:
                ((PullMessageManager) this.b).lambda$manageStatus$2(this.c, connection, errorListener);
                break;
            default:
                ((PushMessageManager) this.b).lambda$manageStatus$0(this.c, connection, errorListener);
                break;
        }
    }
}
