package defpackage;

import android.content.Context;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class hn0 {
    public float c;
    public float d;
    public final WeakReference f;
    public en0 g;
    public final TextPaint a = new TextPaint(1);
    public final ic b = new ic(1, this);
    public boolean e = true;

    public hn0(gn0 gn0Var) {
        this.f = new WeakReference(null);
        this.f = new WeakReference(gn0Var);
    }

    public final float a(String str) {
        if (!this.e) {
            return this.c;
        }
        b(str);
        return this.c;
    }

    public final void b(String str) {
        TextPaint textPaint = this.a;
        this.c = str == null ? 0.0f : textPaint.measureText((CharSequence) str, 0, str.length());
        this.d = str != null ? Math.abs(textPaint.getFontMetrics().ascent) : 0.0f;
        this.e = false;
    }

    public final void c(en0 en0Var, Context context) {
        if (this.g != en0Var) {
            this.g = en0Var;
            if (en0Var != null) {
                TextPaint textPaint = this.a;
                ic icVar = this.b;
                en0Var.f(context, textPaint, icVar);
                gn0 gn0Var = (gn0) this.f.get();
                if (gn0Var != null) {
                    textPaint.drawableState = gn0Var.getState();
                }
                en0Var.e(context, textPaint, icVar);
                this.e = true;
            }
            gn0 gn0Var2 = (gn0) this.f.get();
            if (gn0Var2 != null) {
                gn0Var2.a();
                gn0Var2.onStateChange(gn0Var2.getState());
            }
        }
    }
}
