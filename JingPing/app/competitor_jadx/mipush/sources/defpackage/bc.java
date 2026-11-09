package defpackage;

import android.graphics.Typeface;

/* JADX INFO: loaded from: classes.dex */
public final class bc extends s90 {
    public final Typeface w;
    public final ac x;
    public boolean y;

    public bc(ac acVar, Typeface typeface) {
        this.w = typeface;
        this.x = acVar;
    }

    @Override // defpackage.s90
    public final void O(int i) {
        if (this.y) {
            return;
        }
        this.x.r(this.w);
    }

    @Override // defpackage.s90
    public final void P(Typeface typeface, boolean z) {
        if (this.y) {
            return;
        }
        this.x.r(typeface);
    }
}
