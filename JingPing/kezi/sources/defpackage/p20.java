package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.ExpandedMenuView;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public final class p20 implements z60, AdapterView.OnItemClickListener {
    public Context b;
    public LayoutInflater c;
    public i60 d;
    public ExpandedMenuView e;
    public final int f;
    public y60 g;
    public o20 h;

    public p20(ContextWrapper contextWrapper, int i) {
        this.f = i;
        this.b = contextWrapper;
        this.c = LayoutInflater.from(contextWrapper);
    }

    @Override // defpackage.z60
    public final void a(i60 i60Var, boolean z) {
        y60 y60Var = this.g;
        if (y60Var != null) {
            y60Var.a(i60Var, z);
        }
    }

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
        if (this.e == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.e;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        return bundle;
    }

    @Override // defpackage.z60
    public final void g(Parcelable parcelable) {
        SparseArray<Parcelable> sparseParcelableArray = ((Bundle) parcelable).getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.e.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // defpackage.z60
    public final int getId() {
        return 0;
    }

    @Override // defpackage.z60
    public final void h(y60 y60Var) {
        throw null;
    }

    @Override // defpackage.z60
    public final boolean i(m60 m60Var) {
        return false;
    }

    @Override // defpackage.z60
    public final void k(Context context, i60 i60Var) {
        if (this.b != null) {
            this.b = context;
            if (this.c == null) {
                this.c = LayoutInflater.from(context);
            }
        }
        this.d = i60Var;
        o20 o20Var = this.h;
        if (o20Var != null) {
            o20Var.notifyDataSetChanged();
        }
    }

    @Override // defpackage.z60
    public final void l(boolean z) {
        o20 o20Var = this.h;
        if (o20Var != null) {
            o20Var.notifyDataSetChanged();
        }
    }

    @Override // defpackage.z60
    public final boolean m(el0 el0Var) {
        if (!el0Var.hasVisibleItems()) {
            return false;
        }
        j60 j60Var = new j60();
        j60Var.b = el0Var;
        Context context = el0Var.a;
        w2 w2Var = new w2(context);
        s2 s2Var = (s2) w2Var.c;
        p20 p20Var = new p20(s2Var.a, R$layout.abc_list_menu_item_layout);
        j60Var.d = p20Var;
        p20Var.g = j60Var;
        el0Var.b(p20Var, context);
        p20 p20Var2 = j60Var.d;
        if (p20Var2.h == null) {
            p20Var2.h = new o20(p20Var2);
        }
        s2Var.g = p20Var2.h;
        s2Var.h = j60Var;
        View view = el0Var.o;
        if (view != null) {
            s2Var.e = view;
        } else {
            s2Var.c = el0Var.n;
            s2Var.d = el0Var.m;
        }
        s2Var.f = j60Var;
        x2 x2VarA = w2Var.a();
        j60Var.c = x2VarA;
        x2VarA.setOnDismissListener(j60Var);
        WindowManager.LayoutParams attributes = j60Var.c.getWindow().getAttributes();
        attributes.type = CloseFrame.REFUSE;
        attributes.flags |= 131072;
        j60Var.c.show();
        y60 y60Var = this.g;
        if (y60Var == null) {
            return true;
        }
        y60Var.p(el0Var);
        return true;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.d.q(this.h.getItem(i), this, 0);
    }
}
