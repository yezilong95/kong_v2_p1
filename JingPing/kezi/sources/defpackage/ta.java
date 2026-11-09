package defpackage;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.k;
import com.google.android.material.carousel.CarouselLayoutManager;

/* JADX INFO: loaded from: classes.dex */
public final class ta extends va {
    public final /* synthetic */ CarouselLayoutManager b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ta(CarouselLayoutManager carouselLayoutManager) {
        super(1);
        this.b = carouselLayoutManager;
    }

    @Override // defpackage.va
    public final void a(RectF rectF, RectF rectF2, RectF rectF3) {
        float f = rectF2.top;
        float f2 = rectF3.top;
        if (f < f2 && rectF2.bottom > f2) {
            float f3 = f2 - f;
            rectF.top += f3;
            rectF3.top += f3;
        }
        float f4 = rectF2.bottom;
        float f5 = rectF3.bottom;
        if (f4 <= f5 || rectF2.top >= f5) {
            return;
        }
        float f6 = f4 - f5;
        rectF.bottom = Math.max(rectF.bottom - f6, rectF.top);
        rectF2.bottom = Math.max(rectF2.bottom - f6, rectF2.top);
    }

    @Override // defpackage.va
    public final float b(RecyclerView.LayoutParams layoutParams) {
        return ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
    }

    @Override // defpackage.va
    public final RectF c(float f, float f2, float f3, float f4) {
        return new RectF(0.0f, f3, f2, f - f3);
    }

    @Override // defpackage.va
    public final int d() {
        return this.b.o;
    }

    @Override // defpackage.va
    public final int e() {
        return this.b.o;
    }

    @Override // defpackage.va
    public final int f() {
        return this.b.C();
    }

    @Override // defpackage.va
    public final int g() {
        CarouselLayoutManager carouselLayoutManager = this.b;
        return carouselLayoutManager.n - carouselLayoutManager.D();
    }

    @Override // defpackage.va
    public final int h() {
        return 0;
    }

    @Override // defpackage.va
    public final int i() {
        return 0;
    }

    @Override // defpackage.va
    public final void j(View view, int i, int i2) {
        k.L(view, this.b.C(), i, g(), i2);
    }

    @Override // defpackage.va
    public final void k(RectF rectF, RectF rectF2, RectF rectF3) {
        if (rectF2.bottom <= rectF3.top) {
            rectF.bottom = ((float) Math.floor(rectF.bottom)) - 1.0f;
        }
        if (rectF2.top >= rectF3.bottom) {
            rectF.top = ((float) Math.ceil(rectF.top)) + 1.0f;
        }
    }

    @Override // defpackage.va
    public final void l(View view, Rect rect, float f, float f2) {
        view.offsetTopAndBottom((int) (f2 - (rect.top + f)));
    }
}
