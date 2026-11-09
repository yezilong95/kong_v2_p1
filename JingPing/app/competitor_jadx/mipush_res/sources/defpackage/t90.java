package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class t90 extends BaseAdapter {
    public int E = -1;
    public final /* synthetic */ u90 F;

    public t90(u90 u90Var) {
        this.F = u90Var;
        a();
    }

    public final void a() {
        zd0 zd0Var = this.F.G;
        de0 de0Var = zd0Var.v;
        if (de0Var != null) {
            zd0Var.i();
            ArrayList arrayList = zd0Var.j;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((de0) arrayList.get(i)) == de0Var) {
                    this.E = i;
                    return;
                }
            }
        }
        this.E = -1;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final de0 getItem(int i) {
        u90 u90Var = this.F;
        zd0 zd0Var = u90Var.G;
        zd0Var.i();
        ArrayList arrayList = zd0Var.j;
        u90Var.getClass();
        int i2 = this.E;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return (de0) arrayList.get(i);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        u90 u90Var = this.F;
        zd0 zd0Var = u90Var.G;
        zd0Var.i();
        int size = zd0Var.j.size();
        u90Var.getClass();
        return this.E < 0 ? size : size - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            u90 u90Var = this.F;
            view = u90Var.F.inflate(u90Var.I, viewGroup, false);
        }
        ((re0) view).d(getItem(i));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
