package defpackage;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.c;
import androidx.lifecycle.Lifecycle$State;
import androidx.lifecycle.runtime.R$id;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public abstract class vz implements ComponentCallbacks, View.OnCreateContextMenuListener, x80, s51, w20, kr0 {
    public static final Object u0 = new Object();
    public Bundle F;
    public SparseArray G;
    public Bundle H;
    public Bundle J;
    public vz K;
    public int M;
    public boolean O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public boolean S;
    public boolean T;
    public int U;
    public c V;
    public wz W;
    public vz Y;
    public int Z;
    public int a0;
    public String b0;
    public boolean c0;
    public boolean d0;
    public boolean e0;
    public boolean g0;
    public ViewGroup h0;
    public View i0;
    public boolean j0;
    public sz l0;
    public boolean m0;
    public boolean n0;
    public z80 p0;
    public n00 q0;
    public i6 s0;
    public final ArrayList t0;
    public int E = -1;
    public String I = UUID.randomUUID().toString();
    public String L = null;
    public Boolean N = null;
    public h00 X = new h00();
    public final boolean f0 = true;
    public boolean k0 = true;
    public Lifecycle$State o0 = Lifecycle$State.RESUMED;
    public final wf0 r0 = new wf0();

    public vz() {
        new AtomicInteger();
        this.t0 = new ArrayList();
        this.p0 = new z80(this);
        this.s0 = new i6(new jr0(this, new qg(this, 2)));
    }

    private void registerOnPreAttachListener(tz tzVar) {
        if (this.E >= 0) {
            throw null;
        }
        this.t0.add(tzVar);
    }

    public final void A(int i, int i2, int i3, int i4) {
        if (this.l0 == null && i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            return;
        }
        e().b = i;
        e().c = i2;
        e().d = i3;
        e().e = i4;
    }

    public final void B(Bundle bundle) {
        c cVar = this.V;
        if (cVar != null && (cVar.z || cVar.A)) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.J = bundle;
    }

    @Override // defpackage.kr0
    public final i6 a() {
        return (i6) this.s0.G;
    }

    public z91 b() {
        return new rz(this);
    }

    @Override // defpackage.s51
    public final r51 d() {
        if (this.V == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (h() == Lifecycle$State.INITIALIZED.ordinal()) {
            throw new IllegalStateException("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
        }
        HashMap map = this.V.G.d;
        r51 r51Var = (r51) map.get(this.I);
        if (r51Var != null) {
            return r51Var;
        }
        r51 r51Var2 = new r51();
        map.put(this.I, r51Var2);
        return r51Var2;
    }

    public final sz e() {
        if (this.l0 == null) {
            sz szVar = new sz();
            Object obj = u0;
            szVar.g = obj;
            szVar.h = obj;
            szVar.i = obj;
            szVar.j = 1.0f;
            szVar.k = null;
            this.l0 = szVar;
        }
        return this.l0;
    }

    public final c f() {
        if (this.W != null) {
            return this.X;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    public final Context g() {
        wz wzVar = this.W;
        if (wzVar == null) {
            return null;
        }
        return wzVar.c0;
    }

    @Override // defpackage.x80
    public final s80 getLifecycle() {
        return this.p0;
    }

    public final int h() {
        Lifecycle$State lifecycle$State = this.o0;
        return (lifecycle$State == Lifecycle$State.INITIALIZED || this.Y == null) ? lifecycle$State.ordinal() : Math.min(lifecycle$State.ordinal(), this.Y.h());
    }

    public final c i() {
        c cVar = this.V;
        if (cVar != null) {
            return cVar;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    public final void j(int i, int i2, Intent intent) {
        if (c.B(2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in onActivityResult(): requestCode: " + i + " resultCode: " + i2 + " data: " + intent);
        }
    }

    public void k(FragmentActivity fragmentActivity) {
        this.g0 = true;
        wz wzVar = this.W;
        if ((wzVar == null ? null : wzVar.b0) != null) {
            this.g0 = true;
        }
    }

    public void l(Bundle bundle) {
        Parcelable parcelable;
        this.g0 = true;
        if (bundle != null && (parcelable = bundle.getParcelable("android:support:fragments")) != null) {
            this.X.L(parcelable);
            h00 h00Var = this.X;
            h00Var.z = false;
            h00Var.A = false;
            h00Var.G.g = false;
            h00Var.p(1);
        }
        h00 h00Var2 = this.X;
        if (h00Var2.n >= 1) {
            return;
        }
        h00Var2.z = false;
        h00Var2.A = false;
        h00Var2.G.g = false;
        h00Var2.p(1);
    }

    public View m(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public void n() {
        this.g0 = true;
    }

    public void o() {
        this.g0 = true;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        this.g0 = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        wz wzVar = this.W;
        FragmentActivity fragmentActivity = wzVar == null ? null : wzVar.b0;
        if (fragmentActivity != null) {
            fragmentActivity.onCreateContextMenu(contextMenu, view, contextMenuInfo);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        this.g0 = true;
    }

    public LayoutInflater p(Bundle bundle) {
        wz wzVar = this.W;
        if (wzVar == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        FragmentActivity fragmentActivity = wzVar.f0;
        LayoutInflater layoutInflaterCloneInContext = fragmentActivity.getLayoutInflater().cloneInContext(fragmentActivity);
        layoutInflaterCloneInContext.setFactory2(this.X.f);
        return layoutInflaterCloneInContext;
    }

    public abstract void q(Bundle bundle);

    public void r() {
        this.g0 = true;
    }

    public void s() {
        this.g0 = true;
    }

    public void setOnStartEnterTransitionListener(uz uzVar) {
        e();
        this.l0.getClass();
        if (uzVar == null || uzVar == null) {
            return;
        }
        ((g00) uzVar).a++;
    }

    public void t(Bundle bundle) {
        this.g0 = true;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(Uuid.SIZE_BITS);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} (");
        sb.append(this.I);
        if (this.Z != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.Z));
        }
        if (this.b0 != null) {
            sb.append(" tag=");
            sb.append(this.b0);
        }
        sb.append(")");
        return sb.toString();
    }

    public void u(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.X.G();
        this.T = true;
        this.q0 = new n00(this, d());
        View viewM = m(layoutInflater, viewGroup, bundle);
        this.i0 = viewM;
        if (viewM == null) {
            if (this.q0.F != null) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.q0 = null;
            return;
        }
        this.q0.e();
        View view = this.i0;
        n00 n00Var = this.q0;
        t40.n(view, "<this>");
        view.setTag(R$id.view_tree_lifecycle_owner, n00Var);
        View view2 = this.i0;
        n00 n00Var2 = this.q0;
        t40.n(view2, "<this>");
        view2.setTag(androidx.lifecycle.viewmodel.R$id.view_tree_view_model_store_owner, n00Var2);
        View view3 = this.i0;
        n00 n00Var3 = this.q0;
        t40.n(view3, "<this>");
        view3.setTag(androidx.savedstate.R$id.view_tree_saved_state_registry_owner, n00Var3);
        this.r0.g(this.q0);
    }

    public final void v() {
        this.g0 = true;
        for (vz vzVar : this.X.c.D()) {
            if (vzVar != null) {
                vzVar.v();
            }
        }
    }

    public final void w() {
        for (vz vzVar : this.X.c.D()) {
            if (vzVar != null) {
                vzVar.w();
            }
        }
    }

    public final void x() {
        for (vz vzVar : this.X.c.D()) {
            if (vzVar != null) {
                vzVar.x();
            }
        }
    }

    public final Context y() {
        Context contextG = g();
        if (contextG != null) {
            return contextG;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    public final View z() {
        View view = this.i0;
        if (view != null) {
            return view;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }
}
