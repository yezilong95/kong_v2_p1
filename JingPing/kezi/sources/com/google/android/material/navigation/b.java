package com.google.android.material.navigation;

import android.content.Context;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.MenuItem;
import androidx.transition.AutoTransition;
import com.google.android.material.badge.BadgeState$State;
import com.google.android.material.internal.ParcelableSparseArray;
import defpackage.el0;
import defpackage.i60;
import defpackage.jp0;
import defpackage.m60;
import defpackage.t7;
import defpackage.z60;

/* JADX INFO: loaded from: classes.dex */
public final class b implements z60 {
    public NavigationBarMenuView b;
    public boolean c;
    public int d;

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
        NavigationBarPresenter$SavedState navigationBarPresenter$SavedState = new NavigationBarPresenter$SavedState();
        navigationBarPresenter$SavedState.b = this.b.getSelectedItemId();
        SparseArray<t7> badgeDrawables = this.b.getBadgeDrawables();
        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
        for (int i = 0; i < badgeDrawables.size(); i++) {
            int iKeyAt = badgeDrawables.keyAt(i);
            t7 t7VarValueAt = badgeDrawables.valueAt(i);
            if (t7VarValueAt == null) {
                throw new IllegalArgumentException("badgeDrawable cannot be null");
            }
            parcelableSparseArray.put(iKeyAt, t7VarValueAt.f.a);
        }
        navigationBarPresenter$SavedState.c = parcelableSparseArray;
        return navigationBarPresenter$SavedState;
    }

    @Override // defpackage.z60
    public final void g(Parcelable parcelable) {
        SparseArray sparseArray;
        if (parcelable instanceof NavigationBarPresenter$SavedState) {
            NavigationBarMenuView navigationBarMenuView = this.b;
            NavigationBarPresenter$SavedState navigationBarPresenter$SavedState = (NavigationBarPresenter$SavedState) parcelable;
            int i = navigationBarPresenter$SavedState.b;
            int size = navigationBarMenuView.F.f.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                MenuItem item = navigationBarMenuView.F.getItem(i2);
                if (i == item.getItemId()) {
                    navigationBarMenuView.h = i;
                    navigationBarMenuView.i = i2;
                    item.setChecked(true);
                    break;
                }
                i2++;
            }
            Context context = this.b.getContext();
            ParcelableSparseArray parcelableSparseArray = navigationBarPresenter$SavedState.c;
            SparseArray sparseArray2 = new SparseArray(parcelableSparseArray.size());
            for (int i3 = 0; i3 < parcelableSparseArray.size(); i3++) {
                int iKeyAt = parcelableSparseArray.keyAt(i3);
                BadgeState$State badgeState$State = (BadgeState$State) parcelableSparseArray.valueAt(i3);
                if (badgeState$State == null) {
                    throw new IllegalArgumentException("BadgeDrawable's savedState cannot be null");
                }
                sparseArray2.put(iKeyAt, new t7(context, badgeState$State));
            }
            NavigationBarMenuView navigationBarMenuView2 = this.b;
            navigationBarMenuView2.getClass();
            int i4 = 0;
            while (true) {
                int size2 = sparseArray2.size();
                sparseArray = navigationBarMenuView2.t;
                if (i4 >= size2) {
                    break;
                }
                int iKeyAt2 = sparseArray2.keyAt(i4);
                if (sparseArray.indexOfKey(iKeyAt2) < 0) {
                    sparseArray.append(iKeyAt2, (t7) sparseArray2.get(iKeyAt2));
                }
                i4++;
            }
            NavigationBarItemView[] navigationBarItemViewArr = navigationBarMenuView2.g;
            if (navigationBarItemViewArr != null) {
                for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                    navigationBarItemView.setBadge((t7) sparseArray.get(navigationBarItemView.getId()));
                }
            }
        }
    }

    @Override // defpackage.z60
    public final int getId() {
        return this.d;
    }

    @Override // defpackage.z60
    public final boolean i(m60 m60Var) {
        return false;
    }

    @Override // defpackage.z60
    public final void k(Context context, i60 i60Var) {
        this.b.F = i60Var;
    }

    @Override // defpackage.z60
    public final void l(boolean z) {
        AutoTransition autoTransition;
        if (this.c) {
            return;
        }
        if (z) {
            this.b.a();
            return;
        }
        NavigationBarMenuView navigationBarMenuView = this.b;
        i60 i60Var = navigationBarMenuView.F;
        if (i60Var == null || navigationBarMenuView.g == null) {
            return;
        }
        int size = i60Var.f.size();
        if (size != navigationBarMenuView.g.length) {
            navigationBarMenuView.a();
            return;
        }
        int i = navigationBarMenuView.h;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = navigationBarMenuView.F.getItem(i2);
            if (item.isChecked()) {
                navigationBarMenuView.h = item.getItemId();
                navigationBarMenuView.i = i2;
            }
        }
        if (i != navigationBarMenuView.h && (autoTransition = navigationBarMenuView.b) != null) {
            jp0.a(navigationBarMenuView, autoTransition);
        }
        boolean zF = NavigationBarMenuView.f(navigationBarMenuView.f, navigationBarMenuView.F.l().size());
        for (int i3 = 0; i3 < size; i3++) {
            navigationBarMenuView.E.c = true;
            navigationBarMenuView.g[i3].setLabelVisibilityMode(navigationBarMenuView.f);
            navigationBarMenuView.g[i3].setShifting(zF);
            navigationBarMenuView.g[i3].d((m60) navigationBarMenuView.F.getItem(i3));
            navigationBarMenuView.E.c = false;
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
