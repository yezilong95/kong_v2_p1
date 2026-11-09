package defpackage;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import org.bouncycastle.crypto.digests.a;

/* JADX INFO: loaded from: classes.dex */
public final class dp extends PhantomReference {
    public final Runnable a;
    public final String b;

    public dp(bq0 bq0Var, ReferenceQueue referenceQueue) {
        Runnable aVar;
        super(bq0Var, referenceQueue);
        this.b = bq0Var.b;
        switch (bq0Var.c) {
            case 0:
                aVar = new a(bq0Var.a, 0);
                break;
            case 1:
                aVar = new a(bq0Var.a, 1);
                break;
            case 2:
                aVar = new a(bq0Var.a, 2);
                break;
            case 3:
                aVar = new eq0(bq0Var.a, 0);
                break;
            case 4:
                aVar = new a(bq0Var.a, 3);
                break;
            default:
                aVar = new eq0(bq0Var.a, 1);
                break;
        }
        this.a = aVar;
    }

    public final String toString() {
        return this.b;
    }
}
