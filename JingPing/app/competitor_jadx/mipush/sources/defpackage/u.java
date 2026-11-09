package defpackage;

import androidx.appcompat.widget.ActionBarContextView;

/* JADX INFO: loaded from: classes.dex */
public final class u implements j61 {
    public static final t[] d = new t[0];
    public boolean a;
    public int b;
    public Object c;

    public u() {
        this(10);
    }

    @Override // defpackage.j61
    public void a() {
        if (this.a) {
            return;
        }
        ActionBarContextView actionBarContextView = (ActionBarContextView) this.c;
        actionBarContextView.J = null;
        super/*android.view.View*/.setVisibility(this.b);
    }

    @Override // defpackage.j61
    public void b() {
        this.a = true;
    }

    public void c(t tVar) {
        if (tVar == null) {
            throw new NullPointerException("'element' cannot be null");
        }
        t[] tVarArr = (t[]) this.c;
        int length = tVarArr.length;
        int i = this.b + 1;
        if (this.a | (i > length)) {
            t[] tVarArr2 = new t[Math.max(tVarArr.length, (i >> 1) + i)];
            System.arraycopy((t[]) this.c, 0, tVarArr2, 0, this.b);
            this.c = tVarArr2;
            this.a = false;
        }
        ((t[]) this.c)[this.b] = tVar;
        this.b = i;
    }

    public t d(int i) {
        if (i < this.b) {
            return ((t[]) this.c)[i];
        }
        throw new ArrayIndexOutOfBoundsException(i + " >= " + this.b);
    }

    @Override // defpackage.j61
    public void e() {
        super/*android.view.View*/.setVisibility(0);
        this.a = false;
    }

    public t[] f() {
        int i = this.b;
        if (i == 0) {
            return d;
        }
        t[] tVarArr = (t[]) this.c;
        if (tVarArr.length == i) {
            this.a = true;
            return tVarArr;
        }
        t[] tVarArr2 = new t[i];
        System.arraycopy(tVarArr, 0, tVarArr2, 0, i);
        return tVarArr2;
    }

    public u(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("'initialCapacity' must not be negative");
        }
        this.c = i == 0 ? d : new t[i];
        this.b = 0;
        this.a = false;
    }
}
