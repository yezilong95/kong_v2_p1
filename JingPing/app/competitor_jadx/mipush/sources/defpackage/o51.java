package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class o51 {
    public final p51 a = new p51();

    public final void a() {
        p51 p51Var = this.a;
        if (p51Var != null && !p51Var.d) {
            p51Var.d = true;
            synchronized (p51Var.a) {
                try {
                    Iterator it = p51Var.b.values().iterator();
                    while (it.hasNext()) {
                        p51.a((AutoCloseable) it.next());
                    }
                    Iterator it2 = p51Var.c.iterator();
                    while (it2.hasNext()) {
                        p51.a((AutoCloseable) it2.next());
                    }
                    p51Var.c.clear();
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
