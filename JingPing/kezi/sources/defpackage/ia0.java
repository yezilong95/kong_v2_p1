package defpackage;

import java.util.ListIterator;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class ia0 extends Lambda implements pu {
    public final /* synthetic */ int b;
    public final /* synthetic */ qa0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ia0(qa0 qa0Var, int i) {
        super(1);
        this.b = i;
        this.c = qa0Var;
    }

    @Override // defpackage.pu
    public final Object invoke(Object obj) {
        Object objPrevious;
        Object objPrevious2;
        switch (this.b) {
            case 0:
                oy.j((r7) obj, "backEvent");
                qa0 qa0Var = this.c;
                z6 z6Var = qa0Var.b;
                ListIterator listIterator = z6Var.listIterator(z6Var.size());
                while (true) {
                    if (listIterator.hasPrevious()) {
                        objPrevious = listIterator.previous();
                        if (((ot) objPrevious).a) {
                        }
                    } else {
                        objPrevious = null;
                    }
                }
                qa0Var.c = (ot) objPrevious;
                break;
            default:
                oy.j((r7) obj, "backEvent");
                z6 z6Var2 = this.c.b;
                ListIterator listIterator2 = z6Var2.listIterator(z6Var2.size());
                while (true) {
                    if (listIterator2.hasPrevious()) {
                        objPrevious2 = listIterator2.previous();
                        if (((ot) objPrevious2).a) {
                        }
                    } else {
                        objPrevious2 = null;
                    }
                }
                break;
        }
        return bq0.a;
    }
}
