package defpackage;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ad0 {
    public final wc0 a;
    public final ArrayList b;
    public final int c;
    public final so d;
    public final z4 e;
    public final int f;
    public final int g;
    public final int h;
    public int i;

    public ad0(wc0 wc0Var, ArrayList arrayList, int i, so soVar, z4 z4Var, int i2, int i3, int i4) {
        oy.j(wc0Var, "call");
        this.a = wc0Var;
        this.b = arrayList;
        this.c = i;
        this.d = soVar;
        this.e = z4Var;
        this.f = i2;
        this.g = i3;
        this.h = i4;
    }

    public static ad0 a(ad0 ad0Var, int i, so soVar, z4 z4Var, int i2) {
        if ((i2 & 1) != 0) {
            i = ad0Var.c;
        }
        int i3 = i;
        if ((i2 & 2) != 0) {
            soVar = ad0Var.d;
        }
        so soVar2 = soVar;
        if ((i2 & 4) != 0) {
            z4Var = ad0Var.e;
        }
        z4 z4Var2 = z4Var;
        oy.j(z4Var2, "request");
        return new ad0(ad0Var.a, ad0Var.b, i3, soVar2, z4Var2, ad0Var.f, ad0Var.g, ad0Var.h);
    }

    public final gf0 b(z4 z4Var) {
        oy.j(z4Var, "request");
        ArrayList arrayList = this.b;
        int size = arrayList.size();
        int i = this.c;
        if (i >= size) {
            throw new IllegalStateException("Check failed.");
        }
        this.i++;
        so soVar = this.d;
        if (soVar != null) {
            if (!soVar.b.b((fx) z4Var.b)) {
                throw new IllegalStateException(("network interceptor " + arrayList.get(i - 1) + " must retain the same host and port").toString());
            }
            if (this.i != 1) {
                throw new IllegalStateException(("network interceptor " + arrayList.get(i - 1) + " must call proceed() exactly once").toString());
            }
        }
        int i2 = i + 1;
        ad0 ad0VarA = a(this, i2, null, z4Var, 58);
        ny nyVar = (ny) arrayList.get(i);
        gf0 gf0VarA = nyVar.a(ad0VarA);
        if (gf0VarA == null) {
            throw new NullPointerException("interceptor " + nyVar + " returned null");
        }
        if (soVar != null && i2 < arrayList.size() && ad0VarA.i != 1) {
            throw new IllegalStateException(("network interceptor " + nyVar + " must call proceed() exactly once").toString());
        }
        if (gf0VarA.h != null) {
            return gf0VarA;
        }
        throw new IllegalStateException(("interceptor " + nyVar + " returned a response with no body").toString());
    }
}
