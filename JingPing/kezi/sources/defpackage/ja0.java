package defpackage;

import java.util.ListIterator;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class ja0 extends Lambda implements ou {
    public final /* synthetic */ int b;
    public final /* synthetic */ qa0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ja0(qa0 qa0Var, int i) {
        super(0);
        this.b = i;
        this.c = qa0Var;
    }

    @Override // defpackage.ou
    public final Object invoke() {
        Object objPrevious;
        switch (this.b) {
            case 0:
                this.c.a();
                break;
            case 1:
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
                qa0Var.c = null;
                break;
            default:
                this.c.a();
                break;
        }
        return bq0.a;
    }
}
