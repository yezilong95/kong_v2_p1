package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import androidx.appcompat.R$dimen;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class i11 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    public static i11 O;
    public static i11 P;
    public final View E;
    public final CharSequence F;
    public final int G;
    public final h11 H;
    public final h11 I;
    public int J;
    public int K;
    public k11 L;
    public boolean M;
    public boolean N;

    /* JADX WARN: Type inference failed for: r0v0, types: [h11] */
    /* JADX WARN: Type inference failed for: r0v1, types: [h11] */
    public i11(View view, CharSequence charSequence) {
        final int i = 0;
        this.H = new Runnable(this) { // from class: h11
            public final /* synthetic */ i11 F;

            {
                this.F = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i) {
                    case 0:
                        this.F.c(false);
                        break;
                    default:
                        this.F.a();
                        break;
                }
            }
        };
        final int i2 = 1;
        this.I = new Runnable(this) { // from class: h11
            public final /* synthetic */ i11 F;

            {
                this.F = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i2) {
                    case 0:
                        this.F.c(false);
                        break;
                    default:
                        this.F.a();
                        break;
                }
            }
        };
        this.E = view;
        this.F = charSequence;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(view.getContext());
        Method method = j51.a;
        this.G = Build.VERSION.SDK_INT >= 28 ? h51.a(viewConfiguration) : viewConfiguration.getScaledTouchSlop() / 2;
        this.N = true;
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    public static void b(i11 i11Var) {
        i11 i11Var2 = O;
        if (i11Var2 != null) {
            i11Var2.E.removeCallbacks(i11Var2.H);
        }
        O = i11Var;
        if (i11Var != null) {
            i11Var.E.postDelayed(i11Var.H, ViewConfiguration.getLongPressTimeout());
        }
    }

    public final void a() {
        i11 i11Var = P;
        View view = this.E;
        if (i11Var == this) {
            P = null;
            k11 k11Var = this.L;
            if (k11Var != null) {
                View view2 = k11Var.b;
                if (view2.getParent() != null) {
                    ((WindowManager) k11Var.a.getSystemService("window")).removeView(view2);
                }
                this.L = null;
                this.N = true;
                view.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (O == this) {
            b(null);
        }
        view.removeCallbacks(this.I);
    }

    public final void c(boolean z) {
        int height;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        long longPressTimeout;
        long j;
        long j2;
        WeakHashMap weakHashMap = f51.a;
        View view = this.E;
        if (view.isAttachedToWindow()) {
            b(null);
            i11 i11Var = P;
            if (i11Var != null) {
                i11Var.a();
            }
            P = this;
            this.M = z;
            k11 k11Var = new k11(view.getContext());
            this.L = k11Var;
            int width = this.J;
            int i6 = this.K;
            boolean z2 = this.M;
            View view2 = k11Var.b;
            ViewParent parent = view2.getParent();
            Context context = k11Var.a;
            if (parent != null && view2.getParent() != null) {
                ((WindowManager) context.getSystemService("window")).removeView(view2);
            }
            k11Var.c.setText(this.F);
            WindowManager.LayoutParams layoutParams = k11Var.d;
            layoutParams.token = view.getApplicationWindowToken();
            int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R$dimen.tooltip_precise_anchor_threshold);
            if (view.getWidth() < dimensionPixelOffset) {
                width = view.getWidth() / 2;
            }
            if (view.getHeight() >= dimensionPixelOffset) {
                int dimensionPixelOffset2 = context.getResources().getDimensionPixelOffset(R$dimen.tooltip_precise_anchor_extra_offset);
                height = i6 + dimensionPixelOffset2;
                i = i6 - dimensionPixelOffset2;
            } else {
                height = view.getHeight();
                i = 0;
            }
            layoutParams.gravity = 49;
            int dimensionPixelOffset3 = context.getResources().getDimensionPixelOffset(z2 ? R$dimen.tooltip_y_offset_touch : R$dimen.tooltip_y_offset_non_touch);
            View rootView = view.getRootView();
            ViewGroup.LayoutParams layoutParams2 = rootView.getLayoutParams();
            if (!(layoutParams2 instanceof WindowManager.LayoutParams) || ((WindowManager.LayoutParams) layoutParams2).type != 2) {
                Context context2 = view.getContext();
                while (true) {
                    if (!(context2 instanceof ContextWrapper)) {
                        break;
                    }
                    if (context2 instanceof Activity) {
                        rootView = ((Activity) context2).getWindow().getDecorView();
                        break;
                    }
                    context2 = ((ContextWrapper) context2).getBaseContext();
                }
            }
            if (rootView == null) {
                Log.e("TooltipPopup", "Cannot find app view");
                i5 = 1;
            } else {
                Rect rect = k11Var.e;
                rootView.getWindowVisibleDisplayFrame(rect);
                if (rect.left >= 0 || rect.top >= 0) {
                    i2 = width;
                    i3 = i;
                    i4 = 0;
                    i5 = 1;
                } else {
                    Resources resources = context.getResources();
                    i5 = 1;
                    i2 = width;
                    i3 = i;
                    int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
                    int dimensionPixelSize = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
                    DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                    i4 = 0;
                    rect.set(0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels);
                }
                int[] iArr = k11Var.g;
                rootView.getLocationOnScreen(iArr);
                int[] iArr2 = k11Var.f;
                view.getLocationOnScreen(iArr2);
                int i7 = iArr2[i4] - iArr[i4];
                iArr2[i4] = i7;
                iArr2[i5] = iArr2[i5] - iArr[i5];
                layoutParams.x = (i7 + i2) - (rootView.getWidth() / 2);
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i4, i4);
                view2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                int measuredHeight = view2.getMeasuredHeight();
                int i8 = iArr2[i5];
                int i9 = ((i8 + i3) - dimensionPixelOffset3) - measuredHeight;
                int i10 = i8 + height + dimensionPixelOffset3;
                if (z2) {
                    if (i9 >= 0) {
                        layoutParams.y = i9;
                    } else {
                        layoutParams.y = i10;
                    }
                } else if (measuredHeight + i10 <= rect.height()) {
                    layoutParams.y = i10;
                } else {
                    layoutParams.y = i9;
                }
            }
            ((WindowManager) context.getSystemService("window")).addView(view2, layoutParams);
            view.addOnAttachStateChangeListener(this);
            if (this.M) {
                j2 = 2500;
            } else {
                if ((view.getWindowSystemUiVisibility() & 1) == i5) {
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                    j = 3000;
                } else {
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                    j = 15000;
                }
                j2 = j - longPressTimeout;
            }
            h11 h11Var = this.I;
            view.removeCallbacks(h11Var);
            view.postDelayed(h11Var, j2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0066  */
    @Override // android.view.View.OnHoverListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onHover(android.view.View r4, android.view.MotionEvent r5) {
        /*
            r3 = this;
            k11 r4 = r3.L
            r0 = 0
            if (r4 == 0) goto La
            boolean r4 = r3.M
            if (r4 == 0) goto La
            goto L6f
        La:
            android.view.View r4 = r3.E
            android.content.Context r1 = r4.getContext()
            java.lang.String r2 = "accessibility"
            java.lang.Object r1 = r1.getSystemService(r2)
            android.view.accessibility.AccessibilityManager r1 = (android.view.accessibility.AccessibilityManager) r1
            boolean r2 = r1.isEnabled()
            if (r2 == 0) goto L25
            boolean r1 = r1.isTouchExplorationEnabled()
            if (r1 == 0) goto L25
            goto L6f
        L25:
            int r1 = r5.getAction()
            r2 = 7
            if (r1 == r2) goto L38
            r4 = 10
            if (r1 == r4) goto L31
            goto L6f
        L31:
            r4 = 1
            r3.N = r4
            r3.a()
            return r0
        L38:
            boolean r4 = r4.isEnabled()
            if (r4 == 0) goto L6f
            k11 r4 = r3.L
            if (r4 != 0) goto L6f
            float r4 = r5.getX()
            int r4 = (int) r4
            float r5 = r5.getY()
            int r5 = (int) r5
            boolean r1 = r3.N
            if (r1 != 0) goto L66
            int r1 = r3.J
            int r1 = r4 - r1
            int r1 = java.lang.Math.abs(r1)
            int r2 = r3.G
            if (r1 > r2) goto L66
            int r1 = r3.K
            int r1 = r5 - r1
            int r1 = java.lang.Math.abs(r1)
            if (r1 <= r2) goto L6f
        L66:
            r3.J = r4
            r3.K = r5
            r3.N = r0
            b(r3)
        L6f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.i11.onHover(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        this.J = view.getWidth() / 2;
        this.K = view.getHeight() / 2;
        c(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        a();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }
}
