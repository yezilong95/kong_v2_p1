package defpackage;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.recyclerview.widget.d;
import androidx.recyclerview.widget.k;
import com.google.android.material.carousel.CarouselLayoutManager;

/* JADX INFO: loaded from: classes.dex */
public final class qa extends d {
    public final /* synthetic */ int q;
    public final /* synthetic */ Object r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qa(Object obj, Context context, int i) {
        super(context);
        this.q = i;
        this.r = obj;
    }

    @Override // defpackage.xd0
    public PointF a(int i) {
        switch (this.q) {
            case 0:
                return ((CarouselLayoutManager) this.r).a(i);
            default:
                return super.a(i);
        }
    }

    @Override // androidx.recyclerview.widget.d, defpackage.xd0
    public void c(View view, vd0 vd0Var) {
        switch (this.q) {
            case 1:
                fb0 fb0Var = (fb0) this.r;
                int[] iArrB = fb0Var.b(fb0Var.a.getLayoutManager(), view);
                int i = iArrB[0];
                int i2 = iArrB[1];
                int iCeil = (int) Math.ceil(((double) i(Math.max(Math.abs(i), Math.abs(i2)))) / 0.3356d);
                if (iCeil > 0) {
                    DecelerateInterpolator decelerateInterpolator = this.j;
                    vd0Var.a = i;
                    vd0Var.b = i2;
                    vd0Var.c = iCeil;
                    vd0Var.e = decelerateInterpolator;
                    vd0Var.f = true;
                }
                break;
            default:
                super.c(view, vd0Var);
                break;
        }
    }

    @Override // androidx.recyclerview.widget.d
    public int f(View view, int i) {
        switch (this.q) {
            case 0:
                CarouselLayoutManager carouselLayoutManager = (CarouselLayoutManager) this.r;
                if (carouselLayoutManager.u == null || !carouselLayoutManager.J0()) {
                    return 0;
                }
                int iF = k.F(view);
                return (int) (carouselLayoutManager.p - carouselLayoutManager.H0(iF, carouselLayoutManager.G0(iF)));
            default:
                return super.f(view, i);
        }
    }

    @Override // androidx.recyclerview.widget.d
    public int g(View view, int i) {
        switch (this.q) {
            case 0:
                CarouselLayoutManager carouselLayoutManager = (CarouselLayoutManager) this.r;
                if (carouselLayoutManager.u == null || carouselLayoutManager.J0()) {
                    return 0;
                }
                int iF = k.F(view);
                return (int) (carouselLayoutManager.p - carouselLayoutManager.H0(iF, carouselLayoutManager.G0(iF)));
            default:
                return super.g(view, i);
        }
    }

    @Override // androidx.recyclerview.widget.d
    public float h(DisplayMetrics displayMetrics) {
        switch (this.q) {
            case 1:
                return 100.0f / displayMetrics.densityDpi;
            default:
                return super.h(displayMetrics);
        }
    }

    @Override // androidx.recyclerview.widget.d
    public int i(int i) {
        switch (this.q) {
            case 1:
                return Math.min(100, super.i(i));
            default:
                return super.i(i);
        }
    }
}
