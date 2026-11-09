package defpackage;

import android.os.Looper;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class ea0 {
    public static final Object k = new Object();
    public final Object a = new Object();
    public final yq0 b = new yq0();
    public int c = 0;
    public boolean d;
    public volatile Object e;
    public volatile Object f;
    public int g;
    public boolean h;
    public boolean i;
    public final f j;

    public ea0() {
        Object obj = k;
        this.f = obj;
        this.j = new f(16, this);
        this.e = obj;
        this.g = -1;
    }

    public static void a(String str) {
        e8.k0().j.getClass();
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException(zn.n("Cannot invoke ", str, " on a background thread"));
        }
    }

    public final void b(da0 da0Var) {
        if (this.h) {
            this.i = true;
            return;
        }
        this.h = true;
        do {
            this.i = false;
            if (da0Var != null) {
                if (da0Var.b) {
                    int i = da0Var.c;
                    int i2 = this.g;
                    if (i < i2) {
                        da0Var.c = i2;
                        da0Var.a.onChanged(this.e);
                    }
                }
                da0Var = null;
            } else {
                yq0 yq0Var = this.b;
                yq0Var.getClass();
                wq0 wq0Var = new wq0(yq0Var);
                yq0Var.G.put(wq0Var, Boolean.FALSE);
                while (wq0Var.hasNext()) {
                    da0 da0Var2 = (da0) ((Map.Entry) wq0Var.next()).getValue();
                    if (da0Var2.b) {
                        int i3 = da0Var2.c;
                        int i4 = this.g;
                        if (i3 < i4) {
                            da0Var2.c = i4;
                            da0Var2.a.onChanged(this.e);
                        }
                    }
                    if (this.i) {
                        break;
                    }
                }
            }
        } while (this.i);
        this.h = false;
    }

    public final void c(ti0 ti0Var) {
        Object obj;
        a("observeForever");
        da0 da0Var = new da0(this, ti0Var);
        yq0 yq0Var = this.b;
        vq0 vq0VarA = yq0Var.a(ti0Var);
        if (vq0VarA != null) {
            obj = vq0VarA.F;
        } else {
            vq0 vq0Var = new vq0(ti0Var, da0Var);
            yq0Var.H++;
            vq0 vq0Var2 = yq0Var.F;
            if (vq0Var2 == null) {
                yq0Var.E = vq0Var;
                yq0Var.F = vq0Var;
            } else {
                vq0Var2.G = vq0Var;
                vq0Var.H = vq0Var2;
                yq0Var.F = vq0Var;
            }
            obj = null;
        }
        if (((da0) obj) != null) {
            return;
        }
        da0Var.a(true);
    }

    public void f(Object obj) {
        boolean z;
        synchronized (this.a) {
            z = this.f == k;
            this.f = obj;
        }
        if (z) {
            e8.k0().l0(this.j);
        }
    }

    public void g(Object obj) {
        a("setValue");
        this.g++;
        this.e = obj;
        b(null);
    }

    public void d() {
    }

    public void e() {
    }
}
