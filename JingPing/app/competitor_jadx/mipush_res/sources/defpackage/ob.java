package defpackage;

import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ob extends FunctionReferenceImpl implements d10 {
    public static final ob M = new ob(2, pb.class, "createSegment", "createSegment(JLkotlinx/coroutines/channels/ChannelSegment;)Lkotlinx/coroutines/channels/ChannelSegment;", 1);

    @Override // defpackage.d10
    public final Object invoke(Object obj, Object obj2) {
        long jLongValue = ((Number) obj).longValue();
        pd pdVar = (pd) obj2;
        pd pdVar2 = pb.a;
        nb nbVar = pdVar.e;
        t40.k(nbVar);
        return new pd(jLongValue, pdVar, nbVar, 0);
    }
}
