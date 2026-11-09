package androidx.recyclerview.widget;

import android.view.View;
import defpackage.ws;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public ws a;
    public int b;
    public int c;
    public boolean d;
    public boolean e;

    public a() {
        d();
    }

    public final void a() {
        this.c = this.d ? this.a.g() : this.a.k();
    }

    public final void b(View view, int i) {
        if (this.d) {
            int iB = this.a.b(view);
            ws wsVar = this.a;
            this.c = (Integer.MIN_VALUE == wsVar.a ? 0 : wsVar.l() - wsVar.a) + iB;
        } else {
            this.c = this.a.e(view);
        }
        this.b = i;
    }

    public final void c(View view, int i) {
        ws wsVar = this.a;
        int iL = Integer.MIN_VALUE == wsVar.a ? 0 : wsVar.l() - wsVar.a;
        if (iL >= 0) {
            b(view, i);
            return;
        }
        this.b = i;
        if (!this.d) {
            int iE = this.a.e(view);
            int iK = iE - this.a.k();
            this.c = iE;
            if (iK > 0) {
                int iG = (this.a.g() - Math.min(0, (this.a.g() - iL) - this.a.b(view))) - (this.a.c(view) + iE);
                if (iG < 0) {
                    this.c -= Math.min(iK, -iG);
                    return;
                }
                return;
            }
            return;
        }
        int iG2 = (this.a.g() - iL) - this.a.b(view);
        this.c = this.a.g() - iG2;
        if (iG2 > 0) {
            int iC = this.c - this.a.c(view);
            int iK2 = this.a.k();
            int iMin = iC - (Math.min(this.a.e(view) - iK2, 0) + iK2);
            if (iMin < 0) {
                this.c = Math.min(iG2, -iMin) + this.c;
            }
        }
    }

    public final void d() {
        this.b = -1;
        this.c = Integer.MIN_VALUE;
        this.d = false;
        this.e = false;
    }

    public final String toString() {
        return "AnchorInfo{mPosition=" + this.b + ", mCoordinate=" + this.c + ", mLayoutFromEnd=" + this.d + ", mValid=" + this.e + '}';
    }
}
