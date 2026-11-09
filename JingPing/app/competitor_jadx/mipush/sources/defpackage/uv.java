package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class uv extends mn0 {
    public final /* synthetic */ vv a;

    public uv(vv vvVar) {
        this.a = vvVar;
    }

    @Override // defpackage.mn0
    public final void b(RecyclerView recyclerView, int i, int i2) {
        int iComputeHorizontalScrollOffset = recyclerView.computeHorizontalScrollOffset();
        int iComputeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        vv vvVar = this.a;
        int iComputeVerticalScrollRange = vvVar.s.computeVerticalScrollRange();
        int i3 = vvVar.r;
        int i4 = iComputeVerticalScrollRange - i3;
        int i5 = vvVar.a;
        vvVar.t = i4 > 0 && i3 >= i5;
        int iComputeHorizontalScrollRange = vvVar.s.computeHorizontalScrollRange();
        int i6 = vvVar.q;
        boolean z = iComputeHorizontalScrollRange - i6 > 0 && i6 >= i5;
        vvVar.u = z;
        boolean z2 = vvVar.t;
        if (!z2 && !z) {
            if (vvVar.v != 0) {
                vvVar.g(0);
                return;
            }
            return;
        }
        if (z2) {
            float f = i3;
            vvVar.l = (int) ((((f / 2.0f) + iComputeVerticalScrollOffset) * f) / iComputeVerticalScrollRange);
            vvVar.k = Math.min(i3, (i3 * i3) / iComputeVerticalScrollRange);
        }
        if (vvVar.u) {
            float f2 = iComputeHorizontalScrollOffset;
            float f3 = i6;
            vvVar.o = (int) ((((f3 / 2.0f) + f2) * f3) / iComputeHorizontalScrollRange);
            vvVar.n = Math.min(i6, (i6 * i6) / iComputeHorizontalScrollRange);
        }
        int i7 = vvVar.v;
        if (i7 == 0 || i7 == 1) {
            vvVar.g(1);
        }
    }
}
