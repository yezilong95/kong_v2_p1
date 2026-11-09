package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import defpackage.de0;
import defpackage.f3;
import defpackage.g3;
import defpackage.h3;
import defpackage.i3;
import defpackage.ie0;
import defpackage.m3;
import defpackage.p50;
import defpackage.pe0;
import defpackage.re0;
import defpackage.rx0;
import defpackage.t9;
import defpackage.z8;
import defpackage.zd0;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class b extends t9 {
    public i3 N;
    public Drawable O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public int S;
    public int T;
    public int U;
    public boolean V;
    public final SparseBooleanArray W;
    public f3 X;
    public f3 Y;
    public h3 Z;
    public g3 a0;
    public final z8 b0;
    public int c0;

    public b(Context context) {
        int i = R$layout.abc_action_menu_layout;
        int i2 = R$layout.abc_action_menu_item_layout;
        this.E = context;
        this.H = LayoutInflater.from(context);
        this.J = i;
        this.K = i2;
        this.W = new SparseBooleanArray();
        this.b0 = new z8(4, this);
    }

    @Override // defpackage.qe0
    public final void a(zd0 zd0Var, boolean z) {
        d();
        f3 f3Var = this.Y;
        if (f3Var != null && f3Var.b()) {
            f3Var.i.dismiss();
        }
        pe0 pe0Var = this.I;
        if (pe0Var != null) {
            pe0Var.a(zd0Var, z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View b(de0 de0Var, View view, ViewGroup viewGroup) {
        View actionView = de0Var.getActionView();
        if (actionView == null || de0Var.e()) {
            re0 re0Var = view instanceof re0 ? (re0) view : (re0) this.H.inflate(this.K, viewGroup, false);
            re0Var.d(de0Var);
            ActionMenuItemView actionMenuItemView = (ActionMenuItemView) re0Var;
            actionMenuItemView.setItemInvoker((ActionMenuView) this.L);
            if (this.a0 == null) {
                this.a0 = new g3(this);
            }
            actionMenuItemView.setPopupCallback(this.a0);
            actionView = (View) re0Var;
        }
        actionView.setVisibility(de0Var.C ? 8 : 0);
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        ((ActionMenuView) viewGroup).getClass();
        if (!(layoutParams instanceof ActionMenuView.LayoutParams)) {
            actionView.setLayoutParams(ActionMenuView.m(layoutParams));
        }
        return actionView;
    }

    public final boolean d() {
        Object obj;
        h3 h3Var = this.Z;
        if (h3Var != null && (obj = this.L) != null) {
            ((View) obj).removeCallbacks(h3Var);
            this.Z = null;
            return true;
        }
        f3 f3Var = this.X;
        if (f3Var == null) {
            return false;
        }
        if (f3Var.b()) {
            f3Var.i.dismiss();
        }
        return true;
    }

    @Override // defpackage.qe0
    public final void e(Parcelable parcelable) {
        int i;
        MenuItem menuItemFindItem;
        if ((parcelable instanceof ActionMenuPresenter$SavedState) && (i = ((ActionMenuPresenter$SavedState) parcelable).E) > 0 && (menuItemFindItem = this.G.findItem(i)) != null) {
            k((rx0) menuItemFindItem.getSubMenu());
        }
    }

    public final boolean h() {
        f3 f3Var = this.X;
        return f3Var != null && f3Var.b();
    }

    @Override // defpackage.qe0
    public final void i(Context context, zd0 zd0Var) {
        this.F = context;
        LayoutInflater.from(context);
        this.G = zd0Var;
        Resources resources = context.getResources();
        p50 p50VarT = p50.t(context);
        if (!this.R) {
            this.Q = true;
        }
        this.S = ((Context) p50VarT.F).getResources().getDisplayMetrics().widthPixels / 2;
        this.U = p50VarT.z();
        int measuredWidth = this.S;
        if (this.Q) {
            if (this.N == null) {
                i3 i3Var = new i3(this, this.E);
                this.N = i3Var;
                if (this.P) {
                    i3Var.setImageDrawable(this.O);
                    this.O = null;
                    this.P = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.N.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.N.getMeasuredWidth();
        } else {
            this.N = null;
        }
        this.T = measuredWidth;
        float f = resources.getDisplayMetrics().density;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.qe0
    public final void j(boolean z) {
        int i;
        ViewGroup viewGroup = (ViewGroup) this.L;
        ArrayList arrayList = null;
        boolean z2 = false;
        if (viewGroup != null) {
            zd0 zd0Var = this.G;
            if (zd0Var != null) {
                zd0Var.i();
                ArrayList arrayListL = this.G.l();
                int size = arrayListL.size();
                i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    de0 de0Var = (de0) arrayListL.get(i2);
                    if ((de0Var.x & 32) == 32) {
                        View childAt = viewGroup.getChildAt(i);
                        de0 itemData = childAt instanceof re0 ? ((re0) childAt).getItemData() : null;
                        View viewB = b(de0Var, childAt, viewGroup);
                        if (de0Var != itemData) {
                            viewB.setPressed(false);
                            viewB.jumpDrawablesToCurrentState();
                        }
                        if (viewB != childAt) {
                            ViewGroup viewGroup2 = (ViewGroup) viewB.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(viewB);
                            }
                            ((ViewGroup) this.L).addView(viewB, i);
                        }
                        i++;
                    }
                }
            } else {
                i = 0;
            }
            while (i < viewGroup.getChildCount()) {
                if (viewGroup.getChildAt(i) == this.N) {
                    i++;
                } else {
                    viewGroup.removeViewAt(i);
                }
            }
        }
        ((View) this.L).requestLayout();
        zd0 zd0Var2 = this.G;
        if (zd0Var2 != null) {
            zd0Var2.i();
            ArrayList arrayList2 = zd0Var2.i;
            int size2 = arrayList2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                m3 m3Var = ((de0) arrayList2.get(i3)).A;
                if (m3Var != null) {
                    m3Var.a = this;
                }
            }
        }
        zd0 zd0Var3 = this.G;
        if (zd0Var3 != null) {
            zd0Var3.i();
            arrayList = zd0Var3.j;
        }
        if (this.Q && arrayList != null) {
            int size3 = arrayList.size();
            if (size3 == 1) {
                z2 = !((de0) arrayList.get(0)).C;
            } else if (size3 > 0) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.N == null) {
                this.N = new i3(this, this.E);
            }
            ViewGroup viewGroup3 = (ViewGroup) this.N.getParent();
            if (viewGroup3 != this.L) {
                if (viewGroup3 != null) {
                    viewGroup3.removeView(this.N);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.L;
                i3 i3Var = this.N;
                actionMenuView.getClass();
                ActionMenuView.LayoutParams layoutParamsL = ActionMenuView.l();
                layoutParamsL.a = true;
                actionMenuView.addView(i3Var, layoutParamsL);
            }
        } else {
            i3 i3Var2 = this.N;
            if (i3Var2 != null) {
                Object parent = i3Var2.getParent();
                Object obj = this.L;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.N);
                }
            }
        }
        ((ActionMenuView) this.L).setOverflowReserved(this.Q);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.qe0
    public final boolean k(rx0 rx0Var) {
        boolean z;
        if (rx0Var.hasVisibleItems()) {
            rx0 rx0Var2 = rx0Var;
            while (true) {
                zd0 zd0Var = rx0Var2.z;
                if (zd0Var == this.G) {
                    break;
                }
                rx0Var2 = (rx0) zd0Var;
            }
            ViewGroup viewGroup = (ViewGroup) this.L;
            View view = null;
            view = null;
            if (viewGroup != null) {
                int childCount = viewGroup.getChildCount();
                int i = 0;
                while (true) {
                    if (i >= childCount) {
                        break;
                    }
                    View childAt = viewGroup.getChildAt(i);
                    if ((childAt instanceof re0) && ((re0) childAt).getItemData() == rx0Var2.A) {
                        view = childAt;
                        break;
                    }
                    i++;
                }
            }
            if (view != null) {
                this.c0 = rx0Var.A.a;
                int size = rx0Var.f.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        z = false;
                        break;
                    }
                    MenuItem item = rx0Var.getItem(i2);
                    if (item.isVisible() && item.getIcon() != null) {
                        z = true;
                        break;
                    }
                    i2++;
                }
                f3 f3Var = new f3(this, this.F, rx0Var, view);
                this.Y = f3Var;
                f3Var.g = z;
                ie0 ie0Var = f3Var.i;
                if (ie0Var != null) {
                    ie0Var.q(z);
                }
                f3 f3Var2 = this.Y;
                if (!f3Var2.b()) {
                    if (f3Var2.e == null) {
                        throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
                    }
                    f3Var2.d(0, 0, false, false);
                }
                pe0 pe0Var = this.I;
                if (pe0Var != null) {
                    pe0Var.n(rx0Var);
                }
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.qe0
    public final boolean l() {
        int size;
        ArrayList arrayListL;
        int i;
        boolean z;
        b bVar = this;
        zd0 zd0Var = bVar.G;
        if (zd0Var != null) {
            arrayListL = zd0Var.l();
            size = arrayListL.size();
        } else {
            size = 0;
            arrayListL = null;
        }
        int i2 = bVar.U;
        int i3 = bVar.T;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) bVar.L;
        int i4 = 0;
        boolean z2 = false;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            i = 2;
            z = true;
            if (i4 >= size) {
                break;
            }
            de0 de0Var = (de0) arrayListL.get(i4);
            int i7 = de0Var.y;
            if ((i7 & 2) == 2) {
                i5++;
            } else if ((i7 & 1) == 1) {
                i6++;
            } else {
                z2 = true;
            }
            if (bVar.V && de0Var.C) {
                i2 = 0;
            }
            i4++;
        }
        if (bVar.Q && (z2 || i6 + i5 > i2)) {
            i2--;
        }
        int i8 = i2 - i5;
        SparseBooleanArray sparseBooleanArray = bVar.W;
        sparseBooleanArray.clear();
        int i9 = 0;
        int i10 = 0;
        while (i9 < size) {
            de0 de0Var2 = (de0) arrayListL.get(i9);
            int i11 = de0Var2.y;
            boolean z3 = (i11 & 2) == i ? z : false;
            int i12 = de0Var2.b;
            if (z3) {
                View viewB = bVar.b(de0Var2, null, viewGroup);
                viewB.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                int measuredWidth = viewB.getMeasuredWidth();
                i3 -= measuredWidth;
                if (i10 == 0) {
                    i10 = measuredWidth;
                }
                if (i12 != 0) {
                    sparseBooleanArray.put(i12, z);
                }
                de0Var2.g(z);
            } else if ((i11 & 1) == z) {
                boolean z4 = sparseBooleanArray.get(i12);
                boolean z5 = ((i8 > 0 || z4) && i3 > 0) ? z : false;
                if (z5) {
                    View viewB2 = bVar.b(de0Var2, null, viewGroup);
                    viewB2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    int measuredWidth2 = viewB2.getMeasuredWidth();
                    i3 -= measuredWidth2;
                    if (i10 == 0) {
                        i10 = measuredWidth2;
                    }
                    z5 &= i3 + i10 > 0;
                }
                if (z5 && i12 != 0) {
                    sparseBooleanArray.put(i12, true);
                } else if (z4) {
                    sparseBooleanArray.put(i12, false);
                    for (int i13 = 0; i13 < i9; i13++) {
                        de0 de0Var3 = (de0) arrayListL.get(i13);
                        if (de0Var3.b == i12) {
                            if ((de0Var3.x & 32) == 32) {
                                i8++;
                            }
                            de0Var3.g(false);
                        }
                    }
                }
                if (z5) {
                    i8--;
                }
                de0Var2.g(z5);
            } else {
                de0Var2.g(false);
                i9++;
                i = 2;
                bVar = this;
                z = true;
            }
            i9++;
            i = 2;
            bVar = this;
            z = true;
        }
        return z;
    }

    @Override // defpackage.qe0
    public final Parcelable m() {
        ActionMenuPresenter$SavedState actionMenuPresenter$SavedState = new ActionMenuPresenter$SavedState();
        actionMenuPresenter$SavedState.E = this.c0;
        return actionMenuPresenter$SavedState;
    }

    public final boolean n() {
        zd0 zd0Var;
        if (!this.Q || h() || (zd0Var = this.G) == null || this.L == null || this.Z != null) {
            return false;
        }
        zd0Var.i();
        if (zd0Var.j.isEmpty()) {
            return false;
        }
        h3 h3Var = new h3(this, 0, new f3(this, this.F, this.G, this.N));
        this.Z = h3Var;
        ((View) this.L).post(h3Var);
        return true;
    }
}
