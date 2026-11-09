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
import defpackage.a70;
import defpackage.el0;
import defpackage.g1;
import defpackage.i60;
import defpackage.k1;
import defpackage.l1;
import defpackage.m1;
import defpackage.m60;
import defpackage.n1;
import defpackage.o70;
import defpackage.r1;
import defpackage.r60;
import defpackage.x7;
import defpackage.y60;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class b extends x7 {
    public n1 k;
    public Drawable l;
    public boolean m;
    public boolean n;
    public boolean o;
    public int p;
    public int q;
    public int r;
    public boolean s;
    public final SparseBooleanArray t;
    public k1 u;
    public k1 v;
    public m1 w;
    public l1 x;
    public final o70 y;
    public int z;

    public b(Context context) {
        int i = R$layout.abc_action_menu_layout;
        int i2 = R$layout.abc_action_menu_item_layout;
        this.b = context;
        this.e = LayoutInflater.from(context);
        this.g = i;
        this.h = i2;
        this.t = new SparseBooleanArray();
        this.y = new o70(4, this);
    }

    @Override // defpackage.z60
    public final void a(i60 i60Var, boolean z) {
        f();
        k1 k1Var = this.v;
        if (k1Var != null && k1Var.b()) {
            k1Var.i.dismiss();
        }
        y60 y60Var = this.f;
        if (y60Var != null) {
            y60Var.a(i60Var, z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View b(m60 m60Var, View view, ViewGroup viewGroup) {
        View actionView = m60Var.getActionView();
        if (actionView == null || m60Var.e()) {
            a70 a70Var = view instanceof a70 ? (a70) view : (a70) this.e.inflate(this.h, viewGroup, false);
            a70Var.d(m60Var);
            ActionMenuItemView actionMenuItemView = (ActionMenuItemView) a70Var;
            actionMenuItemView.setItemInvoker((ActionMenuView) this.i);
            if (this.x == null) {
                this.x = new l1(this);
            }
            actionMenuItemView.setPopupCallback(this.x);
            actionView = (View) a70Var;
        }
        actionView.setVisibility(m60Var.C ? 8 : 0);
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        ((ActionMenuView) viewGroup).getClass();
        if (!(layoutParams instanceof ActionMenuView.LayoutParams)) {
            actionView.setLayoutParams(ActionMenuView.m(layoutParams));
        }
        return actionView;
    }

    @Override // defpackage.z60
    public final boolean c() {
        int size;
        ArrayList arrayListL;
        int i;
        boolean z;
        b bVar = this;
        i60 i60Var = bVar.d;
        if (i60Var != null) {
            arrayListL = i60Var.l();
            size = arrayListL.size();
        } else {
            size = 0;
            arrayListL = null;
        }
        int i2 = bVar.r;
        int i3 = bVar.q;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) bVar.i;
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
            m60 m60Var = (m60) arrayListL.get(i4);
            int i7 = m60Var.y;
            if ((i7 & 2) == 2) {
                i5++;
            } else if ((i7 & 1) == 1) {
                i6++;
            } else {
                z2 = true;
            }
            if (bVar.s && m60Var.C) {
                i2 = 0;
            }
            i4++;
        }
        if (bVar.n && (z2 || i6 + i5 > i2)) {
            i2--;
        }
        int i8 = i2 - i5;
        SparseBooleanArray sparseBooleanArray = bVar.t;
        sparseBooleanArray.clear();
        int i9 = 0;
        int i10 = 0;
        while (i9 < size) {
            m60 m60Var2 = (m60) arrayListL.get(i9);
            int i11 = m60Var2.y;
            boolean z3 = (i11 & 2) == i ? z : false;
            int i12 = m60Var2.b;
            if (z3) {
                View viewB = bVar.b(m60Var2, null, viewGroup);
                viewB.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                int measuredWidth = viewB.getMeasuredWidth();
                i3 -= measuredWidth;
                if (i10 == 0) {
                    i10 = measuredWidth;
                }
                if (i12 != 0) {
                    sparseBooleanArray.put(i12, z);
                }
                m60Var2.g(z);
            } else if ((i11 & 1) == z) {
                boolean z4 = sparseBooleanArray.get(i12);
                boolean z5 = ((i8 > 0 || z4) && i3 > 0) ? z : false;
                if (z5) {
                    View viewB2 = bVar.b(m60Var2, null, viewGroup);
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
                        m60 m60Var3 = (m60) arrayListL.get(i13);
                        if (m60Var3.b == i12) {
                            if ((m60Var3.x & 32) == 32) {
                                i8++;
                            }
                            m60Var3.g(false);
                        }
                    }
                }
                if (z5) {
                    i8--;
                }
                m60Var2.g(z5);
            } else {
                m60Var2.g(false);
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

    @Override // defpackage.z60
    public final Parcelable e() {
        ActionMenuPresenter$SavedState actionMenuPresenter$SavedState = new ActionMenuPresenter$SavedState();
        actionMenuPresenter$SavedState.b = this.z;
        return actionMenuPresenter$SavedState;
    }

    public final boolean f() {
        Object obj;
        m1 m1Var = this.w;
        if (m1Var != null && (obj = this.i) != null) {
            ((View) obj).removeCallbacks(m1Var);
            this.w = null;
            return true;
        }
        k1 k1Var = this.u;
        if (k1Var == null) {
            return false;
        }
        if (k1Var.b()) {
            k1Var.i.dismiss();
        }
        return true;
    }

    @Override // defpackage.z60
    public final void g(Parcelable parcelable) {
        int i;
        MenuItem menuItemFindItem;
        if ((parcelable instanceof ActionMenuPresenter$SavedState) && (i = ((ActionMenuPresenter$SavedState) parcelable).b) > 0 && (menuItemFindItem = this.d.findItem(i)) != null) {
            m((el0) menuItemFindItem.getSubMenu());
        }
    }

    public final boolean j() {
        k1 k1Var = this.u;
        return k1Var != null && k1Var.b();
    }

    @Override // defpackage.z60
    public final void k(Context context, i60 i60Var) {
        this.c = context;
        LayoutInflater.from(context);
        this.d = i60Var;
        Resources resources = context.getResources();
        g1 g1VarB = g1.b(context);
        if (!this.o) {
            this.n = true;
        }
        this.p = g1VarB.a.getResources().getDisplayMetrics().widthPixels / 2;
        this.r = g1VarB.c();
        int measuredWidth = this.p;
        if (this.n) {
            if (this.k == null) {
                n1 n1Var = new n1(this, this.b);
                this.k = n1Var;
                if (this.m) {
                    n1Var.setImageDrawable(this.l);
                    this.l = null;
                    this.m = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.k.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.k.getMeasuredWidth();
        } else {
            this.k = null;
        }
        this.q = measuredWidth;
        float f = resources.getDisplayMetrics().density;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.z60
    public final void l(boolean z) {
        int i;
        ViewGroup viewGroup = (ViewGroup) this.i;
        ArrayList arrayList = null;
        boolean z2 = false;
        if (viewGroup != null) {
            i60 i60Var = this.d;
            if (i60Var != null) {
                i60Var.i();
                ArrayList arrayListL = this.d.l();
                int size = arrayListL.size();
                i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    m60 m60Var = (m60) arrayListL.get(i2);
                    if ((m60Var.x & 32) == 32) {
                        View childAt = viewGroup.getChildAt(i);
                        m60 itemData = childAt instanceof a70 ? ((a70) childAt).getItemData() : null;
                        View viewB = b(m60Var, childAt, viewGroup);
                        if (m60Var != itemData) {
                            viewB.setPressed(false);
                            viewB.jumpDrawablesToCurrentState();
                        }
                        if (viewB != childAt) {
                            ViewGroup viewGroup2 = (ViewGroup) viewB.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(viewB);
                            }
                            ((ViewGroup) this.i).addView(viewB, i);
                        }
                        i++;
                    }
                }
            } else {
                i = 0;
            }
            while (i < viewGroup.getChildCount()) {
                if (viewGroup.getChildAt(i) == this.k) {
                    i++;
                } else {
                    viewGroup.removeViewAt(i);
                }
            }
        }
        ((View) this.i).requestLayout();
        i60 i60Var2 = this.d;
        if (i60Var2 != null) {
            i60Var2.i();
            ArrayList arrayList2 = i60Var2.i;
            int size2 = arrayList2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                r1 r1Var = ((m60) arrayList2.get(i3)).A;
                if (r1Var != null) {
                    r1Var.a = this;
                }
            }
        }
        i60 i60Var3 = this.d;
        if (i60Var3 != null) {
            i60Var3.i();
            arrayList = i60Var3.j;
        }
        if (this.n && arrayList != null) {
            int size3 = arrayList.size();
            if (size3 == 1) {
                z2 = !((m60) arrayList.get(0)).C;
            } else if (size3 > 0) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.k == null) {
                this.k = new n1(this, this.b);
            }
            ViewGroup viewGroup3 = (ViewGroup) this.k.getParent();
            if (viewGroup3 != this.i) {
                if (viewGroup3 != null) {
                    viewGroup3.removeView(this.k);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.i;
                n1 n1Var = this.k;
                actionMenuView.getClass();
                ActionMenuView.LayoutParams layoutParamsL = ActionMenuView.l();
                layoutParamsL.a = true;
                actionMenuView.addView(n1Var, layoutParamsL);
            }
        } else {
            n1 n1Var2 = this.k;
            if (n1Var2 != null) {
                Object parent = n1Var2.getParent();
                Object obj = this.i;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.k);
                }
            }
        }
        ((ActionMenuView) this.i).setOverflowReserved(this.n);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.z60
    public final boolean m(el0 el0Var) {
        boolean z;
        if (el0Var.hasVisibleItems()) {
            el0 el0Var2 = el0Var;
            while (true) {
                i60 i60Var = el0Var2.z;
                if (i60Var == this.d) {
                    break;
                }
                el0Var2 = (el0) i60Var;
            }
            ViewGroup viewGroup = (ViewGroup) this.i;
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
                    if ((childAt instanceof a70) && ((a70) childAt).getItemData() == el0Var2.A) {
                        view = childAt;
                        break;
                    }
                    i++;
                }
            }
            if (view != null) {
                this.z = el0Var.A.a;
                int size = el0Var.f.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        z = false;
                        break;
                    }
                    MenuItem item = el0Var.getItem(i2);
                    if (item.isVisible() && item.getIcon() != null) {
                        z = true;
                        break;
                    }
                    i2++;
                }
                k1 k1Var = new k1(this, this.c, el0Var, view);
                this.v = k1Var;
                k1Var.g = z;
                r60 r60Var = k1Var.i;
                if (r60Var != null) {
                    r60Var.q(z);
                }
                k1 k1Var2 = this.v;
                if (!k1Var2.b()) {
                    if (k1Var2.e == null) {
                        throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
                    }
                    k1Var2.d(0, 0, false, false);
                }
                y60 y60Var = this.f;
                if (y60Var != null) {
                    y60Var.p(el0Var);
                }
                return true;
            }
        }
        return false;
    }

    public final boolean n() {
        i60 i60Var;
        if (!this.n || j() || (i60Var = this.d) == null || this.i == null || this.w != null) {
            return false;
        }
        i60Var.i();
        if (i60Var.j.isEmpty()) {
            return false;
        }
        m1 m1Var = new m1(this, 0, new k1(this, this.c, this.d, this.k));
        this.w = m1Var;
        ((View) this.i).post(m1Var);
        return true;
    }
}
