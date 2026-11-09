package defpackage;

import android.graphics.Typeface;

/* JADX INFO: loaded from: classes.dex */
public final class qz0 extends o80 {
    public final /* synthetic */ s90 j;
    public final /* synthetic */ sz0 k;

    public qz0(sz0 sz0Var, s90 s90Var) {
        this.k = sz0Var;
        this.j = s90Var;
    }

    @Override // defpackage.o80
    public final void W(int i) {
        this.k.m = true;
        this.j.O(i);
    }

    @Override // defpackage.o80
    public final void X(Typeface typeface) {
        sz0 sz0Var = this.k;
        sz0Var.n = Typeface.create(typeface, sz0Var.c);
        sz0Var.m = true;
        this.j.P(sz0Var.n, false);
    }
}
