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
public class le0 {
    public final Context a;
    public final zd0 b;
    public final boolean c;
    public final int d;
    public View e;
    public boolean g;
    public pe0 h;
    public ie0 i;
    public PopupWindow.OnDismissListener j;
    public int f = 8388611;
    public final je0 k = new je0(this);

    public le0(int i, zd0 zd0Var, Context context, View view, boolean z) {
        this.a = context;
        this.b = zd0Var;
        this.e = view;
        this.c = z;
        this.d = i;
    }

    public final ie0 a() {
        ie0 zw0Var;
        if (this.i == null) {
            Context context = this.a;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            ke0.a(defaultDisplay, point);
            if (Math.min(point.x, point.y) >= context.getResources().getDimensionPixelSize(R$dimen.abc_cascading_menus_min_smallest_width)) {
                zw0Var = new uc(context, this.e, this.d, this.c);
            } else {
                View view = this.e;
                Context context2 = this.a;
                boolean z = this.c;
                zw0Var = new zw0(this.d, this.b, context2, view, z);
            }
            zw0Var.n(this.b);
            zw0Var.setOnDismissListener(this.k);
            zw0Var.p(this.e);
            zw0Var.f(this.h);
            zw0Var.q(this.g);
            zw0Var.r(this.f);
            this.i = zw0Var;
        }
        return this.i;
    }

    public final boolean b() {
        ie0 ie0Var = this.i;
        return ie0Var != null && ie0Var.b();
    }

    public void c() {
        this.i = null;
        PopupWindow.OnDismissListener onDismissListener = this.j;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public final void d(int i, int i2, boolean z, boolean z2) {
        ie0 ie0VarA = a();
        ie0VarA.t(z2);
        if (z) {
            int i3 = this.f;
            View view = this.e;
            WeakHashMap weakHashMap = f51.a;
            if ((Gravity.getAbsoluteGravity(i3, view.getLayoutDirection()) & 7) == 5) {
                i -= this.e.getWidth();
            }
            ie0VarA.s(i);
            ie0VarA.u(i2);
            int i4 = (int) ((this.a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            ie0VarA.E = new Rect(i - i4, i2 - i4, i + i4, i2 + i4);
        }
        ie0VarA.d();
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.j = onDismissListener;
    }
}
