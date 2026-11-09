package defpackage;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;

/* JADX INFO: loaded from: classes.dex */
public final class rz0 extends s90 {
    public final /* synthetic */ Context w;
    public final /* synthetic */ TextPaint x;
    public final /* synthetic */ s90 y;
    public final /* synthetic */ sz0 z;

    public rz0(sz0 sz0Var, Context context, TextPaint textPaint, s90 s90Var) {
        this.z = sz0Var;
        this.w = context;
        this.x = textPaint;
        this.y = s90Var;
    }

    @Override // defpackage.s90
    public final void O(int i) {
        this.y.O(i);
    }

    @Override // defpackage.s90
    public final void P(Typeface typeface, boolean z) {
        this.z.g(this.w, this.x, typeface);
        this.y.P(typeface, z);
    }
}
