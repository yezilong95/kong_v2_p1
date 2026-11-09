package defpackage;

import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class cj extends Lambda implements b10 {
    public static final cj F = new cj(1, 0);
    public static final cj G = new cj(1, 1);
    public static final cj H = new cj(1, 2);
    public final /* synthetic */ int E;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ cj(int i, int i2) {
        super(i);
        this.E = i2;
    }

    @Override // defpackage.b10
    public final Object invoke(Object obj) {
        switch (this.E) {
            case 0:
                zi ziVar = (zi) obj;
                if (ziVar instanceof ej) {
                    return (ej) ziVar;
                }
                return null;
            case 1:
                zi ziVar2 = (zi) obj;
                if (ziVar2 instanceof su) {
                    return (su) ziVar2;
                }
                return null;
            default:
                String str = (String) obj;
                t40.n(str, "line");
                return str;
        }
    }
}
