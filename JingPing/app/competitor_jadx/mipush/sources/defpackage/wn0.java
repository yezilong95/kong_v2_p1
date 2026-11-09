package defpackage;

import android.view.animation.BaseInterpolator;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.d;
import java.util.Arrays;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class wn0 implements Runnable {
    public int E;
    public int F;
    public OverScroller G;
    public Interpolator H;
    public boolean I;
    public boolean J;
    public final /* synthetic */ RecyclerView K;

    public wn0(RecyclerView recyclerView) {
        this.K = recyclerView;
        an0 an0Var = RecyclerView.Z0;
        this.H = an0Var;
        this.I = false;
        this.J = false;
        this.G = new OverScroller(recyclerView.getContext(), an0Var);
    }

    public final void a() {
        if (this.I) {
            this.J = true;
            return;
        }
        RecyclerView recyclerView = this.K;
        recyclerView.removeCallbacks(this);
        WeakHashMap weakHashMap = f51.a;
        recyclerView.postOnAnimation(this);
    }

    public final void b(int i, int i2, int i3, BaseInterpolator baseInterpolator) {
        int iRound;
        RecyclerView recyclerView = this.K;
        if (i3 == Integer.MIN_VALUE) {
            int iAbs = Math.abs(i);
            int iAbs2 = Math.abs(i2);
            boolean z = iAbs > iAbs2;
            int iSqrt = (int) Math.sqrt(0);
            int iSqrt2 = (int) Math.sqrt((i2 * i2) + (i * i));
            int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
            int i4 = width / 2;
            float f = width;
            float f2 = i4;
            float fSin = (((float) Math.sin((Math.min(1.0f, (iSqrt2 * 1.0f) / f) - 0.5f) * 0.47123894f)) * f2) + f2;
            if (iSqrt > 0) {
                iRound = Math.round(Math.abs(fSin / iSqrt) * 1000.0f) * 4;
            } else {
                if (!z) {
                    iAbs = iAbs2;
                }
                iRound = (int) (((iAbs / f) + 1.0f) * 300.0f);
            }
            i3 = Math.min(iRound, 2000);
        }
        int i5 = i3;
        Interpolator interpolator = baseInterpolator;
        if (baseInterpolator == null) {
            interpolator = RecyclerView.Z0;
        }
        if (this.H != interpolator) {
            this.H = interpolator;
            this.G = new OverScroller(recyclerView.getContext(), interpolator);
        }
        this.F = 0;
        this.E = 0;
        recyclerView.setScrollState(2);
        this.G.startScroll(0, 0, i, i2, i5);
        a();
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i;
        int i2;
        int i3;
        int i4;
        RecyclerView recyclerView = this.K;
        if (recyclerView.P == null) {
            recyclerView.removeCallbacks(this);
            this.G.abortAnimation();
            return;
        }
        this.J = false;
        this.I = true;
        recyclerView.l();
        OverScroller overScroller = this.G;
        if (overScroller.computeScrollOffset()) {
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int i5 = currX - this.E;
            int i6 = currY - this.F;
            this.E = currX;
            this.F = currY;
            int[] iArr = recyclerView.T0;
            iArr[0] = 0;
            iArr[1] = 0;
            boolean zR = recyclerView.r(i5, i6, 1, iArr, null);
            int[] iArr2 = recyclerView.T0;
            if (zR) {
                i = i5 - iArr2[0];
                i2 = i6 - iArr2[1];
            } else {
                i = i5;
                i2 = i6;
            }
            if (recyclerView.getOverScrollMode() != 2) {
                recyclerView.k(i, i2);
            }
            if (recyclerView.O != null) {
                iArr2[0] = 0;
                iArr2[1] = 0;
                recyclerView.Y(i, i2, iArr2);
                i3 = iArr2[0];
                i4 = iArr2[1];
                i -= i3;
                i2 -= i4;
                d dVar = recyclerView.P.e;
                if (dVar != null && !dVar.d && dVar.e) {
                    int iB = recyclerView.H0.b();
                    if (iB == 0) {
                        dVar.d();
                    } else if (dVar.a >= iB) {
                        dVar.a = iB - 1;
                        dVar.b(i3, i4);
                    } else {
                        dVar.b(i3, i4);
                    }
                }
            } else {
                i3 = 0;
                i4 = 0;
            }
            if (!recyclerView.Q.isEmpty()) {
                recyclerView.invalidate();
            }
            int[] iArr3 = recyclerView.T0;
            iArr3[0] = 0;
            iArr3[1] = 0;
            recyclerView.s(i3, i4, i, i2, null, 1, iArr3);
            int i7 = i - iArr2[0];
            int i8 = i2 - iArr2[1];
            if (i3 != 0 || i4 != 0) {
                recyclerView.t(i3, i4);
            }
            if (!recyclerView.awakenScrollBars()) {
                recyclerView.invalidate();
            }
            boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i7 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i8 != 0));
            d dVar2 = recyclerView.P.e;
            if ((dVar2 == null || !dVar2.d) && z) {
                if (recyclerView.getOverScrollMode() != 2) {
                    int currVelocity = (int) overScroller.getCurrVelocity();
                    int i9 = i7 < 0 ? -currVelocity : i7 > 0 ? currVelocity : 0;
                    if (i8 < 0) {
                        currVelocity = -currVelocity;
                    } else if (i8 <= 0) {
                        currVelocity = 0;
                    }
                    if (i9 < 0) {
                        recyclerView.v();
                        if (recyclerView.l0.isFinished()) {
                            recyclerView.l0.onAbsorb(-i9);
                        }
                    } else if (i9 > 0) {
                        recyclerView.w();
                        if (recyclerView.n0.isFinished()) {
                            recyclerView.n0.onAbsorb(i9);
                        }
                    }
                    if (currVelocity < 0) {
                        recyclerView.x();
                        if (recyclerView.m0.isFinished()) {
                            recyclerView.m0.onAbsorb(-currVelocity);
                        }
                    } else if (currVelocity > 0) {
                        recyclerView.u();
                        if (recyclerView.o0.isFinished()) {
                            recyclerView.o0.onAbsorb(currVelocity);
                        }
                    }
                    if (i9 != 0 || currVelocity != 0) {
                        WeakHashMap weakHashMap = f51.a;
                        recyclerView.postInvalidateOnAnimation();
                    }
                }
                w10 w10Var = recyclerView.G0;
                int[] iArr4 = (int[]) w10Var.d;
                if (iArr4 != null) {
                    Arrays.fill(iArr4, -1);
                }
                w10Var.c = 0;
            } else {
                a();
                y10 y10Var = recyclerView.F0;
                if (y10Var != null) {
                    y10Var.a(recyclerView, i3, i4);
                }
            }
        }
        d dVar3 = recyclerView.P.e;
        if (dVar3 != null && dVar3.d) {
            dVar3.b(0, 0);
        }
        this.I = false;
        if (!this.J) {
            recyclerView.setScrollState(0);
            recyclerView.e0(1);
        } else {
            recyclerView.removeCallbacks(this);
            WeakHashMap weakHashMap2 = f51.a;
            recyclerView.postOnAnimation(this);
        }
    }
}
