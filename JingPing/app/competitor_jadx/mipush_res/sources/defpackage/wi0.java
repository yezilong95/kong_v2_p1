package defpackage;

import java.util.ListIterator;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class wi0 extends Lambda implements b10 {
    public final /* synthetic */ int E;
    public final /* synthetic */ ej0 F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ wi0(ej0 ej0Var, int i) {
        super(1);
        this.E = i;
        this.F = ej0Var;
    }

    @Override // defpackage.b10
    public final Object invoke(Object obj) {
        Object objPrevious;
        Object objPrevious2;
        switch (this.E) {
            case 0:
                t40.n((l9) obj, "backEvent");
                ej0 ej0Var = this.F;
                h8 h8Var = ej0Var.b;
                ListIterator listIterator = h8Var.listIterator(h8Var.size());
                while (true) {
                    if (listIterator.hasPrevious()) {
                        objPrevious = listIterator.previous();
                        if (((a00) objPrevious).a) {
                        }
                    } else {
                        objPrevious = null;
                    }
                }
                ej0Var.c = (a00) objPrevious;
                break;
            default:
                t40.n((l9) obj, "backEvent");
                h8 h8Var2 = this.F.b;
                ListIterator listIterator2 = h8Var2.listIterator(h8Var2.size());
                while (true) {
                    if (listIterator2.hasPrevious()) {
                        objPrevious2 = listIterator2.previous();
                        if (((a00) objPrevious2).a) {
                        }
                    } else {
                        objPrevious2 = null;
                    }
                }
                break;
        }
        return y21.a;
    }
}
