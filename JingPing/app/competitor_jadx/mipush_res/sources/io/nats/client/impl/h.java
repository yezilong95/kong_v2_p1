package io.nats.client.impl;

import io.nats.client.api.StreamInfo;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h implements Consumer {
    public final /* synthetic */ int a;

    public /* synthetic */ h(int i) {
        this.a = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.a) {
            case 0:
                ((NatsConsumer) obj).markUnsubedForDrain();
                break;
            default:
                NatsJetStreamImpl.lambda$cacheStreamInfo$0((StreamInfo) obj);
                break;
        }
    }
}
