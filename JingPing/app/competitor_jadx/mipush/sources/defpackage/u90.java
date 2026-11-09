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
import io.nats.client.support.NatsObjectStoreUtil;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public final class u90 implements qe0, AdapterView.OnItemClickListener {
    public Context E;
    public LayoutInflater F;
    public zd0 G;
    public ExpandedMenuView H;
    public final int I;
    public pe0 J;
    public t90 K;

    public u90(ContextWrapper contextWrapper, int i) {
        this.I = i;
        this.E = contextWrapper;
        this.F = LayoutInflater.from(contextWrapper);
    }

    @Override // defpackage.qe0
    public final void a(zd0 zd0Var, boolean z) {
        pe0 pe0Var = this.J;
        if (pe0Var != null) {
            pe0Var.a(zd0Var, z);
        }
    }

    @Override // defpackage.qe0
    public final boolean c(de0 de0Var) {
        return false;
    }

    @Override // defpackage.qe0
    public final void e(Parcelable parcelable) {
        SparseArray<Parcelable> sparseParcelableArray = ((Bundle) parcelable).getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.H.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // defpackage.qe0
    public final void f(pe0 pe0Var) {
        throw null;
    }

    @Override // defpackage.qe0
    public final boolean g(de0 de0Var) {
        return false;
    }

    @Override // defpackage.qe0
    public final int getId() {
        return 0;
    }

    @Override // defpackage.qe0
    public final void i(Context context, zd0 zd0Var) {
        if (this.E != null) {
            this.E = context;
            if (this.F == null) {
                this.F = LayoutInflater.from(context);
            }
        }
        this.G = zd0Var;
        t90 t90Var = this.K;
        if (t90Var != null) {
            t90Var.notifyDataSetChanged();
        }
    }

    @Override // defpackage.qe0
    public final void j(boolean z) {
        t90 t90Var = this.K;
        if (t90Var != null) {
            t90Var.notifyDataSetChanged();
        }
    }

    @Override // defpackage.qe0
    public final boolean k(rx0 rx0Var) {
        if (!rx0Var.hasVisibleItems()) {
            return false;
        }
        ae0 ae0Var = new ae0();
        ae0Var.E = rx0Var;
        Context context = rx0Var.a;
        j4 j4Var = new j4(context);
        f4 f4Var = (f4) j4Var.F;
        u90 u90Var = new u90(f4Var.a, R$layout.abc_list_menu_item_layout);
        ae0Var.G = u90Var;
        u90Var.J = ae0Var;
        rx0Var.b(u90Var, context);
        u90 u90Var2 = ae0Var.G;
        if (u90Var2.K == null) {
            u90Var2.K = new t90(u90Var2);
        }
        f4Var.g = u90Var2.K;
        f4Var.h = ae0Var;
        View view = rx0Var.o;
        if (view != null) {
            f4Var.e = view;
        } else {
            f4Var.c = rx0Var.n;
            f4Var.d = rx0Var.m;
        }
        f4Var.f = ae0Var;
        k4 k4VarA = j4Var.a();
        ae0Var.F = k4VarA;
        k4VarA.setOnDismissListener(ae0Var);
        WindowManager.LayoutParams attributes = ae0Var.F.getWindow().getAttributes();
        attributes.type = CloseFrame.REFUSE;
        attributes.flags |= NatsObjectStoreUtil.DEFAULT_CHUNK_SIZE;
        ae0Var.F.show();
        pe0 pe0Var = this.J;
        if (pe0Var == null) {
            return true;
        }
        pe0Var.n(rx0Var);
        return true;
    }

    @Override // defpackage.qe0
    public final boolean l() {
        return false;
    }

    @Override // defpackage.qe0
    public final Parcelable m() {
        if (this.H == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.H;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        return bundle;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.G.q(this.K.getItem(i), this, 0);
    }
}
