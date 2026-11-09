package defpackage;

import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class t9 extends FunctionReferenceImpl implements ru {
    public static final t9 j = new t9(2, u9.class, "createSegment", "createSegment(JLkotlinx/coroutines/channels/ChannelSegment;)Lkotlinx/coroutines/channels/ChannelSegment;", 1);

    @Override // defpackage.ru
    public final Object invoke(Object obj, Object obj2) {
        long jLongValue = ((Number) obj).longValue();
        vb vbVar = (vb) obj2;
        vb vbVar2 = u9.a;
        s9 s9Var = vbVar.e;
        oy.g(s9Var);
        return new vb(jLongValue, vbVar, s9Var, 0);
    }
}
