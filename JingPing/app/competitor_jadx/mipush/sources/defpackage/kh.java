package defpackage;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class kh implements lu0 {
    public final AtomicReference a;

    public kh(ou0 ou0Var) {
        this.a = new AtomicReference(ou0Var);
    }

    @Override // defpackage.lu0
    public final Iterator iterator() {
        lu0 lu0Var = (lu0) this.a.getAndSet(null);
        if (lu0Var != null) {
            return lu0Var.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}
