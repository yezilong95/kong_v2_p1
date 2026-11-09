package defpackage;

import android.view.View;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class l61 implements n61 {
    public final /* synthetic */ boolean E;
    public final /* synthetic */ boolean F;
    public final /* synthetic */ boolean G;
    public final /* synthetic */ p50 H;

    public l61(boolean z, boolean z2, boolean z3, p50 p50Var) {
        this.E = z;
        this.F = z2;
        this.G = z3;
        this.H = p50Var;
    }

    @Override // defpackage.n61
    public final s81 o(View view, s81 s81Var, o61 o61Var) {
        if (this.E) {
            o61Var.d = s81Var.a() + o61Var.d;
        }
        boolean zD0 = fa0.d0(view);
        if (this.F) {
            if (zD0) {
                o61Var.c = s81Var.b() + o61Var.c;
            } else {
                o61Var.a = s81Var.b() + o61Var.a;
            }
        }
        if (this.G) {
            if (zD0) {
                o61Var.a = s81Var.c() + o61Var.a;
            } else {
                o61Var.c = s81Var.c() + o61Var.c;
            }
        }
        int i = o61Var.a;
        int i2 = o61Var.b;
        int i3 = o61Var.c;
        int i4 = o61Var.d;
        WeakHashMap weakHashMap = f51.a;
        view.setPaddingRelative(i, i2, i3, i4);
        this.H.o(view, s81Var, o61Var);
        return s81Var;
    }
}
