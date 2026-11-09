package defpackage;

import io.nats.client.Connection;
import io.nats.client.Consumer;
import io.nats.client.Message;
import io.nats.client.impl.ErrorListenerLoggerImpl;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class iu implements Supplier {
    public final /* synthetic */ int a;
    public final /* synthetic */ ErrorListenerLoggerImpl b;
    public final /* synthetic */ Connection c;
    public final /* synthetic */ Object d;

    public /* synthetic */ iu(ErrorListenerLoggerImpl errorListenerLoggerImpl, Connection connection, Object obj, int i) {
        this.a = i;
        this.b = errorListenerLoggerImpl;
        this.c = connection;
        this.d = obj;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        switch (this.a) {
            case 0:
                return this.b.lambda$slowConsumerDetected$2(this.c, (Consumer) this.d);
            case 1:
                return this.b.lambda$messageDiscarded$3(this.c, (Message) this.d);
            case 2:
                return this.b.lambda$exceptionOccurred$1(this.c, (Exception) this.d);
            default:
                return this.b.lambda$errorOccurred$0(this.c, (String) this.d);
        }
    }
}
