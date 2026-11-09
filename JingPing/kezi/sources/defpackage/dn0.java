package defpackage;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;

/* JADX INFO: loaded from: classes.dex */
public final class dn0 extends za0 {
    public final /* synthetic */ Context p;
    public final /* synthetic */ TextPaint q;
    public final /* synthetic */ za0 r;
    public final /* synthetic */ en0 s;

    public dn0(en0 en0Var, Context context, TextPaint textPaint, za0 za0Var) {
        this.s = en0Var;
        this.p = context;
        this.q = textPaint;
        this.r = za0Var;
    }

    @Override // defpackage.za0
    public final void G(int i) {
        this.r.G(i);
    }

    @Override // defpackage.za0
    public final void H(Typeface typeface, boolean z) {
        this.s.g(this.p, this.q, typeface);
        this.r.H(typeface, z);
    }
}
