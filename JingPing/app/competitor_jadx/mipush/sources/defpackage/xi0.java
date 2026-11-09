package defpackage;

import java.util.ListIterator;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class xi0 extends Lambda implements a10 {
    public final /* synthetic */ int E;
    public final /* synthetic */ ej0 F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ xi0(ej0 ej0Var, int i) {
        super(0);
        this.E = i;
        this.F = ej0Var;
    }

    @Override // defpackage.a10
    public final Object invoke() {
        Object objPrevious;
        switch (this.E) {
            case 0:
                this.F.a();
                break;
            case 1:
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
                ej0Var.c = null;
                break;
            default:
                this.F.a();
                break;
        }
        return y21.a;
    }
}
