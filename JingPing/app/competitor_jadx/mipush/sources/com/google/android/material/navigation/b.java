package com.google.android.material.navigation;

import android.content.Context;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.MenuItem;
import androidx.transition.AutoTransition;
import com.google.android.material.badge.BadgeState$State;
import com.google.android.material.internal.ParcelableSparseArray;
import defpackage.de0;
import defpackage.n9;
import defpackage.qe0;
import defpackage.rx0;
import defpackage.v11;
import defpackage.zd0;

/* JADX INFO: loaded from: classes.dex */
public final class b implements qe0 {
    public NavigationBarMenuView E;
    public boolean F;
    public int G;

    @Override // defpackage.qe0
    public final boolean c(de0 de0Var) {
        return false;
    }

    @Override // defpackage.qe0
    public final void e(Parcelable parcelable) {
        SparseArray sparseArray;
        if (parcelable instanceof NavigationBarPresenter$SavedState) {
            NavigationBarMenuView navigationBarMenuView = this.E;
            NavigationBarPresenter$SavedState navigationBarPresenter$SavedState = (NavigationBarPresenter$SavedState) parcelable;
            int i = navigationBarPresenter$SavedState.E;
            int size = navigationBarMenuView.l0.f.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                MenuItem item = navigationBarMenuView.l0.getItem(i2);
                if (i == item.getItemId()) {
                    navigationBarMenuView.K = i;
                    navigationBarMenuView.L = i2;
                    item.setChecked(true);
                    break;
                }
                i2++;
            }
            Context context = this.E.getContext();
            ParcelableSparseArray parcelableSparseArray = navigationBarPresenter$SavedState.F;
            SparseArray sparseArray2 = new SparseArray(parcelableSparseArray.size());
            for (int i3 = 0; i3 < parcelableSparseArray.size(); i3++) {
                int iKeyAt = parcelableSparseArray.keyAt(i3);
                BadgeState$State badgeState$State = (BadgeState$State) parcelableSparseArray.valueAt(i3);
                sparseArray2.put(iKeyAt, badgeState$State != null ? new n9(context, badgeState$State) : null);
            }
            NavigationBarMenuView navigationBarMenuView2 = this.E;
            navigationBarMenuView2.getClass();
            int i4 = 0;
            while (true) {
                int size2 = sparseArray2.size();
                sparseArray = navigationBarMenuView2.W;
                if (i4 >= size2) {
                    break;
                }
                int iKeyAt2 = sparseArray2.keyAt(i4);
                if (sparseArray.indexOfKey(iKeyAt2) < 0) {
                    sparseArray.append(iKeyAt2, (n9) sparseArray2.get(iKeyAt2));
                }
                i4++;
            }
            NavigationBarItemView[] navigationBarItemViewArr = navigationBarMenuView2.J;
            if (navigationBarItemViewArr != null) {
                for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                    n9 n9Var = (n9) sparseArray.get(navigationBarItemView.getId());
                    if (n9Var != null) {
                        navigationBarItemView.setBadge(n9Var);
                    }
                }
            }
        }
    }

    @Override // defpackage.qe0
    public final boolean g(de0 de0Var) {
        return false;
    }

    @Override // defpackage.qe0
    public final int getId() {
        return this.G;
    }

    @Override // defpackage.qe0
    public final void i(Context context, zd0 zd0Var) {
        this.E.l0 = zd0Var;
    }

    @Override // defpackage.qe0
    public final void j(boolean z) {
        AutoTransition autoTransition;
        if (this.F) {
            return;
        }
        if (z) {
            this.E.a();
            return;
        }
        NavigationBarMenuView navigationBarMenuView = this.E;
        zd0 zd0Var = navigationBarMenuView.l0;
        if (zd0Var == null || navigationBarMenuView.J == null) {
            return;
        }
        int size = zd0Var.f.size();
        if (size != navigationBarMenuView.J.length) {
            navigationBarMenuView.a();
            return;
        }
        int i = navigationBarMenuView.K;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = navigationBarMenuView.l0.getItem(i2);
            if (item.isChecked()) {
                navigationBarMenuView.K = item.getItemId();
                navigationBarMenuView.L = i2;
            }
        }
        if (i != navigationBarMenuView.K && (autoTransition = navigationBarMenuView.E) != null) {
            v11.a(navigationBarMenuView, autoTransition);
        }
        boolean zF = NavigationBarMenuView.f(navigationBarMenuView.I, navigationBarMenuView.l0.l().size());
        for (int i3 = 0; i3 < size; i3++) {
            navigationBarMenuView.k0.F = true;
            navigationBarMenuView.J[i3].setLabelVisibilityMode(navigationBarMenuView.I);
            navigationBarMenuView.J[i3].setShifting(zF);
            navigationBarMenuView.J[i3].d((de0) navigationBarMenuView.l0.getItem(i3));
            navigationBarMenuView.k0.F = false;
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
        NavigationBarPresenter$SavedState navigationBarPresenter$SavedState = new NavigationBarPresenter$SavedState();
        navigationBarPresenter$SavedState.E = this.E.getSelectedItemId();
        SparseArray<n9> badgeDrawables = this.E.getBadgeDrawables();
        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
        for (int i = 0; i < badgeDrawables.size(); i++) {
            int iKeyAt = badgeDrawables.keyAt(i);
            n9 n9VarValueAt = badgeDrawables.valueAt(i);
            parcelableSparseArray.put(iKeyAt, n9VarValueAt != null ? n9VarValueAt.I.a : null);
        }
        navigationBarPresenter$SavedState.F = parcelableSparseArray;
        return navigationBarPresenter$SavedState;
    }

    @Override // defpackage.qe0
    public final void a(zd0 zd0Var, boolean z) {
    }
}
