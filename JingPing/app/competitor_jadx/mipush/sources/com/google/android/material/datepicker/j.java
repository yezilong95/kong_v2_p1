package com.google.android.material.datepicker;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.fk0;
import defpackage.h31;
import defpackage.i5;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class j extends androidx.recyclerview.widget.h {
    public final Calendar a = h31.g(null);
    public final Calendar b = h31.g(null);
    public final /* synthetic */ l c;

    public j(l lVar) {
        this.c = lVar;
    }

    @Override // androidx.recyclerview.widget.h
    public final void b(Canvas canvas, RecyclerView recyclerView) {
        Object obj;
        if ((recyclerView.getAdapter() instanceof t) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
            t tVar = (t) recyclerView.getAdapter();
            GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            l lVar = this.c;
            for (fk0 fk0Var : lVar.x0.d()) {
                Object obj2 = fk0Var.a;
                if (obj2 != null && (obj = fk0Var.b) != null) {
                    long jLongValue = ((Long) obj2).longValue();
                    Calendar calendar = this.a;
                    calendar.setTimeInMillis(jLongValue);
                    long jLongValue2 = ((Long) obj).longValue();
                    Calendar calendar2 = this.b;
                    calendar2.setTimeInMillis(jLongValue2);
                    int i = calendar.get(1) - tVar.c.y0.E.G;
                    int i2 = calendar2.get(1) - tVar.c.y0.E.G;
                    View viewQ = gridLayoutManager.q(i);
                    View viewQ2 = gridLayoutManager.q(i2);
                    int i3 = gridLayoutManager.F;
                    int i4 = i / i3;
                    int i5 = i2 / i3;
                    int i6 = i4;
                    while (i6 <= i5) {
                        if (gridLayoutManager.q(gridLayoutManager.F * i6) != null) {
                            canvas.drawRect((i6 != i4 || viewQ == null) ? 0 : (viewQ.getWidth() / 2) + viewQ.getLeft(), r10.getTop() + ((Rect) ((i5) lVar.C0.d).c).top, (i6 != i5 || viewQ2 == null) ? recyclerView.getWidth() : (viewQ2.getWidth() / 2) + viewQ2.getLeft(), r10.getBottom() - ((Rect) ((i5) lVar.C0.d).c).bottom, (Paint) lVar.C0.h);
                        }
                        i6++;
                    }
                }
            }
        }
    }
}
