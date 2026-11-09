package defpackage;

import android.graphics.Typeface;
import android.os.Build;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class w6 extends o80 {
    public final /* synthetic */ int j;
    public final /* synthetic */ int k;
    public final /* synthetic */ WeakReference l;
    public final /* synthetic */ c7 m;

    public w6(c7 c7Var, int i, int i2, WeakReference weakReference) {
        this.m = c7Var;
        this.j = i;
        this.k = i2;
        this.l = weakReference;
    }

    @Override // defpackage.o80
    public final void X(Typeface typeface) {
        int i;
        if (Build.VERSION.SDK_INT >= 28 && (i = this.j) != -1) {
            typeface = b7.a(typeface, i, (this.k & 2) != 0);
        }
        c7 c7Var = this.m;
        if (c7Var.m) {
            c7Var.l = typeface;
            TextView textView = (TextView) this.l.get();
            if (textView != null) {
                WeakHashMap weakHashMap = f51.a;
                if (textView.isAttachedToWindow()) {
                    textView.post(new x6(textView, typeface, c7Var.j));
                } else {
                    textView.setTypeface(typeface, c7Var.j);
                }
            }
        }
    }

    @Override // defpackage.o80
    public final void W(int i) {
    }
}
