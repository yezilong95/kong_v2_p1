package defpackage;

import android.graphics.Typeface;
import android.os.Build;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class s5 extends rp0 {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ WeakReference g;
    public final /* synthetic */ y5 h;

    public s5(y5 y5Var, int i, int i2, WeakReference weakReference) {
        this.h = y5Var;
        this.e = i;
        this.f = i2;
        this.g = weakReference;
    }

    @Override // defpackage.rp0
    public final void M(Typeface typeface) {
        int i;
        if (Build.VERSION.SDK_INT >= 28 && (i = this.e) != -1) {
            typeface = x5.a(typeface, i, (this.f & 2) != 0);
        }
        y5 y5Var = this.h;
        if (y5Var.m) {
            y5Var.l = typeface;
            TextView textView = (TextView) this.g.get();
            if (textView != null) {
                WeakHashMap weakHashMap = xr0.a;
                if (textView.isAttachedToWindow()) {
                    textView.post(new t5(textView, typeface, y5Var.j));
                } else {
                    textView.setTypeface(typeface, y5Var.j);
                }
            }
        }
    }

    @Override // defpackage.rp0
    public final void L(int i) {
    }
}
