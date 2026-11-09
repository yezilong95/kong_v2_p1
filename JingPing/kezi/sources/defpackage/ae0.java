package defpackage;

import android.view.animation.BaseInterpolator;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.d;
import java.util.Arrays;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ae0 implements Runnable {
    public int b;
    public int c;
    public OverScroller d;
    public Interpolator e;
    public boolean f;
    public boolean g;
    public final /* synthetic */ RecyclerView h;

    public ae0(RecyclerView recyclerView) {
        this.h = recyclerView;
        fd0 fd0Var = RecyclerView.w0;
        this.e = fd0Var;
        this.f = false;
        this.g = false;
        this.d = new OverScroller(recyclerView.getContext(), fd0Var);
    }

    public final void a() {
        if (this.f) {
            this.g = true;
            return;
        }
        RecyclerView recyclerView = this.h;
        recyclerView.removeCallbacks(this);
        WeakHashMap weakHashMap = xr0.a;
        recyclerView.postOnAnimation(this);
    }

    public final void b(int i, int i2, int i3, BaseInterpolator baseInterpolator) {
        int iRound;
        RecyclerView recyclerView = this.h;
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
            interpolator = RecyclerView.w0;
        }
        if (this.e != interpolator) {
            this.e = interpolator;
            this.d = new OverScroller(recyclerView.getContext(), interpolator);
        }
        this.c = 0;
        this.b = 0;
        recyclerView.setScrollState(2);
        this.d.startScroll(0, 0, i, i2, i5);
        a();
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i;
        int i2;
        int i3;
        int i4;
        RecyclerView recyclerView = this.h;
        if (recyclerView.m == null) {
            recyclerView.removeCallbacks(this);
            this.d.abortAnimation();
            return;
        }
        this.g = false;
        this.f = true;
        recyclerView.l();
        OverScroller overScroller = this.d;
        if (overScroller.computeScrollOffset()) {
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int i5 = currX - this.b;
            int i6 = currY - this.c;
            this.b = currX;
            this.c = currY;
            int[] iArr = recyclerView.q0;
            iArr[0] = 0;
            iArr[1] = 0;
            boolean zR = recyclerView.r(i5, i6, iArr, null, 1);
            int[] iArr2 = recyclerView.q0;
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
            if (recyclerView.l != null) {
                iArr2[0] = 0;
                iArr2[1] = 0;
                recyclerView.Y(i, i2, iArr2);
                i3 = iArr2[0];
                i4 = iArr2[1];
                i -= i3;
                i2 -= i4;
                d dVar = recyclerView.m.e;
                if (dVar != null && !dVar.d && dVar.e) {
                    int iB = recyclerView.e0.b();
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
            if (!recyclerView.n.isEmpty()) {
                recyclerView.invalidate();
            }
            int[] iArr3 = recyclerView.q0;
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
            d dVar2 = recyclerView.m.e;
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
                        if (recyclerView.F.isFinished()) {
                            recyclerView.F.onAbsorb(-i9);
                        }
                    } else if (i9 > 0) {
                        recyclerView.w();
                        if (recyclerView.H.isFinished()) {
                            recyclerView.H.onAbsorb(i9);
                        }
                    }
                    if (currVelocity < 0) {
                        recyclerView.x();
                        if (recyclerView.G.isFinished()) {
                            recyclerView.G.onAbsorb(-currVelocity);
                        }
                    } else if (currVelocity > 0) {
                        recyclerView.u();
                        if (recyclerView.I.isFinished()) {
                            recyclerView.I.onAbsorb(currVelocity);
                        }
                    }
                    if (i9 != 0 || currVelocity != 0) {
                        WeakHashMap weakHashMap = xr0.a;
                        recyclerView.postInvalidateOnAnimation();
                    }
                }
                zu zuVar = recyclerView.d0;
                int[] iArr4 = (int[]) zuVar.d;
                if (iArr4 != null) {
                    Arrays.fill(iArr4, -1);
                }
                zuVar.c = 0;
            } else {
                a();
                bv bvVar = recyclerView.c0;
                if (bvVar != null) {
                    bvVar.a(recyclerView, i3, i4);
                }
            }
        }
        d dVar3 = recyclerView.m.e;
        if (dVar3 != null && dVar3.d) {
            dVar3.b(0, 0);
        }
        this.f = false;
        if (!this.g) {
            recyclerView.setScrollState(0);
            recyclerView.e0(1);
        } else {
            recyclerView.removeCallbacks(this);
            WeakHashMap weakHashMap2 = xr0.a;
            recyclerView.postOnAnimation(this);
        }
    }
}
