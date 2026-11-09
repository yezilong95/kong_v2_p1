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
public final class za extends r60 implements View.OnKeyListener, PopupWindow.OnDismissListener {
    public static final int B = R$layout.abc_cascading_menu_item_layout;
    public boolean A;
    public final Context c;
    public final int d;
    public final int e;
    public final boolean f;
    public final Handler g;
    public View o;
    public View p;
    public int q;
    public boolean r;
    public boolean s;
    public int t;
    public int u;
    public boolean w;
    public y60 x;
    public ViewTreeObserver y;
    public PopupWindow.OnDismissListener z;
    public final ArrayList h = new ArrayList();
    public final ArrayList i = new ArrayList();
    public final v1 j = new v1(3, this);
    public final wa k = new wa(0, this);
    public final o70 l = new o70(9, this);
    public int m = 0;
    public int n = 0;
    public boolean v = false;

    public za(Context context, View view, int i, boolean z) {
        this.c = context;
        this.o = view;
        this.e = i;
        this.f = z;
        WeakHashMap weakHashMap = xr0.a;
        this.q = view.getLayoutDirection() != 1 ? 1 : 0;
        Resources resources = context.getResources();
        this.d = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.g = new Handler();
    }

    @Override // defpackage.z60
    public final void a(i60 i60Var, boolean z) {
        ArrayList arrayList = this.i;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (i60Var == ((ya) arrayList.get(i)).b) {
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
            ((ya) arrayList.get(i2)).b.c(false);
        }
        ya yaVar = (ya) arrayList.remove(i);
        yaVar.b.r(this);
        boolean z2 = this.A;
        d dVar = yaVar.a;
        if (z2) {
            v60.b(dVar.A, null);
            dVar.A.setAnimationStyle(0);
        }
        dVar.dismiss();
        int size2 = arrayList.size();
        if (size2 > 0) {
            this.q = ((ya) arrayList.get(size2 - 1)).c;
        } else {
            View view = this.o;
            WeakHashMap weakHashMap = xr0.a;
            this.q = view.getLayoutDirection() == 1 ? 0 : 1;
        }
        if (size2 != 0) {
            if (z) {
                ((ya) arrayList.get(0)).b.c(false);
                return;
            }
            return;
        }
        dismiss();
        y60 y60Var = this.x;
        if (y60Var != null) {
            y60Var.a(i60Var, true);
        }
        ViewTreeObserver viewTreeObserver = this.y;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.y.removeGlobalOnLayoutListener(this.j);
            }
            this.y = null;
        }
        this.p.removeOnAttachStateChangeListener(this.k);
        this.z.onDismiss();
    }

    @Override // defpackage.bj0
    public final boolean b() {
        ArrayList arrayList = this.i;
        return arrayList.size() > 0 && ((ya) arrayList.get(0)).a.A.isShowing();
    }

    @Override // defpackage.z60
    public final boolean c() {
        return false;
    }

    @Override // defpackage.bj0
    public final void dismiss() {
        ArrayList arrayList = this.i;
        int size = arrayList.size();
        if (size > 0) {
            ya[] yaVarArr = (ya[]) arrayList.toArray(new ya[size]);
            for (int i = size - 1; i >= 0; i--) {
                ya yaVar = yaVarArr[i];
                if (yaVar.a.A.isShowing()) {
                    yaVar.a.dismiss();
                }
            }
        }
    }

    @Override // defpackage.z60
    public final Parcelable e() {
        return null;
    }

    @Override // defpackage.bj0
    public final void f() {
        if (b()) {
            return;
        }
        ArrayList arrayList = this.h;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            w((i60) it.next());
        }
        arrayList.clear();
        View view = this.o;
        this.p = view;
        if (view != null) {
            boolean z = this.y == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.y = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.j);
            }
            this.p.addOnAttachStateChangeListener(this.k);
        }
    }

    @Override // defpackage.z60
    public final void h(y60 y60Var) {
        this.x = y60Var;
    }

    @Override // defpackage.bj0
    public final cm j() {
        ArrayList arrayList = this.i;
        if (arrayList.isEmpty()) {
            return null;
        }
        return ((ya) arrayList.get(arrayList.size() - 1)).a.d;
    }

    @Override // defpackage.z60
    public final void l(boolean z) {
        Iterator it = this.i.iterator();
        while (it.hasNext()) {
            ListAdapter adapter = ((ya) it.next()).a.d.getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((f60) adapter).notifyDataSetChanged();
        }
    }

    @Override // defpackage.z60
    public final boolean m(el0 el0Var) {
        for (ya yaVar : this.i) {
            if (el0Var == yaVar.b) {
                yaVar.a.d.requestFocus();
                return true;
            }
        }
        if (!el0Var.hasVisibleItems()) {
            return false;
        }
        n(el0Var);
        y60 y60Var = this.x;
        if (y60Var != null) {
            y60Var.p(el0Var);
        }
        return true;
    }

    @Override // defpackage.r60
    public final void n(i60 i60Var) {
        i60Var.b(this, this.c);
        if (b()) {
            w(i60Var);
        } else {
            this.h.add(i60Var);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        ya yaVar;
        ArrayList arrayList = this.i;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                yaVar = null;
                break;
            }
            yaVar = (ya) arrayList.get(i);
            if (!yaVar.a.A.isShowing()) {
                break;
            } else {
                i++;
            }
        }
        if (yaVar != null) {
            yaVar.b.c(false);
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
        if (this.o != view) {
            this.o = view;
            int i = this.m;
            WeakHashMap weakHashMap = xr0.a;
            this.n = Gravity.getAbsoluteGravity(i, view.getLayoutDirection());
        }
    }

    @Override // defpackage.r60
    public final void q(boolean z) {
        this.v = z;
    }

    @Override // defpackage.r60
    public final void r(int i) {
        if (this.m != i) {
            this.m = i;
            View view = this.o;
            WeakHashMap weakHashMap = xr0.a;
            this.n = Gravity.getAbsoluteGravity(i, view.getLayoutDirection());
        }
    }

    @Override // defpackage.r60
    public final void s(int i) {
        this.r = true;
        this.t = i;
    }

    @Override // defpackage.r60
    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.z = onDismissListener;
    }

    @Override // defpackage.r60
    public final void t(boolean z) {
        this.w = z;
    }

    @Override // defpackage.r60
    public final void u(int i) {
        this.s = true;
        this.u = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void w(defpackage.i60 r18) {
        /*
            Method dump skipped, instruction units count: 531
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.za.w(i60):void");
    }

    @Override // defpackage.z60
    public final void g(Parcelable parcelable) {
    }
}
