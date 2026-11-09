package defpackage;

import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.impl.NatsKeyValue;
import io.nats.client.impl.NatsObjectStore;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class mg0 implements MessageHandler {
    public final /* synthetic */ int a;
    public final /* synthetic */ ArrayList b;

    public /* synthetic */ mg0(ArrayList arrayList, int i) {
        this.a = i;
        this.b = arrayList;
    }

    @Override // io.nats.client.MessageHandler
    public final void onMessage(Message message) throws InterruptedException {
        switch (this.a) {
            case 0:
                NatsKeyValue.lambda$_keys$0(this.b, message);
                break;
            case 1:
                NatsKeyValue.lambda$history$3(this.b, message);
                break;
            default:
                NatsObjectStore.lambda$getList$0(this.b, message);
                break;
        }
    }
}
