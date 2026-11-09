package defpackage;

import android.content.Context;
import android.content.IntentFilter;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public abstract class z5 {
    public Object a;
    public Object b;

    public z5(Context context) {
        this.a = context;
    }

    public static float h(int i, int i2, int i3) {
        return (i - i2) / i3;
    }

    public abstract void c();

    public void d() {
        y5 y5Var = (y5) this.a;
        if (y5Var != null) {
            try {
                ((c6) this.b).O.unregisterReceiver(y5Var);
            } catch (IllegalArgumentException unused) {
            }
            this.a = null;
        }
    }

    public void e() {
        nw0 nw0Var = (nw0) this.a;
        HashSet hashSet = nw0Var.e;
        if (hashSet.remove((gc) this.b) && hashSet.isEmpty()) {
            nw0Var.b();
        }
    }

    public abstract IntentFilter f();

    public abstract int g();

    public MenuItem i(MenuItem menuItem) {
        if (!(menuItem instanceof dy0)) {
            return menuItem;
        }
        dy0 dy0Var = (dy0) menuItem;
        if (((vv0) this.b) == null) {
            this.b = new vv0();
        }
        MenuItem menuItem2 = (MenuItem) ((vv0) this.b).getOrDefault(dy0Var, null);
        if (menuItem2 != null) {
            return menuItem2;
        }
        he0 he0Var = new he0((Context) this.a, dy0Var);
        ((vv0) this.b).put(dy0Var, he0Var);
        return he0Var;
    }

    public abstract void j();

    public abstract void k();

    public abstract void l(z9 z9Var);

    public abstract void m();

    public void n() {
        d();
        IntentFilter intentFilterF = f();
        if (intentFilterF.countActions() == 0) {
            return;
        }
        if (((y5) this.a) == null) {
            this.a = new y5(0, this);
        }
        ((c6) this.b).O.registerReceiver((y5) this.a, intentFilterF);
    }

    public abstract void o();

    public abstract void p();

    public z5(int i) {
        this.b = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            ((ArrayList) this.b).add(new zp());
        }
    }

    public z5(nw0 nw0Var, gc gcVar) {
        this.a = nw0Var;
        this.b = gcVar;
    }

    public z5(c6 c6Var) {
        this.b = c6Var;
    }
}
