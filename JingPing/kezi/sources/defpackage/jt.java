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
public abstract class jt implements ComponentCallbacks, View.OnCreateContextMenuListener, u10, ls0, qv, vg0 {
    public static final Object R = new Object();
    public boolean A;
    public boolean B;
    public boolean D;
    public ViewGroup E;
    public View F;
    public boolean G;
    public ft I;
    public boolean J;
    public boolean K;
    public w10 M;
    public bu N;
    public e5 P;
    public final ArrayList Q;
    public Bundle c;
    public SparseArray d;
    public Bundle e;
    public Bundle g;
    public jt h;
    public int j;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public int r;
    public c s;
    public kt t;
    public jt v;
    public int w;
    public int x;
    public String y;
    public boolean z;
    public int b = -1;
    public String f = UUID.randomUUID().toString();
    public String i = null;
    public Boolean k = null;
    public vt u = new vt();
    public final boolean C = true;
    public boolean H = true;
    public Lifecycle$State L = Lifecycle$State.RESUMED;
    public final c80 O = new c80();

    public jt() {
        new AtomicInteger();
        this.Q = new ArrayList();
        this.M = new w10(this);
        this.P = new e5(new ug0(this, new we(this, 2)));
    }

    private void registerOnPreAttachListener(gt gtVar) {
        if (this.b >= 0) {
            throw null;
        }
        this.Q.add(gtVar);
    }

    public final void A(int i, int i2, int i3, int i4) {
        if (this.I == null && i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            return;
        }
        e().b = i;
        e().c = i2;
        e().d = i3;
        e().e = i4;
    }

    public final void B(Bundle bundle) {
        c cVar = this.s;
        if (cVar != null && (cVar.z || cVar.A)) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.g = bundle;
    }

    @Override // defpackage.vg0
    public final e5 b() {
        return (e5) this.P.d;
    }

    public nv0 c() {
        return new et(this);
    }

    @Override // defpackage.ls0
    public final ks0 d() {
        if (this.s == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (h() == Lifecycle$State.INITIALIZED.ordinal()) {
            throw new IllegalStateException("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
        }
        HashMap map = this.s.G.d;
        ks0 ks0Var = (ks0) map.get(this.f);
        if (ks0Var != null) {
            return ks0Var;
        }
        ks0 ks0Var2 = new ks0();
        map.put(this.f, ks0Var2);
        return ks0Var2;
    }

    public final ft e() {
        if (this.I == null) {
            ft ftVar = new ft();
            Object obj = R;
            ftVar.g = obj;
            ftVar.h = obj;
            ftVar.i = obj;
            ftVar.j = 1.0f;
            ftVar.k = null;
            this.I = ftVar;
        }
        return this.I;
    }

    public final c f() {
        if (this.t != null) {
            return this.u;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    public final Context g() {
        kt ktVar = this.t;
        if (ktVar == null) {
            return null;
        }
        return ktVar.u;
    }

    @Override // defpackage.u10
    public final p10 getLifecycle() {
        return this.M;
    }

    public final int h() {
        Lifecycle$State lifecycle$State = this.L;
        return (lifecycle$State == Lifecycle$State.INITIALIZED || this.v == null) ? lifecycle$State.ordinal() : Math.min(lifecycle$State.ordinal(), this.v.h());
    }

    public final c i() {
        c cVar = this.s;
        if (cVar != null) {
            return cVar;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    public final void j(int i, int i2, Intent intent) {
        if (c.C(2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in onActivityResult(): requestCode: " + i + " resultCode: " + i2 + " data: " + intent);
        }
    }

    public void k(FragmentActivity fragmentActivity) {
        this.D = true;
        kt ktVar = this.t;
        if ((ktVar == null ? null : ktVar.t) != null) {
            this.D = true;
        }
    }

    public void l(Bundle bundle) {
        Parcelable parcelable;
        this.D = true;
        if (bundle != null && (parcelable = bundle.getParcelable("android:support:fragments")) != null) {
            this.u.M(parcelable);
            vt vtVar = this.u;
            vtVar.z = false;
            vtVar.A = false;
            vtVar.G.g = false;
            vtVar.p(1);
        }
        vt vtVar2 = this.u;
        if (vtVar2.n >= 1) {
            return;
        }
        vtVar2.z = false;
        vtVar2.A = false;
        vtVar2.G.g = false;
        vtVar2.p(1);
    }

    public View m(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public void n() {
        this.D = true;
    }

    public void o() {
        this.D = true;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        this.D = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        kt ktVar = this.t;
        FragmentActivity fragmentActivity = ktVar == null ? null : ktVar.t;
        if (fragmentActivity != null) {
            fragmentActivity.onCreateContextMenu(contextMenu, view, contextMenuInfo);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        this.D = true;
    }

    public LayoutInflater p(Bundle bundle) {
        kt ktVar = this.t;
        if (ktVar == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        FragmentActivity fragmentActivity = ktVar.x;
        LayoutInflater layoutInflaterCloneInContext = fragmentActivity.getLayoutInflater().cloneInContext(fragmentActivity);
        layoutInflaterCloneInContext.setFactory2(this.u.f);
        return layoutInflaterCloneInContext;
    }

    public abstract void q(Bundle bundle);

    public void r() {
        this.D = true;
    }

    public void s() {
        this.D = true;
    }

    public void setOnStartEnterTransitionListener(ht htVar) {
        e();
        this.I.getClass();
        if (htVar == null || htVar == null) {
            return;
        }
        ((ut) htVar).a++;
    }

    public void t(Bundle bundle) {
        this.D = true;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(Uuid.SIZE_BITS);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} (");
        sb.append(this.f);
        if (this.w != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.w));
        }
        if (this.y != null) {
            sb.append(" tag=");
            sb.append(this.y);
        }
        sb.append(")");
        return sb.toString();
    }

    public void u(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.u.H();
        this.q = true;
        this.N = new bu(this, d());
        View viewM = m(layoutInflater, viewGroup, bundle);
        this.F = viewM;
        if (viewM == null) {
            if (this.N.c != null) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.N = null;
            return;
        }
        this.N.e();
        View view = this.F;
        bu buVar = this.N;
        oy.j(view, "<this>");
        view.setTag(R$id.view_tree_lifecycle_owner, buVar);
        View view2 = this.F;
        bu buVar2 = this.N;
        oy.j(view2, "<this>");
        view2.setTag(androidx.lifecycle.viewmodel.R$id.view_tree_view_model_store_owner, buVar2);
        View view3 = this.F;
        bu buVar3 = this.N;
        oy.j(view3, "<this>");
        view3.setTag(androidx.savedstate.R$id.view_tree_saved_state_registry_owner, buVar3);
        this.O.i(this.N);
    }

    public final void v() {
        this.D = true;
        for (jt jtVar : this.u.c.B()) {
            if (jtVar != null) {
                jtVar.v();
            }
        }
    }

    public final void w() {
        for (jt jtVar : this.u.c.B()) {
            if (jtVar != null) {
                jtVar.w();
            }
        }
    }

    public final void x() {
        for (jt jtVar : this.u.c.B()) {
            if (jtVar != null) {
                jtVar.x();
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
        View view = this.F;
        if (view != null) {
            return view;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }
}
