package defpackage;

import android.content.Context;
import android.content.IntentFilter;
import android.view.MenuItem;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public abstract class u4 {
    public Object a;
    public Object b;

    public u4(Context context) {
        this.a = context;
    }

    public void c() {
        t4 t4Var = (t4) this.a;
        if (t4Var != null) {
            try {
                ((x4) this.b).l.unregisterReceiver(t4Var);
            } catch (IllegalArgumentException unused) {
            }
            this.a = null;
        }
    }

    public void d() {
        zj0 zj0Var = (zj0) this.a;
        HashSet hashSet = zj0Var.e;
        if (hashSet.remove((oa) this.b) && hashSet.isEmpty()) {
            zj0Var.b();
        }
    }

    public abstract IntentFilter e();

    public abstract int f();

    public MenuItem g(MenuItem menuItem) {
        if (!(menuItem instanceof pl0)) {
            return menuItem;
        }
        pl0 pl0Var = (pl0) menuItem;
        if (((fj0) this.b) == null) {
            this.b = new fj0();
        }
        MenuItem menuItem2 = (MenuItem) ((fj0) this.b).getOrDefault(pl0Var, null);
        if (menuItem2 != null) {
            return menuItem2;
        }
        q60 q60Var = new q60((Context) this.a, pl0Var);
        ((fj0) this.b).put(pl0Var, q60Var);
        return q60Var;
    }

    public abstract void h();

    public void i() {
        c();
        IntentFilter intentFilterE = e();
        if (intentFilterE.countActions() == 0) {
            return;
        }
        if (((t4) this.a) == null) {
            this.a = new t4(this);
        }
        ((x4) this.b).l.registerReceiver((t4) this.a, intentFilterE);
    }

    public u4(zj0 zj0Var, oa oaVar) {
        this.a = zj0Var;
        this.b = oaVar;
    }

    public u4(x4 x4Var) {
        this.b = x4Var;
    }
}
