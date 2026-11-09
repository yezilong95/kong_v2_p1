package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.material.R$dimen;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.internal.ParcelableSparseArray;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class b90 implements z60 {
    public int A;
    public int B;
    public NavigationMenuView b;
    public LinearLayout c;
    public i60 d;
    public int e;
    public t80 f;
    public LayoutInflater g;
    public ColorStateList i;
    public ColorStateList l;
    public ColorStateList m;
    public Drawable n;
    public RippleDrawable o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public boolean x;
    public int z;
    public int h = 0;
    public int j = 0;
    public boolean k = true;
    public boolean y = true;
    public int C = -1;
    public final c1 D = new c1(4, this);

    @Override // defpackage.z60
    public final boolean c() {
        return false;
    }

    @Override // defpackage.z60
    public final boolean d(m60 m60Var) {
        return false;
    }

    @Override // defpackage.z60
    public final Parcelable e() {
        Bundle bundle = new Bundle();
        if (this.b != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.b.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        t80 t80Var = this.f;
        if (t80Var != null) {
            t80Var.getClass();
            Bundle bundle2 = new Bundle();
            m60 m60Var = t80Var.d;
            if (m60Var != null) {
                bundle2.putInt("android:menu:checked", m60Var.a);
            }
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            ArrayList arrayList = t80Var.c;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                v80 v80Var = (v80) arrayList.get(i);
                if (v80Var instanceof x80) {
                    m60 m60Var2 = ((x80) v80Var).a;
                    View actionView = m60Var2 != null ? m60Var2.getActionView() : null;
                    if (actionView != null) {
                        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
                        actionView.saveHierarchyState(parcelableSparseArray);
                        sparseArray2.put(m60Var2.a, parcelableSparseArray);
                    }
                }
            }
            bundle2.putSparseParcelableArray("android:menu:action_views", sparseArray2);
            bundle.putBundle("android:menu:adapter", bundle2);
        }
        if (this.c != null) {
            SparseArray<Parcelable> sparseArray3 = new SparseArray<>();
            this.c.saveHierarchyState(sparseArray3);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray3);
        }
        return bundle;
    }

    @Override // defpackage.z60
    public final void g(Parcelable parcelable) {
        m60 m60Var;
        View actionView;
        ParcelableSparseArray parcelableSparseArray;
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.b.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                t80 t80Var = this.f;
                t80Var.getClass();
                int i = bundle2.getInt("android:menu:checked", 0);
                ArrayList arrayList = t80Var.c;
                if (i != 0) {
                    t80Var.e = true;
                    int size = arrayList.size();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= size) {
                            break;
                        }
                        v80 v80Var = (v80) arrayList.get(i2);
                        if (v80Var instanceof x80) {
                            m60 m60Var2 = ((x80) v80Var).a;
                            if (m60Var2.a == i) {
                                t80Var.h(m60Var2);
                                break;
                            }
                        }
                        i2++;
                    }
                    t80Var.e = false;
                    t80Var.g();
                }
                SparseArray sparseParcelableArray2 = bundle2.getSparseParcelableArray("android:menu:action_views");
                if (sparseParcelableArray2 != null) {
                    int size2 = arrayList.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        v80 v80Var2 = (v80) arrayList.get(i3);
                        if ((v80Var2 instanceof x80) && (actionView = (m60Var = ((x80) v80Var2).a).getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray2.get(m60Var.a)) != null) {
                            actionView.restoreHierarchyState(parcelableSparseArray);
                        }
                    }
                }
            }
            SparseArray<Parcelable> sparseParcelableArray3 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray3 != null) {
                this.c.restoreHierarchyState(sparseParcelableArray3);
            }
        }
    }

    @Override // defpackage.z60
    public final int getId() {
        return this.e;
    }

    @Override // defpackage.z60
    public final boolean i(m60 m60Var) {
        return false;
    }

    @Override // defpackage.z60
    public final void k(Context context, i60 i60Var) {
        this.g = LayoutInflater.from(context);
        this.d = i60Var;
        this.B = context.getResources().getDimensionPixelOffset(R$dimen.design_navigation_separator_vertical_padding);
    }

    @Override // defpackage.z60
    public final void l(boolean z) {
        t80 t80Var = this.f;
        if (t80Var != null) {
            t80Var.g();
            t80Var.a.b();
        }
    }

    @Override // defpackage.z60
    public final boolean m(el0 el0Var) {
        return false;
    }

    @Override // defpackage.z60
    public final void a(i60 i60Var, boolean z) {
    }
}
