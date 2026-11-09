package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.sidesheet.SideSheetBehavior;
import io.nats.client.BaseConsumeOptions;

/* JADX INFO: loaded from: classes.dex */
public final class m80 extends la0 {
    public final /* synthetic */ int k0;
    public final SideSheetBehavior l0;

    public /* synthetic */ m80(SideSheetBehavior sideSheetBehavior, int i) {
        this.k0 = i;
        this.l0 = sideSheetBehavior;
    }

    @Override // defpackage.la0
    public final int A(View view) {
        switch (this.k0) {
            case 0:
                return view.getRight() + this.l0.S;
            default:
                return view.getLeft() - this.l0.S;
        }
    }

    @Override // defpackage.la0
    public final int E(CoordinatorLayout coordinatorLayout) {
        switch (this.k0) {
            case 0:
                return coordinatorLayout.getLeft();
            default:
                return coordinatorLayout.getRight();
        }
    }

    @Override // defpackage.la0
    public final int G() {
        switch (this.k0) {
            case 0:
                return 1;
            default:
                return 0;
        }
    }

    @Override // defpackage.la0
    public final boolean O(float f) {
        switch (this.k0) {
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

    @Override // defpackage.la0
    public final boolean R(View view) {
        switch (this.k0) {
            case 0:
                if (view.getRight() < (t() - u()) / 2) {
                }
                break;
            default:
                if (view.getLeft() > (t() + this.l0.Q) / 2) {
                }
                break;
        }
        return false;
    }

    @Override // defpackage.la0
    public final boolean S(float f, float f2) {
        switch (this.k0) {
            case 0:
                if (Math.abs(f) > Math.abs(f2)) {
                    float fAbs = Math.abs(f);
                    this.l0.getClass();
                    if (fAbs > BaseConsumeOptions.DEFAULT_MESSAGE_COUNT) {
                    }
                }
                break;
            default:
                if (Math.abs(f) > Math.abs(f2)) {
                    float fAbs2 = Math.abs(f);
                    this.l0.getClass();
                    if (fAbs2 > BaseConsumeOptions.DEFAULT_MESSAGE_COUNT) {
                    }
                }
                break;
        }
        return false;
    }

    @Override // defpackage.la0
    public final int d(ViewGroup.MarginLayoutParams marginLayoutParams) {
        switch (this.k0) {
            case 0:
                return marginLayoutParams.leftMargin;
            default:
                return marginLayoutParams.rightMargin;
        }
    }

    @Override // defpackage.la0
    public final float e(int i) {
        switch (this.k0) {
            case 0:
                float fU = u();
                return (i - fU) / (t() - fU);
            default:
                float f = this.l0.Q;
                return (f - i) / (f - t());
        }
    }

    @Override // defpackage.la0
    public final boolean h0(View view, float f) {
        switch (this.k0) {
            case 0:
                float left = view.getLeft();
                SideSheetBehavior sideSheetBehavior = this.l0;
                float fAbs = Math.abs((f * sideSheetBehavior.O) + left);
                sideSheetBehavior.getClass();
                if (fAbs > 0.5f) {
                }
                break;
            default:
                float right = view.getRight();
                SideSheetBehavior sideSheetBehavior2 = this.l0;
                float fAbs2 = Math.abs((f * sideSheetBehavior2.O) + right);
                sideSheetBehavior2.getClass();
                if (fAbs2 > 0.5f) {
                }
                break;
        }
        return false;
    }

    @Override // defpackage.la0
    public final void l0(ViewGroup.MarginLayoutParams marginLayoutParams, int i) {
        switch (this.k0) {
            case 0:
                marginLayoutParams.leftMargin = i;
                break;
            default:
                marginLayoutParams.rightMargin = i;
                break;
        }
    }

    @Override // defpackage.la0
    public final void m0(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2) {
        switch (this.k0) {
            case 0:
                if (i <= this.l0.Q) {
                    marginLayoutParams.leftMargin = i2;
                }
                break;
            default:
                int i3 = this.l0.Q;
                if (i <= i3) {
                    marginLayoutParams.rightMargin = i3 - i;
                }
                break;
        }
    }

    @Override // defpackage.la0
    public final int q(ViewGroup.MarginLayoutParams marginLayoutParams) {
        switch (this.k0) {
            case 0:
                return marginLayoutParams.leftMargin;
            default:
                return marginLayoutParams.rightMargin;
        }
    }

    @Override // defpackage.la0
    public final int t() {
        switch (this.k0) {
            case 0:
                SideSheetBehavior sideSheetBehavior = this.l0;
                return Math.max(0, sideSheetBehavior.R + sideSheetBehavior.S);
            default:
                SideSheetBehavior sideSheetBehavior2 = this.l0;
                return Math.max(0, (sideSheetBehavior2.Q - sideSheetBehavior2.P) - sideSheetBehavior2.S);
        }
    }

    @Override // defpackage.la0
    public final int u() {
        switch (this.k0) {
            case 0:
                SideSheetBehavior sideSheetBehavior = this.l0;
                return (-sideSheetBehavior.P) - sideSheetBehavior.S;
            default:
                return this.l0.Q;
        }
    }

    @Override // defpackage.la0
    public final int v() {
        switch (this.k0) {
            case 0:
                return this.l0.S;
            default:
                return this.l0.Q;
        }
    }

    @Override // defpackage.la0
    public final int w() {
        switch (this.k0) {
            case 0:
                return -this.l0.P;
            default:
                return t();
        }
    }
}
