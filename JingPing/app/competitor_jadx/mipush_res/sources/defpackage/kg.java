package defpackage;

import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$IntRef;

/* JADX INFO: loaded from: classes.dex */
public final class kg extends Lambda implements d10 {
    public final /* synthetic */ bj[] E;
    public final /* synthetic */ Ref$IntRef F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public kg(bj[] bjVarArr, Ref$IntRef ref$IntRef) {
        super(2);
        this.E = bjVarArr;
        this.F = ref$IntRef;
    }

    @Override // defpackage.d10
    public final Object invoke(Object obj, Object obj2) {
        zi ziVar = (zi) obj2;
        t40.n((y21) obj, "<anonymous parameter 0>");
        t40.n(ziVar, "element");
        Ref$IntRef ref$IntRef = this.F;
        int i = ref$IntRef.element;
        ref$IntRef.element = i + 1;
        this.E[i] = ziVar;
        return y21.a;
    }
}
