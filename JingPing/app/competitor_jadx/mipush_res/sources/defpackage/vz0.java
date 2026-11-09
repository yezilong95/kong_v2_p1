package defpackage;

import android.content.Context;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class vz0 {
    public float c;
    public float d;
    public final WeakReference f;
    public sz0 g;
    public final TextPaint a = new TextPaint(1);
    public final de b = new de(1, this);
    public boolean e = true;

    public vz0(uz0 uz0Var) {
        this.f = new WeakReference(null);
        this.f = new WeakReference(uz0Var);
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

    public final void c(sz0 sz0Var, Context context) {
        if (this.g != sz0Var) {
            this.g = sz0Var;
            if (sz0Var != null) {
                TextPaint textPaint = this.a;
                de deVar = this.b;
                sz0Var.f(context, textPaint, deVar);
                uz0 uz0Var = (uz0) this.f.get();
                if (uz0Var != null) {
                    textPaint.drawableState = uz0Var.getState();
                }
                sz0Var.e(context, textPaint, deVar);
                this.e = true;
            }
            uz0 uz0Var2 = (uz0) this.f.get();
            if (uz0Var2 != null) {
                uz0Var2.a();
                uz0Var2.onStateChange(uz0Var2.getState());
            }
        }
    }
}
