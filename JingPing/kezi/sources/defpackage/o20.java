package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class o20 extends BaseAdapter {
    public int b = -1;
    public final /* synthetic */ p20 c;

    public o20(p20 p20Var) {
        this.c = p20Var;
        a();
    }

    public final void a() {
        i60 i60Var = this.c.d;
        m60 m60Var = i60Var.v;
        if (m60Var != null) {
            i60Var.i();
            ArrayList arrayList = i60Var.j;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((m60) arrayList.get(i)) == m60Var) {
                    this.b = i;
                    return;
                }
            }
        }
        this.b = -1;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final m60 getItem(int i) {
        p20 p20Var = this.c;
        i60 i60Var = p20Var.d;
        i60Var.i();
        ArrayList arrayList = i60Var.j;
        p20Var.getClass();
        int i2 = this.b;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return (m60) arrayList.get(i);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        p20 p20Var = this.c;
        i60 i60Var = p20Var.d;
        i60Var.i();
        int size = i60Var.j.size();
        p20Var.getClass();
        return this.b < 0 ? size : size - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            p20 p20Var = this.c;
            view = p20Var.c.inflate(p20Var.f, viewGroup, false);
        }
        ((a70) view).d(getItem(i));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
