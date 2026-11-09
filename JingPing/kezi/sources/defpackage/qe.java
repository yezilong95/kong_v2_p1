package defpackage;

import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$IntRef;

/* JADX INFO: loaded from: classes.dex */
public final class qe extends Lambda implements ru {
    public final /* synthetic */ nh[] b;
    public final /* synthetic */ Ref$IntRef c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public qe(nh[] nhVarArr, Ref$IntRef ref$IntRef) {
        super(2);
        this.b = nhVarArr;
        this.c = ref$IntRef;
    }

    @Override // defpackage.ru
    public final Object invoke(Object obj, Object obj2) {
        lh lhVar = (lh) obj2;
        oy.j((bq0) obj, "<anonymous parameter 0>");
        oy.j(lhVar, "element");
        Ref$IntRef ref$IntRef = this.c;
        int i = ref$IntRef.element;
        ref$IntRef.element = i + 1;
        this.b[i] = lhVar;
        return bq0.a;
    }
}
