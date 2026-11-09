package kotlinx.coroutines.channels;

import defpackage.su;
import defpackage.vb;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class BufferedChannel$receiveImpl$1 extends Lambda implements su {
    public static final BufferedChannel$receiveImpl$1 INSTANCE = new BufferedChannel$receiveImpl$1();

    public BufferedChannel$receiveImpl$1() {
        super(3);
    }

    @Override // defpackage.su
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((vb) obj, ((Number) obj2).intValue(), ((Number) obj3).longValue());
    }

    public final Void invoke(vb vbVar, int i, long j) {
        throw new IllegalStateException("unexpected");
    }
}
