package kotlinx.coroutines.channels;

import defpackage.f10;
import defpackage.pd;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class BufferedChannel$sendImpl$1 extends Lambda implements f10 {
    public static final BufferedChannel$sendImpl$1 INSTANCE = new BufferedChannel$sendImpl$1();

    public BufferedChannel$sendImpl$1() {
        super(4);
    }

    @Override // defpackage.f10
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return invoke((pd) obj, ((Number) obj2).intValue(), obj3, ((Number) obj4).longValue());
    }

    public final Void invoke(pd pdVar, int i, Object obj, long j) {
        throw new IllegalStateException("unexpected");
    }
}
