package defpackage;

import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class oh extends Lambda implements pu {
    public static final oh c = new oh(1, 0);
    public static final oh d = new oh(1, 1);
    public static final oh e = new oh(1, 2);
    public final /* synthetic */ int b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ oh(int i, int i2) {
        super(i);
        this.b = i2;
    }

    @Override // defpackage.pu
    public final Object invoke(Object obj) {
        switch (this.b) {
            case 0:
                lh lhVar = (lh) obj;
                if (lhVar instanceof qh) {
                    return (qh) lhVar;
                }
                return null;
            case 1:
                lh lhVar2 = (lh) obj;
                if (lhVar2 instanceof vo) {
                    return (vo) lhVar2;
                }
                return null;
            default:
                String str = (String) obj;
                oy.j(str, "line");
                return str;
        }
    }
}
