package defpackage;

import io.nats.client.support.JsonUtils;
import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b60 implements JsonUtils.ListAdder {
    @Override // io.nats.client.support.JsonUtils.ListAdder
    public final void append(StringBuilder sb, Object obj) {
        JsonUtils.lambda$addDurations$2(sb, (Duration) obj);
    }
}
