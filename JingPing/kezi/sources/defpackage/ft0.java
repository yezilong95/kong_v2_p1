package defpackage;

import android.view.View;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ft0 implements ht0 {
    public final /* synthetic */ boolean b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ boolean d;
    public final /* synthetic */ u8 e;

    public ft0(boolean z, boolean z2, boolean z3, u8 u8Var) {
        this.b = z;
        this.c = z2;
        this.d = z3;
        this.e = u8Var;
    }

    @Override // defpackage.ht0
    public final xu0 f(View view, xu0 xu0Var, it0 it0Var) {
        if (this.b) {
            it0Var.d = xu0Var.a() + it0Var.d;
        }
        boolean zI = rp0.I(view);
        if (this.c) {
            if (zI) {
                it0Var.c = xu0Var.b() + it0Var.c;
            } else {
                it0Var.a = xu0Var.b() + it0Var.a;
            }
        }
        if (this.d) {
            if (zI) {
                it0Var.a = xu0Var.c() + it0Var.a;
            } else {
                it0Var.c = xu0Var.c() + it0Var.c;
            }
        }
        int i = it0Var.a;
        int i2 = it0Var.b;
        int i3 = it0Var.c;
        int i4 = it0Var.d;
        WeakHashMap weakHashMap = xr0.a;
        view.setPaddingRelative(i, i2, i3, i4);
        this.e.f(view, xu0Var, it0Var);
        return xu0Var;
    }
}
