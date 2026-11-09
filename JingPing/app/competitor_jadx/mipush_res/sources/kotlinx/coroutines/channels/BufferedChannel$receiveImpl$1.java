package kotlinx.coroutines.channels;

import defpackage.e10;
import defpackage.pd;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class BufferedChannel$receiveImpl$1 extends Lambda implements e10 {
    public static final BufferedChannel$receiveImpl$1 INSTANCE = new BufferedChannel$receiveImpl$1();

    public BufferedChannel$receiveImpl$1() {
        super(3);
    }

    @Override // defpackage.e10
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((pd) obj, ((Number) obj2).intValue(), ((Number) obj3).longValue());
    }

    public final Void invoke(pd pdVar, int i, long j) {
        throw new IllegalStateException("unexpected");
    }
}
