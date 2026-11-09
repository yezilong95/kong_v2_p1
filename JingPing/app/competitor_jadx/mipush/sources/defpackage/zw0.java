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
public final class zw0 extends ie0 implements PopupWindow.OnDismissListener, View.OnKeyListener {
    public static final int Y = R$layout.abc_popup_menu_item_layout;
    public final Context F;
    public final zd0 G;
    public final wd0 H;
    public final boolean I;
    public final int J;
    public final int K;
    public final d L;
    public PopupWindow.OnDismissListener O;
    public View P;
    public View Q;
    public pe0 R;
    public ViewTreeObserver S;
    public boolean T;
    public boolean U;
    public int V;
    public boolean X;
    public final q3 M = new q3(5, this);
    public final rc N = new rc(4, this);
    public int W = 0;

    public zw0(int i, zd0 zd0Var, Context context, View view, boolean z) {
        this.F = context;
        this.G = zd0Var;
        this.I = z;
        this.H = new wd0(zd0Var, LayoutInflater.from(context), z, Y);
        this.K = i;
        Resources resources = context.getResources();
        this.J = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.P = view;
        this.L = new d(context, null, i, 0);
        zd0Var.b(this, context);
    }

    @Override // defpackage.qe0
    public final void a(zd0 zd0Var, boolean z) {
        if (zd0Var != this.G) {
            return;
        }
        dismiss();
        pe0 pe0Var = this.R;
        if (pe0Var != null) {
            pe0Var.a(zd0Var, z);
        }
    }

    @Override // defpackage.qv0
    public final boolean b() {
        return !this.T && this.L.d0.isShowing();
    }

    @Override // defpackage.qv0
    public final void d() {
        View view;
        if (b()) {
            return;
        }
        if (this.T || (view = this.P) == null) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
        this.Q = view;
        d dVar = this.L;
        dVar.setOnDismissListener(this);
        dVar.setOnItemClickListener(this);
        dVar.c0 = true;
        dVar.d0.setFocusable(true);
        View view2 = this.Q;
        boolean z = this.S == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.S = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.M);
        }
        view2.addOnAttachStateChangeListener(this.N);
        dVar.S = view2;
        dVar.P = this.W;
        boolean z2 = this.U;
        Context context = this.F;
        wd0 wd0Var = this.H;
        if (!z2) {
            this.V = ie0.o(wd0Var, context, this.J);
            this.U = true;
        }
        dVar.r(this.V);
        dVar.d0.setInputMethodMode(2);
        Rect rect = this.E;
        dVar.b0 = rect != null ? new Rect(rect) : null;
        dVar.d();
        gq gqVar = dVar.G;
        gqVar.setOnKeyListener(this);
        if (this.X) {
            zd0 zd0Var = this.G;
            if (zd0Var.m != null) {
                FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(context).inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) gqVar, false);
                TextView textView = (TextView) frameLayout.findViewById(R.id.title);
                if (textView != null) {
                    textView.setText(zd0Var.m);
                }
                frameLayout.setEnabled(false);
                gqVar.addHeaderView(frameLayout, null, false);
            }
        }
        dVar.q(wd0Var);
        dVar.d();
    }

    @Override // defpackage.qv0
    public final void dismiss() {
        if (b()) {
            this.L.dismiss();
        }
    }

    @Override // defpackage.qe0
    public final void f(pe0 pe0Var) {
        this.R = pe0Var;
    }

    @Override // defpackage.qv0
    public final gq h() {
        return this.L.G;
    }

    @Override // defpackage.qe0
    public final void j(boolean z) {
        this.U = false;
        wd0 wd0Var = this.H;
        if (wd0Var != null) {
            wd0Var.notifyDataSetChanged();
        }
    }

    @Override // defpackage.qe0
    public final boolean k(rx0 rx0Var) {
        if (rx0Var.hasVisibleItems()) {
            View view = this.Q;
            le0 le0Var = new le0(this.K, rx0Var, this.F, view, this.I);
            pe0 pe0Var = this.R;
            le0Var.h = pe0Var;
            ie0 ie0Var = le0Var.i;
            if (ie0Var != null) {
                ie0Var.f(pe0Var);
            }
            boolean zV = ie0.v(rx0Var);
            le0Var.g = zV;
            ie0 ie0Var2 = le0Var.i;
            if (ie0Var2 != null) {
                ie0Var2.q(zV);
            }
            le0Var.setOnDismissListener(this.O);
            this.O = null;
            this.G.c(false);
            d dVar = this.L;
            int width = dVar.J;
            int iO = dVar.o();
            int i = this.W;
            View view2 = this.P;
            WeakHashMap weakHashMap = f51.a;
            if ((Gravity.getAbsoluteGravity(i, view2.getLayoutDirection()) & 7) == 5) {
                width += this.P.getWidth();
            }
            if (!le0Var.b()) {
                if (le0Var.e != null) {
                    le0Var.d(width, iO, true, true);
                }
            }
            pe0 pe0Var2 = this.R;
            if (pe0Var2 != null) {
                pe0Var2.n(rx0Var);
            }
            return true;
        }
        return false;
    }

    @Override // defpackage.qe0
    public final boolean l() {
        return false;
    }

    @Override // defpackage.qe0
    public final Parcelable m() {
        return null;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.T = true;
        this.G.c(true);
        ViewTreeObserver viewTreeObserver = this.S;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.S = this.Q.getViewTreeObserver();
            }
            this.S.removeGlobalOnLayoutListener(this.M);
            this.S = null;
        }
        this.Q.removeOnAttachStateChangeListener(this.N);
        PopupWindow.OnDismissListener onDismissListener = this.O;
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

    @Override // defpackage.ie0
    public final void p(View view) {
        this.P = view;
    }

    @Override // defpackage.ie0
    public final void q(boolean z) {
        this.H.G = z;
    }

    @Override // defpackage.ie0
    public final void r(int i) {
        this.W = i;
    }

    @Override // defpackage.ie0
    public final void s(int i) {
        this.L.J = i;
    }

    @Override // defpackage.ie0
    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.O = onDismissListener;
    }

    @Override // defpackage.ie0
    public final void t(boolean z) {
        this.X = z;
    }

    @Override // defpackage.ie0
    public final void u(int i) {
        this.L.k(i);
    }

    @Override // defpackage.qe0
    public final void e(Parcelable parcelable) {
    }

    @Override // defpackage.ie0
    public final void n(zd0 zd0Var) {
    }
}
