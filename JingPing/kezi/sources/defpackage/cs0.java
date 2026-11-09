package defpackage;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.OverScroller;
import java.util.Arrays;
import java.util.WeakHashMap;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public final class cs0 {
    public static final fd0 x = new fd0(1);
    public int a;
    public int b;
    public float[] d;
    public float[] e;
    public float[] f;
    public float[] g;
    public int[] h;
    public int[] i;
    public int[] j;
    public int k;
    public VelocityTracker l;
    public final float m;
    public float n;
    public int o;
    public final int p;
    public int q;
    public final OverScroller r;
    public final xi s;
    public View t;
    public boolean u;
    public final ViewGroup v;
    public int c = -1;
    public final v6 w = new v6(18, this);

    public cs0(Context context, ViewGroup viewGroup, xi xiVar) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (xiVar == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.v = viewGroup;
        this.s = xiVar;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        int i = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.p = i;
        this.o = i;
        this.b = viewConfiguration.getScaledTouchSlop();
        this.m = viewConfiguration.getScaledMaximumFlingVelocity();
        this.n = viewConfiguration.getScaledMinimumFlingVelocity();
        this.r = new OverScroller(context, x);
    }

    public final void a() {
        this.c = -1;
        float[] fArr = this.d;
        if (fArr != null) {
            Arrays.fill(fArr, 0.0f);
            Arrays.fill(this.e, 0.0f);
            Arrays.fill(this.f, 0.0f);
            Arrays.fill(this.g, 0.0f);
            Arrays.fill(this.h, 0);
            Arrays.fill(this.i, 0);
            Arrays.fill(this.j, 0);
            this.k = 0;
        }
        VelocityTracker velocityTracker = this.l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.l = null;
        }
    }

    public final void b(View view, int i) {
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = this.v;
        if (parent != viewGroup) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + viewGroup + ")");
        }
        this.t = view;
        this.c = i;
        this.s.K(view, i);
        p(1);
    }

    public final boolean c(float f, float f2, int i, int i2) {
        float fAbs = Math.abs(f);
        float fAbs2 = Math.abs(f2);
        if ((this.h[i] & i2) != i2 || (this.q & i2) == 0 || (this.j[i] & i2) == i2 || (this.i[i] & i2) == i2) {
            return false;
        }
        float f3 = this.b;
        if (fAbs <= f3 && fAbs2 <= f3) {
            return false;
        }
        if (fAbs < fAbs2 * 0.5f) {
            this.s.getClass();
        }
        return (this.i[i] & i2) == 0 && fAbs > ((float) this.b);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0044 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean d(android.view.View r4, float r5, float r6) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L4
            goto L45
        L4:
            xi r1 = r3.s
            int r4 = r1.C(r4)
            r2 = 1
            if (r4 <= 0) goto Lf
            r4 = r2
            goto L10
        Lf:
            r4 = r0
        L10:
            int r1 = r1.D()
            if (r1 <= 0) goto L18
            r1 = r2
            goto L19
        L18:
            r1 = r0
        L19:
            if (r4 == 0) goto L29
            if (r1 == 0) goto L29
            float r5 = r5 * r5
            float r6 = r6 * r6
            float r6 = r6 + r5
            int r4 = r3.b
            int r4 = r4 * r4
            float r4 = (float) r4
            int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r4 <= 0) goto L45
            goto L44
        L29:
            if (r4 == 0) goto L37
            float r4 = java.lang.Math.abs(r5)
            int r5 = r3.b
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L45
            goto L44
        L37:
            if (r1 == 0) goto L45
            float r4 = java.lang.Math.abs(r6)
            int r5 = r3.b
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L45
        L44:
            return r2
        L45:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cs0.d(android.view.View, float, float):boolean");
    }

    public final void e(int i) {
        float[] fArr = this.d;
        if (fArr != null) {
            int i2 = this.k;
            int i3 = 1 << i;
            if ((i2 & i3) != 0) {
                fArr[i] = 0.0f;
                this.e[i] = 0.0f;
                this.f[i] = 0.0f;
                this.g[i] = 0.0f;
                this.h[i] = 0;
                this.i[i] = 0;
                this.j[i] = 0;
                this.k = (~i3) & i2;
            }
        }
    }

    public final int f(int i, int i2, int i3) {
        if (i == 0) {
            return 0;
        }
        float width = this.v.getWidth() / 2;
        float fSin = (((float) Math.sin((Math.min(1.0f, Math.abs(i) / r0) - 0.5f) * 0.47123894f)) * width) + width;
        int iAbs = Math.abs(i2);
        return Math.min(iAbs > 0 ? Math.round(Math.abs(fSin / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i) / i3) + 1.0f) * 256.0f), 600);
    }

    public final boolean g() {
        if (this.a == 2) {
            OverScroller overScroller = this.r;
            boolean zComputeScrollOffset = overScroller.computeScrollOffset();
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int left = currX - this.t.getLeft();
            int top = currY - this.t.getTop();
            if (left != 0) {
                View view = this.t;
                WeakHashMap weakHashMap = xr0.a;
                view.offsetLeftAndRight(left);
            }
            if (top != 0) {
                View view2 = this.t;
                WeakHashMap weakHashMap2 = xr0.a;
                view2.offsetTopAndBottom(top);
            }
            if (left != 0 || top != 0) {
                this.s.M(this.t, currX, currY);
            }
            if (zComputeScrollOffset && currX == overScroller.getFinalX() && currY == overScroller.getFinalY()) {
                overScroller.abortAnimation();
                zComputeScrollOffset = false;
            }
            if (!zComputeScrollOffset) {
                this.v.post(this.w);
            }
        }
        return this.a == 2;
    }

    public final View h(int i, int i2) {
        ViewGroup viewGroup = this.v;
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            this.s.getClass();
            View childAt = viewGroup.getChildAt(childCount);
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public final boolean i(int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        float f4;
        int left = this.t.getLeft();
        int top = this.t.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        OverScroller overScroller = this.r;
        if (i5 == 0 && i6 == 0) {
            overScroller.abortAnimation();
            p(0);
            return false;
        }
        View view = this.t;
        int i7 = (int) this.n;
        int i8 = (int) this.m;
        int iAbs = Math.abs(i3);
        if (iAbs < i7) {
            i3 = 0;
        } else if (iAbs > i8) {
            i3 = i3 > 0 ? i8 : -i8;
        }
        int i9 = (int) this.n;
        int iAbs2 = Math.abs(i4);
        if (iAbs2 < i9) {
            i4 = 0;
        } else if (iAbs2 > i8) {
            i4 = i4 > 0 ? i8 : -i8;
        }
        int iAbs3 = Math.abs(i5);
        int iAbs4 = Math.abs(i6);
        int iAbs5 = Math.abs(i3);
        int iAbs6 = Math.abs(i4);
        int i10 = iAbs5 + iAbs6;
        int i11 = iAbs3 + iAbs4;
        if (i3 != 0) {
            f = iAbs5;
            f2 = i10;
        } else {
            f = iAbs3;
            f2 = i11;
        }
        float f5 = f / f2;
        if (i4 != 0) {
            f3 = iAbs6;
            f4 = i10;
        } else {
            f3 = iAbs4;
            f4 = i11;
        }
        float f6 = f3 / f4;
        xi xiVar = this.s;
        overScroller.startScroll(left, top, i5, i6, (int) ((f(i6, i4, xiVar.D()) * f6) + (f(i5, i3, xiVar.C(view)) * f5)));
        p(2);
        return true;
    }

    public final boolean j(int i) {
        if ((this.k & (1 << i)) != 0) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    public final void k(MotionEvent motionEvent) {
        int i;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            a();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(motionEvent);
        int i2 = 0;
        xi xiVar = this.s;
        if (actionMasked == 0) {
            float x2 = motionEvent.getX();
            float y = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View viewH = h((int) x2, (int) y);
            n(x2, y, pointerId);
            t(viewH, pointerId);
            if ((this.h[pointerId] & this.q) != 0) {
                xiVar.I();
                return;
            }
            return;
        }
        if (actionMasked == 1) {
            if (this.a == 1) {
                l();
            }
            a();
            return;
        }
        if (actionMasked == 2) {
            if (this.a != 1) {
                int pointerCount = motionEvent.getPointerCount();
                while (i2 < pointerCount) {
                    int pointerId2 = motionEvent.getPointerId(i2);
                    if (j(pointerId2)) {
                        float x3 = motionEvent.getX(i2);
                        float y2 = motionEvent.getY(i2);
                        float f = x3 - this.d[pointerId2];
                        float f2 = y2 - this.e[pointerId2];
                        m(f, f2, pointerId2);
                        if (this.a != 1) {
                            View viewH2 = h((int) x3, (int) y2);
                            if (d(viewH2, f, f2) && t(viewH2, pointerId2)) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    i2++;
                }
                o(motionEvent);
                return;
            }
            if (j(this.c)) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.c);
                float x4 = motionEvent.getX(iFindPointerIndex);
                float y3 = motionEvent.getY(iFindPointerIndex);
                float[] fArr = this.f;
                int i3 = this.c;
                int i4 = (int) (x4 - fArr[i3]);
                int i5 = (int) (y3 - this.g[i3]);
                int left = this.t.getLeft() + i4;
                int top = this.t.getTop() + i5;
                int left2 = this.t.getLeft();
                int top2 = this.t.getTop();
                if (i4 != 0) {
                    left = xiVar.g(this.t, left);
                    WeakHashMap weakHashMap = xr0.a;
                    this.t.offsetLeftAndRight(left - left2);
                }
                if (i5 != 0) {
                    top = xiVar.h(this.t, top);
                    WeakHashMap weakHashMap2 = xr0.a;
                    this.t.offsetTopAndBottom(top - top2);
                }
                if (i4 != 0 || i5 != 0) {
                    xiVar.M(this.t, left, top);
                }
                o(motionEvent);
                return;
            }
            return;
        }
        if (actionMasked == 3) {
            if (this.a == 1) {
                this.u = true;
                xiVar.N(this.t, 0.0f, 0.0f);
                this.u = false;
                if (this.a == 1) {
                    p(0);
                }
            }
            a();
            return;
        }
        if (actionMasked == 5) {
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            float x5 = motionEvent.getX(actionIndex);
            float y4 = motionEvent.getY(actionIndex);
            n(x5, y4, pointerId3);
            if (this.a == 0) {
                t(h((int) x5, (int) y4), pointerId3);
                if ((this.h[pointerId3] & this.q) != 0) {
                    xiVar.I();
                    return;
                }
                return;
            }
            int i6 = (int) x5;
            int i7 = (int) y4;
            View view = this.t;
            if (view != null && i6 >= view.getLeft() && i6 < view.getRight() && i7 >= view.getTop() && i7 < view.getBottom()) {
                t(this.t, pointerId3);
                return;
            }
            return;
        }
        if (actionMasked != 6) {
            return;
        }
        int pointerId4 = motionEvent.getPointerId(actionIndex);
        if (this.a == 1 && pointerId4 == this.c) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (true) {
                if (i2 >= pointerCount2) {
                    i = -1;
                    break;
                }
                int pointerId5 = motionEvent.getPointerId(i2);
                if (pointerId5 != this.c) {
                    View viewH3 = h((int) motionEvent.getX(i2), (int) motionEvent.getY(i2));
                    View view2 = this.t;
                    if (viewH3 == view2 && t(view2, pointerId5)) {
                        i = this.c;
                        break;
                    }
                }
                i2++;
            }
            if (i == -1) {
                l();
            }
        }
        e(pointerId4);
    }

    public final void l() {
        VelocityTracker velocityTracker = this.l;
        float f = this.m;
        velocityTracker.computeCurrentVelocity(CloseFrame.NORMAL, f);
        float xVelocity = this.l.getXVelocity(this.c);
        float f2 = this.n;
        float fAbs = Math.abs(xVelocity);
        if (fAbs < f2) {
            xVelocity = 0.0f;
        } else if (fAbs > f) {
            xVelocity = xVelocity > 0.0f ? f : -f;
        }
        float yVelocity = this.l.getYVelocity(this.c);
        float f3 = this.n;
        float fAbs2 = Math.abs(yVelocity);
        if (fAbs2 < f3) {
            f = 0.0f;
        } else if (fAbs2 <= f) {
            f = yVelocity;
        } else if (yVelocity <= 0.0f) {
            f = -f;
        }
        this.u = true;
        this.s.N(this.t, xVelocity, f);
        this.u = false;
        if (this.a == 1) {
            p(0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r3v3, types: [xi] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void m(float f, float f2, int i) {
        boolean zC = c(f, f2, i, 1);
        ?? r0 = zC;
        if (c(f2, f, i, 4)) {
            r0 = (zC ? 1 : 0) | 4;
        }
        ?? r02 = r0;
        if (c(f, f2, i, 2)) {
            r02 = (r0 == true ? 1 : 0) | 2;
        }
        ?? r03 = r02;
        if (c(f2, f, i, 8)) {
            r03 = (r02 == true ? 1 : 0) | 8;
        }
        if (r03 != 0) {
            int[] iArr = this.i;
            iArr[i] = iArr[i] | r03;
            this.s.H(r03, i);
        }
    }

    public final void n(float f, float f2, int i) {
        float[] fArr = this.d;
        if (fArr == null || fArr.length <= i) {
            int i2 = i + 1;
            float[] fArr2 = new float[i2];
            float[] fArr3 = new float[i2];
            float[] fArr4 = new float[i2];
            float[] fArr5 = new float[i2];
            int[] iArr = new int[i2];
            int[] iArr2 = new int[i2];
            int[] iArr3 = new int[i2];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.e;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.f;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.g;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.h;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.i;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.j;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.d = fArr2;
            this.e = fArr3;
            this.f = fArr4;
            this.g = fArr5;
            this.h = iArr;
            this.i = iArr2;
            this.j = iArr3;
        }
        float[] fArr9 = this.d;
        this.f[i] = f;
        fArr9[i] = f;
        float[] fArr10 = this.e;
        this.g[i] = f2;
        fArr10[i] = f2;
        int[] iArr7 = this.h;
        int i3 = (int) f;
        int i4 = (int) f2;
        ViewGroup viewGroup = this.v;
        int i5 = i3 < viewGroup.getLeft() + this.o ? 1 : 0;
        if (i4 < viewGroup.getTop() + this.o) {
            i5 |= 4;
        }
        if (i3 > viewGroup.getRight() - this.o) {
            i5 |= 2;
        }
        if (i4 > viewGroup.getBottom() - this.o) {
            i5 |= 8;
        }
        iArr7[i] = i5;
        this.k |= 1 << i;
    }

    public final void o(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (j(pointerId)) {
                float x2 = motionEvent.getX(i);
                float y = motionEvent.getY(i);
                this.f[pointerId] = x2;
                this.g[pointerId] = y;
            }
        }
    }

    public final void p(int i) {
        this.v.removeCallbacks(this.w);
        if (this.a != i) {
            this.a = i;
            this.s.L(i);
            if (this.a == 0) {
                this.t = null;
            }
        }
    }

    public final boolean q(int i, int i2) {
        if (this.u) {
            return i(i, i2, (int) this.l.getXVelocity(this.c), (int) this.l.getYVelocity(this.c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean r(android.view.MotionEvent r19) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cs0.r(android.view.MotionEvent):boolean");
    }

    public final boolean s(View view, int i, int i2) {
        this.t = view;
        this.c = -1;
        boolean zI = i(i, i2, 0, 0);
        if (!zI && this.a == 0 && this.t != null) {
            this.t = null;
        }
        return zI;
    }

    public final boolean t(View view, int i) {
        if (view == this.t && this.c == i) {
            return true;
        }
        if (view == null || !this.s.S(view, i)) {
            return false;
        }
        this.c = i;
        b(view, i);
        return true;
    }
}
