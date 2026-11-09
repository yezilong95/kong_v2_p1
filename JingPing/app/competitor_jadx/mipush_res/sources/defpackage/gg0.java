package defpackage;

import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.impl.NatsKeyValue;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class gg0 implements MessageHandler {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ gg0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // io.nats.client.MessageHandler
    public final void onMessage(Message message) throws InterruptedException {
        switch (this.a) {
            case 0:
                ((jg0) this.b).getClass();
                new String(message.getData(), StandardCharsets.UTF_8);
                break;
            default:
                NatsKeyValue.lambda$null$1((LinkedBlockingQueue) this.b, message);
                break;
        }
    }
}
