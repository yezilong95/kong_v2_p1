package defpackage;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.e;
import androidx.recyclerview.widget.f;
import androidx.recyclerview.widget.k;

/* JADX INFO: loaded from: classes.dex */
public abstract class ws {
    public int a;
    public final Object b;
    public final Object c;

    public ws(k kVar) {
        this.a = Integer.MIN_VALUE;
        this.c = new Rect();
        this.b = kVar;
    }

    public static ws a(k kVar, int i) {
        if (i == 0) {
            return new e(kVar);
        }
        if (i == 1) {
            return new f(kVar);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public abstract int b(View view);

    public abstract int c(View view);

    public abstract int d(View view);

    public abstract int e(View view);

    public abstract int f();

    public abstract int g();

    public abstract int h();

    public abstract int i();

    public abstract int j();

    public abstract int k();

    public abstract int l();

    public abstract int m(View view);

    public abstract int n(View view);

    public abstract void o(int i);

    public ws(ys ysVar) {
        this.a = 0;
        this.c = new xm();
        this.b = ysVar;
    }
}
