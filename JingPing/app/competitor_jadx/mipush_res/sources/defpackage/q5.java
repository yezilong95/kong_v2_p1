package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.R$color;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class q5 implements vi0, pe0 {
    public final /* synthetic */ c6 E;

    public /* synthetic */ q5(c6 c6Var) {
        this.E = c6Var;
    }

    @Override // defpackage.pe0
    public void a(zd0 zd0Var, boolean z) {
        this.E.r(zd0Var);
    }

    @Override // defpackage.vi0
    public s81 h(View view, s81 s81Var) {
        boolean z;
        boolean z2;
        int iD = s81Var.d();
        c6 c6Var = this.E;
        c6Var.getClass();
        int iD2 = s81Var.d();
        ActionBarContextView actionBarContextView = c6Var.Z;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) c6Var.Z.getLayoutParams();
            if (c6Var.Z.isShown()) {
                if (c6Var.G0 == null) {
                    c6Var.G0 = new Rect();
                    c6Var.H0 = new Rect();
                }
                Rect rect = c6Var.G0;
                Rect rect2 = c6Var.H0;
                rect.set(s81Var.b(), s81Var.d(), s81Var.c(), s81Var.a());
                ViewGroup viewGroup = c6Var.e0;
                Method method = q61.a;
                if (method != null) {
                    try {
                        method.invoke(viewGroup, rect, rect2);
                    } catch (Exception e) {
                        Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", e);
                    }
                }
                int i = rect.top;
                int i2 = rect.left;
                int i3 = rect.right;
                ViewGroup viewGroup2 = c6Var.e0;
                WeakHashMap weakHashMap = f51.a;
                s81 s81VarA = u41.a(viewGroup2);
                int iB = s81VarA == null ? 0 : s81VarA.b();
                int iC = s81VarA == null ? 0 : s81VarA.c();
                if (marginLayoutParams.topMargin == i && marginLayoutParams.leftMargin == i2 && marginLayoutParams.rightMargin == i3) {
                    z2 = false;
                } else {
                    marginLayoutParams.topMargin = i;
                    marginLayoutParams.leftMargin = i2;
                    marginLayoutParams.rightMargin = i3;
                    z2 = true;
                }
                Context context = c6Var.O;
                if (i <= 0 || c6Var.g0 != null) {
                    View view2 = c6Var.g0;
                    if (view2 != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view2.getLayoutParams();
                        int i4 = marginLayoutParams2.height;
                        int i5 = marginLayoutParams.topMargin;
                        if (i4 != i5 || marginLayoutParams2.leftMargin != iB || marginLayoutParams2.rightMargin != iC) {
                            marginLayoutParams2.height = i5;
                            marginLayoutParams2.leftMargin = iB;
                            marginLayoutParams2.rightMargin = iC;
                            c6Var.g0.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view3 = new View(context);
                    c6Var.g0 = view3;
                    view3.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = iB;
                    layoutParams.rightMargin = iC;
                    c6Var.e0.addView(c6Var.g0, -1, layoutParams);
                }
                View view4 = c6Var.g0;
                z = view4 != null;
                if (z && view4.getVisibility() != 0) {
                    View view5 = c6Var.g0;
                    view5.setBackgroundColor((view5.getWindowSystemUiVisibility() & 8192) != 0 ? qi.a(context, R$color.abc_decor_view_status_guard_light) : qi.a(context, R$color.abc_decor_view_status_guard));
                }
                if (!c6Var.l0 && z) {
                    iD2 = 0;
                }
                z = z;
                z = z2;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z = false;
            } else {
                z = false;
                z = false;
            }
            if (z) {
                c6Var.Z.setLayoutParams(marginLayoutParams);
            }
        }
        View view6 = c6Var.g0;
        if (view6 != null) {
            view6.setVisibility(z ? 0 : 8);
        }
        return f51.j(view, iD != iD2 ? s81Var.f(s81Var.b(), iD2, s81Var.c(), s81Var.a()) : s81Var);
    }

    @Override // defpackage.pe0
    public boolean n(zd0 zd0Var) {
        Window.Callback callback = this.E.P.getCallback();
        if (callback == null) {
            return true;
        }
        callback.onMenuOpened(108, zd0Var);
        return true;
    }
}
