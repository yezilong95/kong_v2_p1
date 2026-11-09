package defpackage;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.R$dimen;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class u60 {
    public final Context a;
    public final i60 b;
    public final boolean c;
    public final int d;
    public View e;
    public boolean g;
    public y60 h;
    public r60 i;
    public PopupWindow.OnDismissListener j;
    public int f = 8388611;
    public final s60 k = new s60(this);

    public u60(int i, i60 i60Var, Context context, View view, boolean z) {
        this.a = context;
        this.b = i60Var;
        this.e = view;
        this.c = z;
        this.d = i;
    }

    public final r60 a() {
        r60 lk0Var;
        if (this.i == null) {
            Context context = this.a;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            t60.a(defaultDisplay, point);
            if (Math.min(point.x, point.y) >= context.getResources().getDimensionPixelSize(R$dimen.abc_cascading_menus_min_smallest_width)) {
                lk0Var = new za(context, this.e, this.d, this.c);
            } else {
                View view = this.e;
                Context context2 = this.a;
                boolean z = this.c;
                lk0Var = new lk0(this.d, this.b, context2, view, z);
            }
            lk0Var.n(this.b);
            lk0Var.setOnDismissListener(this.k);
            lk0Var.p(this.e);
            lk0Var.h(this.h);
            lk0Var.q(this.g);
            lk0Var.r(this.f);
            this.i = lk0Var;
        }
        return this.i;
    }

    public final boolean b() {
        r60 r60Var = this.i;
        return r60Var != null && r60Var.b();
    }

    public void c() {
        this.i = null;
        PopupWindow.OnDismissListener onDismissListener = this.j;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public final void d(int i, int i2, boolean z, boolean z2) {
        r60 r60VarA = a();
        r60VarA.t(z2);
        if (z) {
            int i3 = this.f;
            View view = this.e;
            WeakHashMap weakHashMap = xr0.a;
            if ((Gravity.getAbsoluteGravity(i3, view.getLayoutDirection()) & 7) == 5) {
                i -= this.e.getWidth();
            }
            r60VarA.s(i);
            r60VarA.u(i2);
            int i4 = (int) ((this.a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            r60VarA.b = new Rect(i - i4, i2 - i4, i + i4, i2 + i4);
        }
        r60VarA.f();
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.j = onDismissListener;
    }
}
