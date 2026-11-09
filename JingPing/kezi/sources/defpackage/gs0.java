package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class gs0 {
    public final hs0 a = new hs0();

    public final void a() {
        hs0 hs0Var = this.a;
        if (hs0Var != null && !hs0Var.d) {
            hs0Var.d = true;
            synchronized (hs0Var.a) {
                try {
                    Iterator it = hs0Var.b.values().iterator();
                    while (it.hasNext()) {
                        hs0.a((AutoCloseable) it.next());
                    }
                    Iterator it2 = hs0Var.c.iterator();
                    while (it2.hasNext()) {
                        hs0.a((AutoCloseable) it2.next());
                    }
                    hs0Var.c.clear();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        b();
    }

    public void b() {
    }
}
