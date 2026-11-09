package kotlinx.coroutines.channels;

import defpackage.tu;
import defpackage.vb;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class BufferedChannel$sendImpl$1 extends Lambda implements tu {
    public static final BufferedChannel$sendImpl$1 INSTANCE = new BufferedChannel$sendImpl$1();

    public BufferedChannel$sendImpl$1() {
        super(4);
    }

    @Override // defpackage.tu
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return invoke((vb) obj, ((Number) obj2).intValue(), obj3, ((Number) obj4).longValue());
    }

    public final Void invoke(vb vbVar, int i, Object obj, long j) {
        throw new IllegalStateException("unexpected");
    }
}
