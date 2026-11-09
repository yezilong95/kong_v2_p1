package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class op extends qd0 {
    public final /* synthetic */ qp a;

    public op(qp qpVar) {
        this.a = qpVar;
    }

    @Override // defpackage.qd0
    public final void b(RecyclerView recyclerView, int i, int i2) {
        int iComputeHorizontalScrollOffset = recyclerView.computeHorizontalScrollOffset();
        int iComputeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        qp qpVar = this.a;
        int iComputeVerticalScrollRange = qpVar.s.computeVerticalScrollRange();
        int i3 = qpVar.r;
        int i4 = iComputeVerticalScrollRange - i3;
        int i5 = qpVar.a;
        qpVar.t = i4 > 0 && i3 >= i5;
        int iComputeHorizontalScrollRange = qpVar.s.computeHorizontalScrollRange();
        int i6 = qpVar.q;
        boolean z = iComputeHorizontalScrollRange - i6 > 0 && i6 >= i5;
        qpVar.u = z;
        boolean z2 = qpVar.t;
        if (!z2 && !z) {
            if (qpVar.v != 0) {
                qpVar.g(0);
                return;
            }
            return;
        }
        if (z2) {
            float f = i3;
            qpVar.l = (int) ((((f / 2.0f) + iComputeVerticalScrollOffset) * f) / iComputeVerticalScrollRange);
            qpVar.k = Math.min(i3, (i3 * i3) / iComputeVerticalScrollRange);
        }
        if (qpVar.u) {
            float f2 = iComputeHorizontalScrollOffset;
            float f3 = i6;
            qpVar.o = (int) ((((f3 / 2.0f) + f2) * f3) / iComputeHorizontalScrollRange);
            qpVar.n = Math.min(i6, (i6 * i6) / iComputeHorizontalScrollRange);
        }
        int i7 = qpVar.v;
        if (i7 == 0 || i7 == 1) {
            qpVar.g(1);
        }
    }
}
