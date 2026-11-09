package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$layout;
import androidx.appcompat.widget.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class uc extends ie0 implements View.OnKeyListener, PopupWindow.OnDismissListener {
    public static final int e0 = R$layout.abc_cascading_menu_item_layout;
    public final Context F;
    public final int G;
    public final int H;
    public final boolean I;
    public final Handler J;
    public View R;
    public View S;
    public int T;
    public boolean U;
    public boolean V;
    public int W;
    public int X;
    public boolean Z;
    public pe0 a0;
    public ViewTreeObserver b0;
    public PopupWindow.OnDismissListener c0;
    public boolean d0;
    public final ArrayList K = new ArrayList();
    public final ArrayList L = new ArrayList();
    public final q3 M = new q3(3, this);
    public final rc N = new rc(0, this);
    public final p50 O = new p50(10, this);
    public int P = 0;
    public int Q = 0;
    public boolean Y = false;

    public uc(Context context, View view, int i, boolean z) {
        this.F = context;
        this.R = view;
        this.H = i;
        this.I = z;
        WeakHashMap weakHashMap = f51.a;
        this.T = view.getLayoutDirection() != 1 ? 1 : 0;
        Resources resources = context.getResources();
        this.G = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.J = new Handler();
    }

    @Override // defpackage.qe0
    public final void a(zd0 zd0Var, boolean z) {
        ArrayList arrayList = this.L;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (zd0Var == ((tc) arrayList.get(i)).b) {
                break;
            } else {
                i++;
            }
        }
        if (i < 0) {
            return;
        }
        int i2 = i + 1;
        if (i2 < arrayList.size()) {
            ((tc) arrayList.get(i2)).b.c(false);
        }
        tc tcVar = (tc) arrayList.remove(i);
        tcVar.b.r(this);
        boolean z2 = this.d0;
        d dVar = tcVar.a;
        if (z2) {
            me0.b(dVar.d0, null);
            dVar.d0.setAnimationStyle(0);
        }
        dVar.dismiss();
        int size2 = arrayList.size();
        if (size2 > 0) {
            this.T = ((tc) arrayList.get(size2 - 1)).c;
        } else {
            View view = this.R;
            WeakHashMap weakHashMap = f51.a;
            this.T = view.getLayoutDirection() == 1 ? 0 : 1;
        }
        if (size2 != 0) {
            if (z) {
                ((tc) arrayList.get(0)).b.c(false);
                return;
            }
            return;
        }
        dismiss();
        pe0 pe0Var = this.a0;
        if (pe0Var != null) {
            pe0Var.a(zd0Var, true);
        }
        ViewTreeObserver viewTreeObserver = this.b0;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.b0.removeGlobalOnLayoutListener(this.M);
            }
            this.b0 = null;
        }
        this.S.removeOnAttachStateChangeListener(this.N);
        this.c0.onDismiss();
    }

    @Override // defpackage.qv0
    public final boolean b() {
        ArrayList arrayList = this.L;
        return arrayList.size() > 0 && ((tc) arrayList.get(0)).a.d0.isShowing();
    }

    @Override // defpackage.qv0
    public final void d() {
        if (b()) {
            return;
        }
        ArrayList arrayList = this.K;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            w((zd0) it.next());
        }
        arrayList.clear();
        View view = this.R;
        this.S = view;
        if (view != null) {
            boolean z = this.b0 == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.b0 = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.M);
            }
            this.S.addOnAttachStateChangeListener(this.N);
        }
    }

    @Override // defpackage.qv0
    public final void dismiss() {
        ArrayList arrayList = this.L;
        int size = arrayList.size();
        if (size > 0) {
            tc[] tcVarArr = (tc[]) arrayList.toArray(new tc[size]);
            for (int i = size - 1; i >= 0; i--) {
                tc tcVar = tcVarArr[i];
                if (tcVar.a.d0.isShowing()) {
                    tcVar.a.dismiss();
                }
            }
        }
    }

    @Override // defpackage.qe0
    public final void f(pe0 pe0Var) {
        this.a0 = pe0Var;
    }

    @Override // defpackage.qv0
    public final gq h() {
        ArrayList arrayList = this.L;
        if (arrayList.isEmpty()) {
            return null;
        }
        return ((tc) arrayList.get(arrayList.size() - 1)).a.G;
    }

    @Override // defpackage.qe0
    public final void j(boolean z) {
        Iterator it = this.L.iterator();
        while (it.hasNext()) {
            ListAdapter adapter = ((tc) it.next()).a.G.getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((wd0) adapter).notifyDataSetChanged();
        }
    }

    @Override // defpackage.qe0
    public final boolean k(rx0 rx0Var) {
        for (tc tcVar : this.L) {
            if (rx0Var == tcVar.b) {
                tcVar.a.G.requestFocus();
                return true;
            }
        }
        if (!rx0Var.hasVisibleItems()) {
            return false;
        }
        n(rx0Var);
        pe0 pe0Var = this.a0;
        if (pe0Var != null) {
            pe0Var.n(rx0Var);
        }
        return true;
    }

    @Override // defpackage.qe0
    public final boolean l() {
        return false;
    }

    @Override // defpackage.qe0
    public final Parcelable m() {
        return null;
    }

    @Override // defpackage.ie0
    public final void n(zd0 zd0Var) {
        zd0Var.b(this, this.F);
        if (b()) {
            w(zd0Var);
        } else {
            this.K.add(zd0Var);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        tc tcVar;
        ArrayList arrayList = this.L;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                tcVar = null;
                break;
            }
            tcVar = (tc) arrayList.get(i);
            if (!tcVar.a.d0.isShowing()) {
                break;
            } else {
                i++;
            }
        }
        if (tcVar != null) {
            tcVar.b.c(false);
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
        if (this.R != view) {
            this.R = view;
            int i = this.P;
            WeakHashMap weakHashMap = f51.a;
            this.Q = Gravity.getAbsoluteGravity(i, view.getLayoutDirection());
        }
    }

    @Override // defpackage.ie0
    public final void q(boolean z) {
        this.Y = z;
    }

    @Override // defpackage.ie0
    public final void r(int i) {
        if (this.P != i) {
            this.P = i;
            View view = this.R;
            WeakHashMap weakHashMap = f51.a;
            this.Q = Gravity.getAbsoluteGravity(i, view.getLayoutDirection());
        }
    }

    @Override // defpackage.ie0
    public final void s(int i) {
        this.U = true;
        this.W = i;
    }

    @Override // defpackage.ie0
    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.c0 = onDismissListener;
    }

    @Override // defpackage.ie0
    public final void t(boolean z) {
        this.Z = z;
    }

    @Override // defpackage.ie0
    public final void u(int i) {
        this.V = true;
        this.X = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void w(defpackage.zd0 r18) {
        /*
            Method dump skipped, instruction units count: 531
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uc.w(zd0):void");
    }

    @Override // defpackage.qe0
    public final void e(Parcelable parcelable) {
    }
}
