package defpackage;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$layout;
import androidx.appcompat.widget.d;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class lk0 extends r60 implements PopupWindow.OnDismissListener, View.OnKeyListener {
    public static final int v = R$layout.abc_popup_menu_item_layout;
    public final Context c;
    public final i60 d;
    public final f60 e;
    public final boolean f;
    public final int g;
    public final int h;
    public final d i;
    public PopupWindow.OnDismissListener l;
    public View m;
    public View n;
    public y60 o;
    public ViewTreeObserver p;
    public boolean q;
    public boolean r;
    public int s;
    public boolean u;
    public final v1 j = new v1(5, this);
    public final wa k = new wa(4, this);
    public int t = 0;

    public lk0(int i, i60 i60Var, Context context, View view, boolean z) {
        this.c = context;
        this.d = i60Var;
        this.f = z;
        this.e = new f60(i60Var, LayoutInflater.from(context), z, v);
        this.h = i;
        Resources resources = context.getResources();
        this.g = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.m = view;
        this.i = new d(context, null, i, 0);
        i60Var.b(this, context);
    }

    @Override // defpackage.z60
    public final void a(i60 i60Var, boolean z) {
        if (i60Var != this.d) {
            return;
        }
        dismiss();
        y60 y60Var = this.o;
        if (y60Var != null) {
            y60Var.a(i60Var, z);
        }
    }

    @Override // defpackage.bj0
    public final boolean b() {
        return !this.q && this.i.A.isShowing();
    }

    @Override // defpackage.z60
    public final boolean c() {
        return false;
    }

    @Override // defpackage.bj0
    public final void dismiss() {
        if (b()) {
            this.i.dismiss();
        }
    }

    @Override // defpackage.z60
    public final Parcelable e() {
        return null;
    }

    @Override // defpackage.bj0
    public final void f() {
        View view;
        if (b()) {
            return;
        }
        if (this.q || (view = this.m) == null) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
        this.n = view;
        d dVar = this.i;
        dVar.setOnDismissListener(this);
        dVar.setOnItemClickListener(this);
        dVar.z = true;
        dVar.A.setFocusable(true);
        View view2 = this.n;
        boolean z = this.p == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.p = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.j);
        }
        view2.addOnAttachStateChangeListener(this.k);
        dVar.p = view2;
        dVar.m = this.t;
        boolean z2 = this.r;
        Context context = this.c;
        f60 f60Var = this.e;
        if (!z2) {
            this.s = r60.o(f60Var, context, this.g);
            this.r = true;
        }
        dVar.r(this.s);
        dVar.A.setInputMethodMode(2);
        Rect rect = this.b;
        dVar.y = rect != null ? new Rect(rect) : null;
        dVar.f();
        cm cmVar = dVar.d;
        cmVar.setOnKeyListener(this);
        if (this.u) {
            i60 i60Var = this.d;
            if (i60Var.m != null) {
                FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(context).inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) cmVar, false);
                TextView textView = (TextView) frameLayout.findViewById(R.id.title);
                if (textView != null) {
                    textView.setText(i60Var.m);
                }
                frameLayout.setEnabled(false);
                cmVar.addHeaderView(frameLayout, null, false);
            }
        }
        dVar.n(f60Var);
        dVar.f();
    }

    @Override // defpackage.z60
    public final void h(y60 y60Var) {
        this.o = y60Var;
    }

    @Override // defpackage.bj0
    public final cm j() {
        return this.i.d;
    }

    @Override // defpackage.z60
    public final void l(boolean z) {
        this.r = false;
        f60 f60Var = this.e;
        if (f60Var != null) {
            f60Var.notifyDataSetChanged();
        }
    }

    @Override // defpackage.z60
    public final boolean m(el0 el0Var) {
        if (el0Var.hasVisibleItems()) {
            View view = this.n;
            u60 u60Var = new u60(this.h, el0Var, this.c, view, this.f);
            y60 y60Var = this.o;
            u60Var.h = y60Var;
            r60 r60Var = u60Var.i;
            if (r60Var != null) {
                r60Var.h(y60Var);
            }
            boolean zV = r60.v(el0Var);
            u60Var.g = zV;
            r60 r60Var2 = u60Var.i;
            if (r60Var2 != null) {
                r60Var2.q(zV);
            }
            u60Var.setOnDismissListener(this.l);
            this.l = null;
            this.d.c(false);
            d dVar = this.i;
            int width = dVar.g;
            int iG = dVar.g();
            int i = this.t;
            View view2 = this.m;
            WeakHashMap weakHashMap = xr0.a;
            if ((Gravity.getAbsoluteGravity(i, view2.getLayoutDirection()) & 7) == 5) {
                width += this.m.getWidth();
            }
            if (!u60Var.b()) {
                if (u60Var.e != null) {
                    u60Var.d(width, iG, true, true);
                }
            }
            y60 y60Var2 = this.o;
            if (y60Var2 != null) {
                y60Var2.p(el0Var);
            }
            return true;
        }
        return false;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.q = true;
        this.d.c(true);
        ViewTreeObserver viewTreeObserver = this.p;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.p = this.n.getViewTreeObserver();
            }
            this.p.removeGlobalOnLayoutListener(this.j);
            this.p = null;
        }
        this.n.removeOnAttachStateChangeListener(this.k);
        PopupWindow.OnDismissListener onDismissListener = this.l;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // defpackage.r60
    public final void p(View view) {
        this.m = view;
    }

    @Override // defpackage.r60
    public final void q(boolean z) {
        this.e.d = z;
    }

    @Override // defpackage.r60
    public final void r(int i) {
        this.t = i;
    }

    @Override // defpackage.r60
    public final void s(int i) {
        this.i.g = i;
    }

    @Override // defpackage.r60
    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.l = onDismissListener;
    }

    @Override // defpackage.r60
    public final void t(boolean z) {
        this.u = z;
    }

    @Override // defpackage.r60
    public final void u(int i) {
        this.i.m(i);
    }

    @Override // defpackage.z60
    public final void g(Parcelable parcelable) {
    }

    @Override // defpackage.r60
    public final void n(i60 i60Var) {
    }
}
