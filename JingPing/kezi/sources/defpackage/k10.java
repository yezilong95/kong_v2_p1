package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.sidesheet.SideSheetBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class k10 extends jq0 {
    public final /* synthetic */ int s;
    public final SideSheetBehavior t;

    public /* synthetic */ k10(SideSheetBehavior sideSheetBehavior, int i) {
        this.s = i;
        this.t = sideSheetBehavior;
    }

    @Override // defpackage.jq0
    public final int A() {
        switch (this.s) {
            case 0:
                return -this.t.m;
            default:
                return u();
        }
    }

    @Override // defpackage.jq0
    public final int B(View view) {
        switch (this.s) {
            case 0:
                return view.getRight() + this.t.p;
            default:
                return view.getLeft() - this.t.p;
        }
    }

    @Override // defpackage.jq0
    public final int C(CoordinatorLayout coordinatorLayout) {
        switch (this.s) {
            case 0:
                return coordinatorLayout.getLeft();
            default:
                return coordinatorLayout.getRight();
        }
    }

    @Override // defpackage.jq0
    public final int D() {
        switch (this.s) {
            case 0:
                return 1;
            default:
                return 0;
        }
    }

    @Override // defpackage.jq0
    public final boolean G(float f) {
        switch (this.s) {
            case 0:
                if (f > 0.0f) {
                }
                break;
            default:
                if (f < 0.0f) {
                }
                break;
        }
        return false;
    }

    @Override // defpackage.jq0
    public final boolean H(View view) {
        switch (this.s) {
            case 0:
                if (view.getRight() < (u() - w()) / 2) {
                }
                break;
            default:
                if (view.getLeft() > (u() + this.t.n) / 2) {
                }
                break;
        }
        return false;
    }

    @Override // defpackage.jq0
    public final boolean I(float f, float f2) {
        switch (this.s) {
            case 0:
                if (Math.abs(f) > Math.abs(f2)) {
                    float fAbs = Math.abs(f);
                    this.t.getClass();
                    if (fAbs > 500) {
                    }
                }
                break;
            default:
                if (Math.abs(f) > Math.abs(f2)) {
                    float fAbs2 = Math.abs(f);
                    this.t.getClass();
                    if (fAbs2 > 500) {
                    }
                }
                break;
        }
        return false;
    }

    @Override // defpackage.jq0
    public final int a(ViewGroup.MarginLayoutParams marginLayoutParams) {
        switch (this.s) {
            case 0:
                return marginLayoutParams.leftMargin;
            default:
                return marginLayoutParams.rightMargin;
        }
    }

    @Override // defpackage.jq0
    public final float b(int i) {
        switch (this.s) {
            case 0:
                float fW = w();
                return (i - fW) / (u() - fW);
            default:
                float f = this.t.n;
                return (f - i) / (f - u());
        }
    }

    @Override // defpackage.jq0
    public final boolean g0(View view, float f) {
        switch (this.s) {
            case 0:
                float left = view.getLeft();
                SideSheetBehavior sideSheetBehavior = this.t;
                float fAbs = Math.abs((f * sideSheetBehavior.l) + left);
                sideSheetBehavior.getClass();
                if (fAbs > 0.5f) {
                }
                break;
            default:
                float right = view.getRight();
                SideSheetBehavior sideSheetBehavior2 = this.t;
                float fAbs2 = Math.abs((f * sideSheetBehavior2.l) + right);
                sideSheetBehavior2.getClass();
                if (fAbs2 > 0.5f) {
                }
                break;
        }
        return false;
    }

    @Override // defpackage.jq0
    public final void m0(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        switch (this.s) {
            case 0:
                marginLayoutParams.leftMargin = i;
                break;
            default:
                marginLayoutParams.rightMargin = i;
                break;
        }
    }

    @Override // defpackage.jq0
    public final void n0(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2) {
        switch (this.s) {
            case 0:
                if (i <= this.t.n) {
                    marginLayoutParams.leftMargin = i2;
                }
                break;
            default:
                int i3 = this.t.n;
                if (i <= i3) {
                    marginLayoutParams.rightMargin = i3 - i;
                }
                break;
        }
    }

    @Override // defpackage.jq0
    public final int t(ViewGroup.MarginLayoutParams marginLayoutParams) {
        switch (this.s) {
            case 0:
                return marginLayoutParams.leftMargin;
            default:
                return marginLayoutParams.rightMargin;
        }
    }

    @Override // defpackage.jq0
    public final int u() {
        switch (this.s) {
            case 0:
                SideSheetBehavior sideSheetBehavior = this.t;
                return Math.max(0, sideSheetBehavior.o + sideSheetBehavior.p);
            default:
                SideSheetBehavior sideSheetBehavior2 = this.t;
                return Math.max(0, (sideSheetBehavior2.n - sideSheetBehavior2.m) - sideSheetBehavior2.p);
        }
    }

    @Override // defpackage.jq0
    public final int w() {
        switch (this.s) {
            case 0:
                SideSheetBehavior sideSheetBehavior = this.t;
                return (-sideSheetBehavior.m) - sideSheetBehavior.p;
            default:
                return this.t.n;
        }
    }

    @Override // defpackage.jq0
    public final int z() {
        switch (this.s) {
            case 0:
                return this.t.p;
            default:
                return this.t.n;
        }
    }
}
