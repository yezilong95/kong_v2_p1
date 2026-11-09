package io.nats.client.impl;

import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class u implements Function {
    public final /* synthetic */ int a;
    public final /* synthetic */ MessageManager b;

    public /* synthetic */ u(MessageManager messageManager, int i) {
        this.a = i;
        this.b = messageManager;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.a) {
            case 0:
                return ((PullMessageManager) this.b).beforeQueueProcessorImpl((NatsMessage) obj);
            default:
                return ((PushMessageManager) this.b).beforeQueueProcessorImpl((NatsMessage) obj);
        }
    }
}
