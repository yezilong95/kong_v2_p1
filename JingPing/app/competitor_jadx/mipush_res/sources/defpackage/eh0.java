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
public final class eh0 implements qe0 {
    public NavigationMenuView E;
    public LinearLayout F;
    public zd0 G;
    public int H;
    public wg0 I;
    public LayoutInflater J;
    public ColorStateList L;
    public ColorStateList O;
    public ColorStateList P;
    public Drawable Q;
    public RippleDrawable R;
    public int S;
    public int T;
    public int U;
    public int V;
    public int W;
    public int X;
    public int Y;
    public int Z;
    public boolean a0;
    public int c0;
    public int d0;
    public int e0;
    public int K = 0;
    public int M = 0;
    public boolean N = true;
    public boolean b0 = true;
    public int f0 = -1;
    public final y2 g0 = new y2(4, this);

    @Override // defpackage.qe0
    public final boolean c(de0 de0Var) {
        return false;
    }

    @Override // defpackage.qe0
    public final void e(Parcelable parcelable) {
        de0 de0Var;
        View actionView;
        ParcelableSparseArray parcelableSparseArray;
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
            if (sparseParcelableArray != null) {
                this.E.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                wg0 wg0Var = this.I;
                wg0Var.getClass();
                int i = bundle2.getInt("android:menu:checked", 0);
                ArrayList arrayList = wg0Var.c;
                if (i != 0) {
                    wg0Var.e = true;
                    int size = arrayList.size();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= size) {
                            break;
                        }
                        yg0 yg0Var = (yg0) arrayList.get(i2);
                        if (yg0Var instanceof ah0) {
                            de0 de0Var2 = ((ah0) yg0Var).a;
                            if (de0Var2.a == i) {
                                wg0Var.h(de0Var2);
                                break;
                            }
                        }
                        i2++;
                    }
                    wg0Var.e = false;
                    wg0Var.g();
                }
                SparseArray sparseParcelableArray2 = bundle2.getSparseParcelableArray("android:menu:action_views");
                if (sparseParcelableArray2 != null) {
                    int size2 = arrayList.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        yg0 yg0Var2 = (yg0) arrayList.get(i3);
                        if ((yg0Var2 instanceof ah0) && (actionView = (de0Var = ((ah0) yg0Var2).a).getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray2.get(de0Var.a)) != null) {
                            actionView.restoreHierarchyState(parcelableSparseArray);
                        }
                    }
                }
            }
            SparseArray<Parcelable> sparseParcelableArray3 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray3 != null) {
                this.F.restoreHierarchyState(sparseParcelableArray3);
            }
        }
    }

    @Override // defpackage.qe0
    public final boolean g(de0 de0Var) {
        return false;
    }

    @Override // defpackage.qe0
    public final int getId() {
        return this.H;
    }

    @Override // defpackage.qe0
    public final void i(Context context, zd0 zd0Var) {
        this.J = LayoutInflater.from(context);
        this.G = zd0Var;
        this.e0 = context.getResources().getDimensionPixelOffset(R$dimen.design_navigation_separator_vertical_padding);
    }

    @Override // defpackage.qe0
    public final void j(boolean z) {
        wg0 wg0Var = this.I;
        if (wg0Var != null) {
            wg0Var.g();
            wg0Var.a.b();
        }
    }

    @Override // defpackage.qe0
    public final boolean k(rx0 rx0Var) {
        return false;
    }

    @Override // defpackage.qe0
    public final boolean l() {
        return false;
    }

    @Override // defpackage.qe0
    public final Parcelable m() {
        Bundle bundle = new Bundle();
        if (this.E != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.E.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        wg0 wg0Var = this.I;
        if (wg0Var != null) {
            wg0Var.getClass();
            Bundle bundle2 = new Bundle();
            de0 de0Var = wg0Var.d;
            if (de0Var != null) {
                bundle2.putInt("android:menu:checked", de0Var.a);
            }
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            ArrayList arrayList = wg0Var.c;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                yg0 yg0Var = (yg0) arrayList.get(i);
                if (yg0Var instanceof ah0) {
                    de0 de0Var2 = ((ah0) yg0Var).a;
                    View actionView = de0Var2 != null ? de0Var2.getActionView() : null;
                    if (actionView != null) {
                        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
                        actionView.saveHierarchyState(parcelableSparseArray);
                        sparseArray2.put(de0Var2.a, parcelableSparseArray);
                    }
                }
            }
            bundle2.putSparseParcelableArray("android:menu:action_views", sparseArray2);
            bundle.putBundle("android:menu:adapter", bundle2);
        }
        if (this.F != null) {
            SparseArray<Parcelable> sparseArray3 = new SparseArray<>();
            this.F.saveHierarchyState(sparseArray3);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray3);
        }
        return bundle;
    }

    @Override // defpackage.qe0
    public final void a(zd0 zd0Var, boolean z) {
    }
}
