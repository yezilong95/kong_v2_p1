package defpackage;

import android.os.Looper;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class b30 {
    public static final Object k = new Object();
    public final Object a = new Object();
    public final lg0 b = new lg0();
    public int c = 0;
    public boolean d;
    public volatile Object e;
    public volatile Object f;
    public int g;
    public boolean h;
    public boolean i;
    public final v6 j;

    public b30() {
        Object obj = k;
        this.f = obj;
        this.j = new v6(12, this);
        this.e = obj;
        this.g = -1;
    }

    public static void a(String str) {
        t6.W().r.getClass();
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException(hk.j("Cannot invoke ", str, " on a background thread"));
        }
    }

    public final void b(a30 a30Var) {
        if (a30Var.c) {
            if (!a30Var.e()) {
                a30Var.b(false);
                return;
            }
            int i = a30Var.d;
            int i2 = this.g;
            if (i >= i2) {
                return;
            }
            a30Var.d = i2;
            a30Var.b.onChanged(this.e);
        }
    }

    public final void c(a30 a30Var) {
        if (this.h) {
            this.i = true;
            return;
        }
        this.h = true;
        do {
            this.i = false;
            if (a30Var != null) {
                b(a30Var);
                a30Var = null;
            } else {
                lg0 lg0Var = this.b;
                lg0Var.getClass();
                jg0 jg0Var = new jg0(lg0Var);
                lg0Var.d.put(jg0Var, Boolean.FALSE);
                while (jg0Var.hasNext()) {
                    b((a30) ((Map.Entry) jg0Var.next()).getValue());
                    if (this.i) {
                        break;
                    }
                }
            }
        } while (this.i);
        this.h = false;
    }

    public final void d(ba0 ba0Var) {
        Object obj;
        a("observeForever");
        y20 y20Var = new y20(this, ba0Var);
        lg0 lg0Var = this.b;
        ig0 ig0VarA = lg0Var.a(ba0Var);
        if (ig0VarA != null) {
            obj = ig0VarA.c;
        } else {
            ig0 ig0Var = new ig0(ba0Var, y20Var);
            lg0Var.e++;
            ig0 ig0Var2 = lg0Var.c;
            if (ig0Var2 == null) {
                lg0Var.b = ig0Var;
                lg0Var.c = ig0Var;
            } else {
                ig0Var2.d = ig0Var;
                ig0Var.e = ig0Var2;
                lg0Var.c = ig0Var;
            }
            obj = null;
        }
        a30 a30Var = (a30) obj;
        if (a30Var instanceof z20) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (a30Var != null) {
            return;
        }
        y20Var.b(true);
    }

    public void g(Object obj) {
        boolean z;
        synchronized (this.a) {
            z = this.f == k;
            this.f = obj;
        }
        if (z) {
            t6.W().X(this.j);
        }
    }

    public final void h(ba0 ba0Var) {
        a("removeObserver");
        a30 a30Var = (a30) this.b.b(ba0Var);
        if (a30Var == null) {
            return;
        }
        a30Var.c();
        a30Var.b(false);
    }

    public void i(Object obj) {
        a("setValue");
        this.g++;
        this.e = obj;
        c(null);
    }

    public void e() {
    }

    public void f() {
    }
}
