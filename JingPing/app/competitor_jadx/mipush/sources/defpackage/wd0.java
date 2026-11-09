package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class wd0 extends BaseAdapter {
    public final zd0 E;
    public int F = -1;
    public boolean G;
    public final boolean H;
    public final LayoutInflater I;
    public final int J;

    public wd0(zd0 zd0Var, LayoutInflater layoutInflater, boolean z, int i) {
        this.H = z;
        this.I = layoutInflater;
        this.E = zd0Var;
        this.J = i;
        a();
    }

    public final void a() {
        zd0 zd0Var = this.E;
        de0 de0Var = zd0Var.v;
        if (de0Var != null) {
            zd0Var.i();
            ArrayList arrayList = zd0Var.j;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((de0) arrayList.get(i)) == de0Var) {
                    this.F = i;
                    return;
                }
            }
        }
        this.F = -1;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final de0 getItem(int i) {
        ArrayList arrayListL;
        zd0 zd0Var = this.E;
        if (this.H) {
            zd0Var.i();
            arrayListL = zd0Var.j;
        } else {
            arrayListL = zd0Var.l();
        }
        int i2 = this.F;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return (de0) arrayListL.get(i);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        ArrayList arrayListL;
        zd0 zd0Var = this.E;
        if (this.H) {
            zd0Var.i();
            arrayListL = zd0Var.j;
        } else {
            arrayListL = zd0Var.l();
        }
        return this.F < 0 ? arrayListL.size() : arrayListL.size() - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        boolean z = false;
        if (view == null) {
            view = this.I.inflate(this.J, viewGroup, false);
        }
        int i2 = getItem(i).b;
        int i3 = i - 1;
        int i4 = i3 >= 0 ? getItem(i3).b : i2;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        if (this.E.m() && i2 != i4) {
            z = true;
        }
        listMenuItemView.setGroupDividerEnabled(z);
        re0 re0Var = (re0) view;
        if (this.G) {
            listMenuItemView.setForceShowIcon(true);
        }
        re0Var.d(getItem(i));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
