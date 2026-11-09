package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;
import androidx.recyclerview.widget.n;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class gd0 {
    public final /* synthetic */ RecyclerView a;

    public /* synthetic */ gd0(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public void a(n2 n2Var) {
        int i = n2Var.a;
        RecyclerView recyclerView = this.a;
        if (i == 1) {
            recyclerView.m.U(n2Var.b, n2Var.c);
            return;
        }
        if (i == 2) {
            recyclerView.m.X(n2Var.b, n2Var.c);
        } else if (i == 4) {
            recyclerView.m.Y(n2Var.b, n2Var.c);
        } else {
            if (i != 8) {
                return;
            }
            recyclerView.m.W(n2Var.b, n2Var.c);
        }
    }

    public n b(int i) {
        RecyclerView recyclerView = this.a;
        int iG = recyclerView.f.G();
        int i2 = 0;
        n nVar = null;
        while (true) {
            if (i2 >= iG) {
                break;
            }
            n nVarI = RecyclerView.I(recyclerView.f.F(i2));
            if (nVarI != null && !nVarI.h() && nVarI.c == i) {
                if (!((ArrayList) recyclerView.f.e).contains(nVarI.a)) {
                    nVar = nVarI;
                    break;
                }
                nVar = nVarI;
            }
            i2++;
        }
        if (nVar == null || ((ArrayList) recyclerView.f.e).contains(nVar.a)) {
            return null;
        }
        return nVar;
    }

    public void c(int i, int i2) {
        int i3;
        int i4;
        RecyclerView recyclerView = this.a;
        int iG = recyclerView.f.G();
        int i5 = i2 + i;
        for (int i6 = 0; i6 < iG; i6++) {
            View viewF = recyclerView.f.F(i6);
            n nVarI = RecyclerView.I(viewF);
            if (nVarI != null && !nVarI.o() && (i4 = nVarI.c) >= i && i4 < i5) {
                nVarI.a(2);
                nVarI.a(1024);
                ((RecyclerView.LayoutParams) viewF.getLayoutParams()).c = true;
            }
        }
        l lVar = recyclerView.c;
        ArrayList arrayList = lVar.c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            n nVar = (n) arrayList.get(size);
            if (nVar != null && (i3 = nVar.c) >= i && i3 < i5) {
                nVar.a(2);
                lVar.e(size);
            }
        }
        recyclerView.i0 = true;
    }

    public void d(int i, int i2) {
        RecyclerView recyclerView = this.a;
        int iG = recyclerView.f.G();
        for (int i3 = 0; i3 < iG; i3++) {
            n nVarI = RecyclerView.I(recyclerView.f.F(i3));
            if (nVarI != null && !nVarI.o() && nVarI.c >= i) {
                nVarI.l(i2, false);
                recyclerView.e0.f = true;
            }
        }
        ArrayList arrayList = recyclerView.c.c;
        int size = arrayList.size();
        for (int i4 = 0; i4 < size; i4++) {
            n nVar = (n) arrayList.get(i4);
            if (nVar != null && nVar.c >= i) {
                nVar.l(i2, true);
            }
        }
        recyclerView.requestLayout();
        recyclerView.h0 = true;
    }

    public void e(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        RecyclerView recyclerView = this.a;
        int iG = recyclerView.f.G();
        int i10 = -1;
        if (i < i2) {
            i4 = i;
            i3 = i2;
            i5 = -1;
        } else {
            i3 = i;
            i4 = i2;
            i5 = 1;
        }
        for (int i11 = 0; i11 < iG; i11++) {
            n nVarI = RecyclerView.I(recyclerView.f.F(i11));
            if (nVarI != null && (i9 = nVarI.c) >= i4 && i9 <= i3) {
                if (i9 == i) {
                    nVarI.l(i2 - i, false);
                } else {
                    nVarI.l(i5, false);
                }
                recyclerView.e0.f = true;
            }
        }
        l lVar = recyclerView.c;
        lVar.getClass();
        if (i < i2) {
            i7 = i;
            i6 = i2;
        } else {
            i6 = i;
            i7 = i2;
            i10 = 1;
        }
        ArrayList arrayList = lVar.c;
        int size = arrayList.size();
        for (int i12 = 0; i12 < size; i12++) {
            n nVar = (n) arrayList.get(i12);
            if (nVar != null && (i8 = nVar.c) >= i7 && i8 <= i6) {
                if (i8 == i) {
                    nVar.l(i2 - i, false);
                } else {
                    nVar.l(i10, false);
                }
            }
        }
        recyclerView.requestLayout();
        recyclerView.h0 = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f(androidx.recyclerview.widget.n r9, defpackage.n90 r10, defpackage.n90 r11) {
        /*
            r8 = this;
            androidx.recyclerview.widget.RecyclerView r0 = r8.a
            r0.getClass()
            r1 = 0
            r9.n(r1)
            ld0 r1 = r0.J
            r2 = r1
            mj r2 = (defpackage.mj) r2
            if (r10 == 0) goto L20
            r2.getClass()
            int r4 = r10.a
            int r6 = r11.a
            if (r4 != r6) goto L22
            int r1 = r10.b
            int r3 = r11.b
            if (r1 == r3) goto L20
            goto L22
        L20:
            r3 = r9
            goto L2c
        L22:
            int r5 = r10.b
            int r7 = r11.b
            r3 = r9
            boolean r9 = r2.g(r3, r4, r5, r6, r7)
            goto L3b
        L2c:
            r2.l(r3)
            android.view.View r9 = r3.a
            r10 = 0
            r9.setAlpha(r10)
            java.util.ArrayList r9 = r2.i
            r9.add(r3)
            r9 = 1
        L3b:
            if (r9 == 0) goto L40
            r0.S()
        L40:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gd0.f(androidx.recyclerview.widget.n, n90, n90):void");
    }

    public void g(n nVar, n90 n90Var, n90 n90Var2) {
        boolean zG;
        RecyclerView recyclerView = this.a;
        recyclerView.c.j(nVar);
        recyclerView.f(nVar);
        nVar.n(false);
        mj mjVar = (mj) recyclerView.J;
        mjVar.getClass();
        int i = n90Var.a;
        int i2 = n90Var.b;
        View view = nVar.a;
        int left = n90Var2 == null ? view.getLeft() : n90Var2.a;
        int top = n90Var2 == null ? view.getTop() : n90Var2.b;
        if (nVar.h() || (i == left && i2 == top)) {
            mjVar.l(nVar);
            mjVar.h.add(nVar);
            zG = true;
        } else {
            view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
            zG = mjVar.g(nVar, i, i2, left, top);
        }
        if (zG) {
            recyclerView.S();
        }
    }

    public void h(int i) {
        RecyclerView recyclerView = this.a;
        View childAt = recyclerView.getChildAt(i);
        if (childAt != null) {
            recyclerView.n(childAt);
            childAt.clearAnimation();
        }
        recyclerView.removeViewAt(i);
    }
}
