package defpackage;

import io.nats.client.Connection;
import io.nats.client.Consumer;
import io.nats.client.ErrorListener;

/* JADX INFO: loaded from: classes.dex */
public final class ig0 implements ErrorListener {
    @Override // io.nats.client.ErrorListener
    public final void errorOccurred(Connection connection, String str) {
    }

    @Override // io.nats.client.ErrorListener
    public final void exceptionOccurred(Connection connection, Exception exc) {
    }

    @Override // io.nats.client.ErrorListener
    public final void slowConsumerDetected(Connection connection, Consumer consumer) {
    }
}
