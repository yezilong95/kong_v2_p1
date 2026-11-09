package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.h;
import com.google.android.material.R$dimen;
import com.google.android.material.carousel.CarouselLayoutManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class sa extends h {
    public final Paint a;
    public List b;

    public sa() {
        Paint paint = new Paint();
        this.a = paint;
        this.b = Collections.unmodifiableList(new ArrayList());
        paint.setStrokeWidth(5.0f);
        paint.setColor(-65281);
    }

    @Override // androidx.recyclerview.widget.h
    public final void c(Canvas canvas, RecyclerView recyclerView) {
        Canvas canvas2;
        Paint paint = this.a;
        paint.setStrokeWidth(recyclerView.getResources().getDimension(R$dimen.m3_carousel_debug_keyline_width));
        for (d10 d10Var : this.b) {
            paint.setColor(je.b(-65281, d10Var.c, -16776961));
            if (((CarouselLayoutManager) recyclerView.getLayoutManager()).J0()) {
                float fI = ((CarouselLayoutManager) recyclerView.getLayoutManager()).y.i();
                float fD = ((CarouselLayoutManager) recyclerView.getLayoutManager()).y.d();
                float f = d10Var.b;
                canvas2 = canvas;
                canvas2.drawLine(f, fI, f, fD, paint);
            } else {
                float f2 = ((CarouselLayoutManager) recyclerView.getLayoutManager()).y.f();
                float fG = ((CarouselLayoutManager) recyclerView.getLayoutManager()).y.g();
                float f3 = d10Var.b;
                canvas2 = canvas;
                canvas2.drawLine(f2, f3, fG, f3, paint);
            }
            canvas = canvas2;
        }
    }
}
