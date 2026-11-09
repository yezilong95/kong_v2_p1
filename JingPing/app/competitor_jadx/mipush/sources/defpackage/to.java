package defpackage;

import io.nats.client.Options;
import io.nats.service.Discovery;
import java.util.ArrayList;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class to implements Consumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ to(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.a) {
            case 0:
                Discovery.lambda$stats$2((ArrayList) this.b, (byte[]) obj);
                break;
            case 1:
                Discovery.lambda$info$1((ArrayList) this.b, (byte[]) obj);
                break;
            case 2:
                Discovery.lambda$ping$0((ArrayList) this.b, (byte[]) obj);
                break;
            default:
                Options.lambda$classnameProperty$0((Consumer) this.b, (String) obj);
                break;
        }
    }
}
