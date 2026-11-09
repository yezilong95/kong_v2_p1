package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class f60 extends BaseAdapter {
    public final i60 b;
    public int c = -1;
    public boolean d;
    public final boolean e;
    public final LayoutInflater f;
    public final int g;

    public f60(i60 i60Var, LayoutInflater layoutInflater, boolean z, int i) {
        this.e = z;
        this.f = layoutInflater;
        this.b = i60Var;
        this.g = i;
        a();
    }

    public final void a() {
        i60 i60Var = this.b;
        m60 m60Var = i60Var.v;
        if (m60Var != null) {
            i60Var.i();
            ArrayList arrayList = i60Var.j;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((m60) arrayList.get(i)) == m60Var) {
                    this.c = i;
                    return;
                }
            }
        }
        this.c = -1;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final m60 getItem(int i) {
        ArrayList arrayListL;
        i60 i60Var = this.b;
        if (this.e) {
            i60Var.i();
            arrayListL = i60Var.j;
        } else {
            arrayListL = i60Var.l();
        }
        int i2 = this.c;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return (m60) arrayListL.get(i);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        ArrayList arrayListL;
        i60 i60Var = this.b;
        if (this.e) {
            i60Var.i();
            arrayListL = i60Var.j;
        } else {
            arrayListL = i60Var.l();
        }
        return this.c < 0 ? arrayListL.size() : arrayListL.size() - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        boolean z = false;
        if (view == null) {
            view = this.f.inflate(this.g, viewGroup, false);
        }
        int i2 = getItem(i).b;
        int i3 = i - 1;
        int i4 = i3 >= 0 ? getItem(i3).b : i2;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        if (this.b.m() && i2 != i4) {
            z = true;
        }
        listMenuItemView.setGroupDividerEnabled(z);
        a70 a70Var = (a70) view;
        if (this.d) {
            listMenuItemView.setForceShowIcon(true);
        }
        a70Var.d(getItem(i));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
