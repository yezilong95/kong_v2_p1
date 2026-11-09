package defpackage;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class yf implements xh0 {
    public final AtomicReference a;

    public yf(he heVar) {
        this.a = new AtomicReference(heVar);
    }

    @Override // defpackage.xh0
    public final Iterator iterator() {
        xh0 xh0Var = (xh0) this.a.getAndSet(null);
        if (xh0Var != null) {
            return xh0Var.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}
