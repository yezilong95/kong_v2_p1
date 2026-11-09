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
public final class m4 implements ha0, rg, y60 {
    public final /* synthetic */ int b;
    public final /* synthetic */ x4 c;

    public /* synthetic */ m4(x4 x4Var, int i) {
        this.b = i;
        this.c = x4Var;
    }

    @Override // defpackage.y60
    public void a(i60 i60Var, boolean z) {
        w4 w4Var;
        switch (this.b) {
            case 2:
                this.c.r(i60Var);
                break;
            default:
                i60 i60VarK = i60Var.k();
                int i = 0;
                boolean z2 = i60VarK != i60Var;
                if (z2) {
                    i60Var = i60VarK;
                }
                x4 x4Var = this.c;
                w4[] w4VarArr = x4Var.M;
                int length = w4VarArr != null ? w4VarArr.length : 0;
                while (true) {
                    if (i >= length) {
                        w4Var = null;
                    } else {
                        w4Var = w4VarArr[i];
                        if (w4Var == null || w4Var.h != i60Var) {
                            i++;
                        }
                    }
                }
                if (w4Var != null) {
                    if (!z2) {
                        x4Var.s(w4Var, z);
                    } else {
                        x4Var.q(w4Var.a, w4Var, i60VarK);
                        x4Var.s(w4Var, true);
                    }
                }
                break;
        }
    }

    @Override // defpackage.ha0
    public xu0 e(View view, xu0 xu0Var) {
        boolean z;
        boolean z2;
        int iD = xu0Var.d();
        x4 x4Var = this.c;
        x4Var.getClass();
        int iD2 = xu0Var.d();
        ActionBarContextView actionBarContextView = x4Var.w;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) x4Var.w.getLayoutParams();
            if (x4Var.w.isShown()) {
                if (x4Var.d0 == null) {
                    x4Var.d0 = new Rect();
                    x4Var.e0 = new Rect();
                }
                Rect rect = x4Var.d0;
                Rect rect2 = x4Var.e0;
                rect.set(xu0Var.b(), xu0Var.d(), xu0Var.c(), xu0Var.a());
                ViewGroup viewGroup = x4Var.B;
                Method method = kt0.a;
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
                ViewGroup viewGroup2 = x4Var.B;
                WeakHashMap weakHashMap = xr0.a;
                xu0 xu0VarA = mr0.a(viewGroup2);
                int iB = xu0VarA == null ? 0 : xu0VarA.b();
                int iC = xu0VarA == null ? 0 : xu0VarA.c();
                if (marginLayoutParams.topMargin == i && marginLayoutParams.leftMargin == i2 && marginLayoutParams.rightMargin == i3) {
                    z2 = false;
                } else {
                    marginLayoutParams.topMargin = i;
                    marginLayoutParams.leftMargin = i2;
                    marginLayoutParams.rightMargin = i3;
                    z2 = true;
                }
                Context context = x4Var.l;
                if (i <= 0 || x4Var.D != null) {
                    View view2 = x4Var.D;
                    if (view2 != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view2.getLayoutParams();
                        int i4 = marginLayoutParams2.height;
                        int i5 = marginLayoutParams.topMargin;
                        if (i4 != i5 || marginLayoutParams2.leftMargin != iB || marginLayoutParams2.rightMargin != iC) {
                            marginLayoutParams2.height = i5;
                            marginLayoutParams2.leftMargin = iB;
                            marginLayoutParams2.rightMargin = iC;
                            x4Var.D.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view3 = new View(context);
                    x4Var.D = view3;
                    view3.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = iB;
                    layoutParams.rightMargin = iC;
                    x4Var.B.addView(x4Var.D, -1, layoutParams);
                }
                View view4 = x4Var.D;
                z = view4 != null;
                if (z && view4.getVisibility() != 0) {
                    View view5 = x4Var.D;
                    view5.setBackgroundColor((view5.getWindowSystemUiVisibility() & 8192) != 0 ? bh.a(context, R$color.abc_decor_view_status_guard_light) : bh.a(context, R$color.abc_decor_view_status_guard));
                }
                if (!x4Var.I && z) {
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
                x4Var.w.setLayoutParams(marginLayoutParams);
            }
        }
        View view6 = x4Var.D;
        if (view6 != null) {
            view6.setVisibility(z ? 0 : 8);
        }
        return xr0.j(view, iD != iD2 ? xu0Var.f(xu0Var.b(), iD2, xu0Var.c(), xu0Var.a()) : xu0Var);
    }

    @Override // defpackage.y60
    public boolean p(i60 i60Var) {
        Window.Callback callback;
        switch (this.b) {
            case 2:
                Window.Callback callback2 = this.c.m.getCallback();
                if (callback2 != null) {
                    callback2.onMenuOpened(108, i60Var);
                }
                break;
            default:
                if (i60Var == i60Var.k()) {
                    x4 x4Var = this.c;
                    if (x4Var.G && (callback = x4Var.m.getCallback()) != null && !x4Var.R) {
                        callback.onMenuOpened(108, i60Var);
                        break;
                    }
                }
                break;
        }
        return true;
    }
}
