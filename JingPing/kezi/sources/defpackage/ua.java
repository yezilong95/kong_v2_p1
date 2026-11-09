package defpackage;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.k;
import com.google.android.material.carousel.CarouselLayoutManager;

/* JADX INFO: loaded from: classes.dex */
public final class ua extends va {
    public final /* synthetic */ CarouselLayoutManager b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ua(CarouselLayoutManager carouselLayoutManager) {
        super(0);
        this.b = carouselLayoutManager;
    }

    @Override // defpackage.va
    public final void a(RectF rectF, RectF rectF2, RectF rectF3) {
        float f = rectF2.left;
        float f2 = rectF3.left;
        if (f < f2 && rectF2.right > f2) {
            float f3 = f2 - f;
            rectF.left += f3;
            rectF2.left += f3;
        }
        float f4 = rectF2.right;
        float f5 = rectF3.right;
        if (f4 <= f5 || rectF2.left >= f5) {
            return;
        }
        float f6 = f4 - f5;
        rectF.right = Math.max(rectF.right - f6, rectF.left);
        rectF2.right = Math.max(rectF2.right - f6, rectF2.left);
    }

    @Override // defpackage.va
    public final float b(RecyclerView.LayoutParams layoutParams) {
        return ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
    }

    @Override // defpackage.va
    public final RectF c(float f, float f2, float f3, float f4) {
        return new RectF(f4, 0.0f, f2 - f4, f);
    }

    @Override // defpackage.va
    public final int d() {
        CarouselLayoutManager carouselLayoutManager = this.b;
        return carouselLayoutManager.o - carouselLayoutManager.B();
    }

    @Override // defpackage.va
    public final int e() {
        CarouselLayoutManager carouselLayoutManager = this.b;
        if (carouselLayoutManager.K0()) {
            return 0;
        }
        return carouselLayoutManager.n;
    }

    @Override // defpackage.va
    public final int f() {
        return 0;
    }

    @Override // defpackage.va
    public final int g() {
        return this.b.n;
    }

    @Override // defpackage.va
    public final int h() {
        CarouselLayoutManager carouselLayoutManager = this.b;
        if (carouselLayoutManager.K0()) {
            return carouselLayoutManager.n;
        }
        return 0;
    }

    @Override // defpackage.va
    public final int i() {
        return this.b.E();
    }

    @Override // defpackage.va
    public final void j(View view, int i, int i2) {
        k.L(view, i, this.b.E(), i2, d());
    }

    @Override // defpackage.va
    public final void k(RectF rectF, RectF rectF2, RectF rectF3) {
        if (rectF2.right <= rectF3.left) {
            rectF.right = ((float) Math.floor(rectF.right)) - 1.0f;
        }
        if (rectF2.left >= rectF3.right) {
            rectF.left = ((float) Math.ceil(rectF.left)) + 1.0f;
        }
    }

    @Override // defpackage.va
    public final void l(View view, Rect rect, float f, float f2) {
        view.offsetLeftAndRight((int) (f2 - (rect.left + f)));
    }
}
