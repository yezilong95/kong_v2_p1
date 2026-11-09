package defpackage;

import android.graphics.Path;
import android.graphics.RectF;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public abstract class lv0 {
    public zu0 c;
    public boolean a = false;
    public boolean b = false;
    public RectF d = new RectF();
    public final Path e = new Path();

    public abstract void a(FrameLayout frameLayout);

    public abstract boolean b();

    public final void c() {
        zu0 zu0Var;
        RectF rectF = this.d;
        if (rectF.left > rectF.right || rectF.top > rectF.bottom || (zu0Var = this.c) == null) {
            return;
        }
        av0.a.a(zu0Var, 1.0f, rectF, null, this.e);
    }
}
