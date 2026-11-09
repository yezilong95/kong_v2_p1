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
public final class wo0 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    public static wo0 l;
    public static wo0 m;
    public final View b;
    public final CharSequence c;
    public final int d;
    public final vo0 e;
    public final vo0 f;
    public int g;
    public int h;
    public yo0 i;
    public boolean j;
    public boolean k;

    /* JADX WARN: Type inference failed for: r0v0, types: [vo0] */
    /* JADX WARN: Type inference failed for: r0v1, types: [vo0] */
    public wo0(View view, CharSequence charSequence) {
        final int i = 0;
        this.e = new Runnable(this) { // from class: vo0
            public final /* synthetic */ wo0 c;

            {
                this.c = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i) {
                    case 0:
                        this.c.c(false);
                        break;
                    default:
                        this.c.a();
                        break;
                }
            }
        };
        final int i2 = 1;
        this.f = new Runnable(this) { // from class: vo0
            public final /* synthetic */ wo0 c;

            {
                this.c = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                switch (i2) {
                    case 0:
                        this.c.c(false);
                        break;
                    default:
                        this.c.a();
                        break;
                }
            }
        };
        this.b = view;
        this.c = charSequence;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(view.getContext());
        Method method = bs0.a;
        this.d = Build.VERSION.SDK_INT >= 28 ? zr0.a(viewConfiguration) : viewConfiguration.getScaledTouchSlop() / 2;
        this.k = true;
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    public static void b(wo0 wo0Var) {
        wo0 wo0Var2 = l;
        if (wo0Var2 != null) {
            wo0Var2.b.removeCallbacks(wo0Var2.e);
        }
        l = wo0Var;
        if (wo0Var != null) {
            wo0Var.b.postDelayed(wo0Var.e, ViewConfiguration.getLongPressTimeout());
        }
    }

    public final void a() {
        wo0 wo0Var = m;
        View view = this.b;
        if (wo0Var == this) {
            m = null;
            yo0 yo0Var = this.i;
            if (yo0Var != null) {
                View view2 = yo0Var.b;
                if (view2.getParent() != null) {
                    ((WindowManager) yo0Var.a.getSystemService("window")).removeView(view2);
                }
                this.i = null;
                this.k = true;
                view.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (l == this) {
            b(null);
        }
        view.removeCallbacks(this.f);
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
        WeakHashMap weakHashMap = xr0.a;
        View view = this.b;
        if (view.isAttachedToWindow()) {
            b(null);
            wo0 wo0Var = m;
            if (wo0Var != null) {
                wo0Var.a();
            }
            m = this;
            this.j = z;
            yo0 yo0Var = new yo0(view.getContext());
            this.i = yo0Var;
            int width = this.g;
            int i6 = this.h;
            boolean z2 = this.j;
            View view2 = yo0Var.b;
            ViewParent parent = view2.getParent();
            Context context = yo0Var.a;
            if (parent != null && view2.getParent() != null) {
                ((WindowManager) context.getSystemService("window")).removeView(view2);
            }
            yo0Var.c.setText(this.c);
            WindowManager.LayoutParams layoutParams = yo0Var.d;
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
                Rect rect = yo0Var.e;
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
                int[] iArr = yo0Var.g;
                rootView.getLocationOnScreen(iArr);
                int[] iArr2 = yo0Var.f;
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
            if (this.j) {
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
            vo0 vo0Var = this.f;
            view.removeCallbacks(vo0Var);
            view.postDelayed(vo0Var, j2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0066  */
    @Override // android.view.View.OnHoverListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onHover(android.view.View r4, android.view.MotionEvent r5) {
        /*
            r3 = this;
            yo0 r4 = r3.i
            r0 = 0
            if (r4 == 0) goto La
            boolean r4 = r3.j
            if (r4 == 0) goto La
            goto L6f
        La:
            android.view.View r4 = r3.b
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
            r3.k = r4
            r3.a()
            return r0
        L38:
            boolean r4 = r4.isEnabled()
            if (r4 == 0) goto L6f
            yo0 r4 = r3.i
            if (r4 != 0) goto L6f
            float r4 = r5.getX()
            int r4 = (int) r4
            float r5 = r5.getY()
            int r5 = (int) r5
            boolean r1 = r3.k
            if (r1 != 0) goto L66
            int r1 = r3.g
            int r1 = r4 - r1
            int r1 = java.lang.Math.abs(r1)
            int r2 = r3.d
            if (r1 > r2) goto L66
            int r1 = r3.h
            int r1 = r5 - r1
            int r1 = java.lang.Math.abs(r1)
            if (r1 <= r2) goto L6f
        L66:
            r3.g = r4
            r3.h = r5
            r3.k = r0
            b(r3)
        L6f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.wo0.onHover(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        this.g = view.getWidth() / 2;
        this.h = view.getHeight() / 2;
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
