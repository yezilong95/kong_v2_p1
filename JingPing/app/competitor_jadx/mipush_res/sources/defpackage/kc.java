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
public final class kc extends d {
    public final /* synthetic */ int q;
    public final /* synthetic */ Object r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ kc(Object obj, Context context, int i) {
        super(context);
        this.q = i;
        this.r = obj;
    }

    @Override // defpackage.tn0
    public PointF a(int i) {
        switch (this.q) {
            case 0:
                return ((CarouselLayoutManager) this.r).a(i);
            default:
                return super.a(i);
        }
    }

    @Override // androidx.recyclerview.widget.d, defpackage.tn0
    public void c(View view, rn0 rn0Var) {
        switch (this.q) {
            case 1:
                zj0 zj0Var = (zj0) this.r;
                int[] iArrB = zj0Var.b(zj0Var.a.getLayoutManager(), view);
                int i = iArrB[0];
                int i2 = iArrB[1];
                int iCeil = (int) Math.ceil(((double) i(Math.max(Math.abs(i), Math.abs(i2)))) / 0.3356d);
                if (iCeil > 0) {
                    DecelerateInterpolator decelerateInterpolator = this.j;
                    rn0Var.a = i;
                    rn0Var.b = i2;
                    rn0Var.c = iCeil;
                    rn0Var.e = decelerateInterpolator;
                    rn0Var.f = true;
                }
                break;
            default:
                super.c(view, rn0Var);
                break;
        }
    }

    @Override // androidx.recyclerview.widget.d
    public int f(View view, int i) {
        switch (this.q) {
            case 0:
                CarouselLayoutManager carouselLayoutManager = (CarouselLayoutManager) this.r;
                if (carouselLayoutManager.u == null || !carouselLayoutManager.O0()) {
                    return 0;
                }
                int iH = k.H(view);
                return (int) (carouselLayoutManager.p - carouselLayoutManager.L0(iH, carouselLayoutManager.K0(iH)));
            default:
                return super.f(view, i);
        }
    }

    @Override // androidx.recyclerview.widget.d
    public int g(View view, int i) {
        switch (this.q) {
            case 0:
                CarouselLayoutManager carouselLayoutManager = (CarouselLayoutManager) this.r;
                if (carouselLayoutManager.u == null || carouselLayoutManager.O0()) {
                    return 0;
                }
                int iH = k.H(view);
                return (int) (carouselLayoutManager.p - carouselLayoutManager.L0(iH, carouselLayoutManager.K0(iH)));
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
