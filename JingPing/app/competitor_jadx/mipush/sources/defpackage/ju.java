package defpackage;

import io.nats.client.Connection;
import io.nats.client.ErrorListener;
import io.nats.client.JetStreamSubscription;
import io.nats.client.impl.ErrorListenerLoggerImpl;
import io.nats.client.support.Status;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ju implements Supplier {
    public final /* synthetic */ int a;
    public final /* synthetic */ ErrorListenerLoggerImpl b;
    public final /* synthetic */ Connection c;
    public final /* synthetic */ JetStreamSubscription d;
    public final /* synthetic */ Object e;

    public /* synthetic */ ju(ErrorListenerLoggerImpl errorListenerLoggerImpl, Connection connection, JetStreamSubscription jetStreamSubscription, Object obj, int i) {
        this.a = i;
        this.b = errorListenerLoggerImpl;
        this.c = connection;
        this.d = jetStreamSubscription;
        this.e = obj;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        switch (this.a) {
            case 0:
                return this.b.lambda$pullStatusWarning$6(this.c, this.d, (Status) this.e);
            case 1:
                return this.b.lambda$unhandledStatus$5(this.c, this.d, (Status) this.e);
            case 2:
                return this.b.lambda$pullStatusError$7(this.c, this.d, (Status) this.e);
            default:
                return this.b.lambda$flowControlProcessed$8(this.c, this.d, (ErrorListener.FlowControlSource) this.e);
        }
    }
}
