package defpackage;

import android.graphics.Typeface;

/* JADX INFO: loaded from: classes.dex */
public final class xd {
    public final /* synthetic */ int a;
    public final /* synthetic */ yd b;

    public /* synthetic */ xd(yd ydVar, int i) {
        this.a = i;
        this.b = ydVar;
    }

    public final void a(Typeface typeface) {
        switch (this.a) {
            case 0:
                yd ydVar = this.b;
                if (ydVar.m(typeface)) {
                    ydVar.i(false);
                }
                break;
            default:
                yd ydVar2 = this.b;
                if (ydVar2.o(typeface)) {
                    ydVar2.i(false);
                }
                break;
        }
    }
}
