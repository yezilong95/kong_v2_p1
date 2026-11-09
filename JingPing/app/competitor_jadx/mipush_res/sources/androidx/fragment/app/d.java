package androidx.fragment.app;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.R$id;
import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;
import defpackage.b00;
import defpackage.f51;
import defpackage.ga0;
import defpackage.h00;
import defpackage.i6;
import defpackage.j00;
import defpackage.jr0;
import defpackage.k00;
import defpackage.l00;
import defpackage.lw0;
import defpackage.n00;
import defpackage.nw0;
import defpackage.on;
import defpackage.ou;
import defpackage.qg;
import defpackage.r41;
import defpackage.r51;
import defpackage.rc;
import defpackage.vz;
import defpackage.wz;
import defpackage.ym0;
import defpackage.yx0;
import defpackage.z8;
import defpackage.z80;
import defpackage.zn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    public final i6 a;
    public final ou b;
    public final vz c;
    public boolean d = false;
    public int e = -1;

    public d(i6 i6Var, ou ouVar, vz vzVar) {
        this.a = i6Var;
        this.b = ouVar;
        this.c = vzVar;
    }

    public final void a() {
        boolean zB = c.B(3);
        vz vzVar = this.c;
        if (zB) {
            Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + vzVar);
        }
        Bundle bundle = vzVar.F;
        vzVar.X.G();
        vzVar.E = 3;
        vzVar.g0 = true;
        if (c.B(3)) {
            Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + vzVar);
        }
        View view = vzVar.i0;
        if (view != null) {
            Bundle bundle2 = vzVar.F;
            SparseArray<Parcelable> sparseArray = vzVar.G;
            if (sparseArray != null) {
                view.restoreHierarchyState(sparseArray);
                vzVar.G = null;
            }
            if (vzVar.i0 != null) {
                vzVar.q0.G.I(vzVar.H);
                vzVar.H = null;
            }
            vzVar.g0 = false;
            vzVar.t(bundle2);
            if (!vzVar.g0) {
                throw new yx0("Fragment " + vzVar + " did not call through to super.onViewStateRestored()");
            }
            if (vzVar.i0 != null) {
                vzVar.q0.b(Lifecycle$Event.ON_CREATE);
            }
        }
        vzVar.F = null;
        h00 h00Var = vzVar.X;
        h00Var.z = false;
        h00Var.A = false;
        h00Var.G.g = false;
        h00Var.p(4);
        this.a.f(false);
    }

    public final void b() {
        View view;
        View view2;
        ou ouVar = this.b;
        ouVar.getClass();
        vz vzVar = this.c;
        ViewGroup viewGroup = vzVar.h0;
        int iIndexOfChild = -1;
        if (viewGroup != null) {
            ArrayList arrayList = (ArrayList) ouVar.F;
            int iIndexOf = arrayList.indexOf(vzVar);
            int i = iIndexOf - 1;
            while (true) {
                if (i < 0) {
                    while (true) {
                        iIndexOf++;
                        if (iIndexOf >= arrayList.size()) {
                            break;
                        }
                        vz vzVar2 = (vz) arrayList.get(iIndexOf);
                        if (vzVar2.h0 == viewGroup && (view = vzVar2.i0) != null) {
                            iIndexOfChild = viewGroup.indexOfChild(view);
                            break;
                        }
                    }
                } else {
                    vz vzVar3 = (vz) arrayList.get(i);
                    if (vzVar3.h0 == viewGroup && (view2 = vzVar3.i0) != null) {
                        iIndexOfChild = viewGroup.indexOfChild(view2) + 1;
                        break;
                    }
                    i--;
                }
            }
        }
        vzVar.h0.addView(vzVar.i0, iIndexOfChild);
    }

    public final void c() {
        boolean zB = c.B(3);
        vz vzVar = this.c;
        if (zB) {
            Log.d("FragmentManager", "moveto ATTACHED: " + vzVar);
        }
        vz vzVar2 = vzVar.K;
        ou ouVar = this.b;
        d dVar = null;
        if (vzVar2 != null) {
            d dVar2 = (d) ((HashMap) ouVar.G).get(vzVar2.I);
            if (dVar2 == null) {
                throw new IllegalStateException("Fragment " + vzVar + " declared target fragment " + vzVar.K + " that does not belong to this FragmentManager!");
            }
            vzVar.L = vzVar.K.I;
            vzVar.K = null;
            dVar = dVar2;
        } else {
            String str = vzVar.L;
            if (str != null && (dVar = (d) ((HashMap) ouVar.G).get(str)) == null) {
                StringBuilder sb = new StringBuilder("Fragment ");
                sb.append(vzVar);
                sb.append(" declared target fragment ");
                throw new IllegalStateException(zn.o(sb, vzVar.L, " that does not belong to this FragmentManager!"));
            }
        }
        if (dVar != null) {
            dVar.k();
        }
        c cVar = vzVar.V;
        vzVar.W = cVar.o;
        vzVar.Y = cVar.q;
        i6 i6Var = this.a;
        i6Var.o(false);
        ArrayList arrayList = vzVar.t0;
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            throw zn.i(it);
        }
        arrayList.clear();
        vzVar.X.b(vzVar.W, vzVar.b(), vzVar);
        vzVar.E = 0;
        vzVar.g0 = false;
        vzVar.k(vzVar.W.c0);
        if (!vzVar.g0) {
            throw new yx0("Fragment " + vzVar + " did not call through to super.onAttach()");
        }
        Iterator it2 = vzVar.V.m.iterator();
        while (it2.hasNext()) {
            ((k00) it2.next()).b();
        }
        h00 h00Var = vzVar.X;
        h00Var.z = false;
        h00Var.A = false;
        h00Var.G.g = false;
        h00Var.p(0);
        i6Var.h(false);
    }

    public final int d() {
        nw0 nw0Var;
        vz vzVar = this.c;
        if (vzVar.V == null) {
            return vzVar.E;
        }
        int iMin = this.e;
        int i = l00.a[vzVar.o0.ordinal()];
        if (i != 1) {
            iMin = i != 2 ? i != 3 ? i != 4 ? Math.min(iMin, -1) : Math.min(iMin, 0) : Math.min(iMin, 1) : Math.min(iMin, 5);
        }
        if (vzVar.Q) {
            if (vzVar.R) {
                iMin = Math.max(this.e, 2);
                View view = vzVar.i0;
                if (view != null && view.getParent() == null) {
                    iMin = Math.min(iMin, 2);
                }
            } else {
                iMin = this.e < 4 ? Math.min(iMin, vzVar.E) : Math.min(iMin, 1);
            }
        }
        if (!vzVar.O) {
            iMin = Math.min(iMin, 1);
        }
        ViewGroup viewGroup = vzVar.h0;
        if (viewGroup != null) {
            on onVarF = on.f(viewGroup, vzVar.i().z());
            onVarF.getClass();
            nw0 nw0VarD = onVarF.d(vzVar);
            i = nw0VarD != null ? nw0VarD.b : 0;
            Iterator it = onVarF.c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    nw0Var = null;
                    break;
                }
                nw0Var = (nw0) it.next();
                if (nw0Var.c.equals(vzVar) && !nw0Var.f) {
                    break;
                }
            }
            if (nw0Var != null && (i == 0 || i == 1)) {
                i = nw0Var.b;
            }
        }
        if (i == 2) {
            iMin = Math.min(iMin, 6);
        } else if (i == 3) {
            iMin = Math.max(iMin, 3);
        } else if (vzVar.P) {
            iMin = vzVar.U > 0 ? Math.min(iMin, 1) : Math.min(iMin, -1);
        }
        if (vzVar.j0 && vzVar.E < 5) {
            iMin = Math.min(iMin, 4);
        }
        if (c.B(2)) {
            Log.v("FragmentManager", "computeExpectedState() of " + iMin + " for " + vzVar);
        }
        return iMin;
    }

    public final void e() {
        Parcelable parcelable;
        boolean zB = c.B(3);
        vz vzVar = this.c;
        if (zB) {
            Log.d("FragmentManager", "moveto CREATED: " + vzVar);
        }
        if (vzVar.n0) {
            Bundle bundle = vzVar.F;
            if (bundle != null && (parcelable = bundle.getParcelable("android:support:fragments")) != null) {
                vzVar.X.L(parcelable);
                h00 h00Var = vzVar.X;
                h00Var.z = false;
                h00Var.A = false;
                h00Var.G.g = false;
                h00Var.p(1);
            }
            vzVar.E = 1;
            return;
        }
        i6 i6Var = this.a;
        i6Var.q(false);
        Bundle bundle2 = vzVar.F;
        vzVar.X.G();
        vzVar.E = 1;
        vzVar.g0 = false;
        vzVar.p0.a(new ym0(2, vzVar));
        vzVar.s0.I(bundle2);
        vzVar.l(bundle2);
        vzVar.n0 = true;
        if (vzVar.g0) {
            vzVar.p0.e(Lifecycle$Event.ON_CREATE);
            i6Var.i(false);
        } else {
            throw new yx0("Fragment " + vzVar + " did not call through to super.onCreate()");
        }
    }

    public final void f() {
        String resourceName;
        int i = 2;
        vz vzVar = this.c;
        if (vzVar.Q) {
            return;
        }
        if (c.B(3)) {
            Log.d("FragmentManager", "moveto CREATE_VIEW: " + vzVar);
        }
        LayoutInflater layoutInflaterP = vzVar.p(vzVar.F);
        ViewGroup viewGroup = vzVar.h0;
        if (viewGroup == null) {
            int i2 = vzVar.a0;
            if (i2 == 0) {
                viewGroup = null;
            } else {
                if (i2 == -1) {
                    throw new IllegalArgumentException("Cannot create fragment " + vzVar + " for a container view with no id");
                }
                viewGroup = (ViewGroup) vzVar.V.p.W(i2);
                if (viewGroup == null && !vzVar.S) {
                    try {
                        resourceName = vzVar.y().getResources().getResourceName(vzVar.a0);
                    } catch (Resources.NotFoundException unused) {
                        resourceName = "unknown";
                    }
                    throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(vzVar.a0) + " (" + resourceName + ") for fragment " + vzVar);
                }
            }
        }
        vzVar.h0 = viewGroup;
        vzVar.u(layoutInflaterP, viewGroup, vzVar.F);
        View view = vzVar.i0;
        if (view != null) {
            view.setSaveFromParentEnabled(false);
            vzVar.i0.setTag(R$id.fragment_container_view_tag, vzVar);
            if (viewGroup != null) {
                b();
            }
            if (vzVar.c0) {
                vzVar.i0.setVisibility(8);
            }
            View view2 = vzVar.i0;
            WeakHashMap weakHashMap = f51.a;
            if (view2.isAttachedToWindow()) {
                r41.c(vzVar.i0);
            } else {
                View view3 = vzVar.i0;
                view3.addOnAttachStateChangeListener(new rc(i, view3));
            }
            vzVar.X.p(2);
            this.a.v(false);
            int visibility = vzVar.i0.getVisibility();
            vzVar.e().j = vzVar.i0.getAlpha();
            if (vzVar.h0 != null && visibility == 0) {
                View viewFindFocus = vzVar.i0.findFocus();
                if (viewFindFocus != null) {
                    vzVar.e().k = viewFindFocus;
                    if (c.B(2)) {
                        Log.v("FragmentManager", "requestFocus: Saved focused view " + viewFindFocus + " for Fragment " + vzVar);
                    }
                }
                vzVar.i0.setAlpha(0.0f);
            }
        }
        vzVar.E = 2;
    }

    public final void g() {
        boolean z;
        vz vzVarS;
        boolean zB = c.B(3);
        vz vzVar = this.c;
        if (zB) {
            Log.d("FragmentManager", "movefrom CREATED: " + vzVar);
        }
        boolean z2 = vzVar.P && vzVar.U <= 0;
        ou ouVar = this.b;
        if (!z2) {
            j00 j00Var = (j00) ouVar.H;
            if (!((j00Var.b.containsKey(vzVar.I) && j00Var.e) ? j00Var.f : true)) {
                String str = vzVar.L;
                if (str != null && (vzVarS = ouVar.s(str)) != null && vzVarS.e0) {
                    vzVar.K = vzVarS;
                }
                vzVar.E = 0;
                return;
            }
        }
        wz wzVar = vzVar.W;
        if (wzVar != null) {
            z = ((j00) ouVar.H).f;
        } else {
            z = wzVar.c0 != null ? !r7.isChangingConfigurations() : true;
        }
        if (z2 || z) {
            j00 j00Var2 = (j00) ouVar.H;
            j00Var2.getClass();
            if (c.B(3)) {
                Log.d("FragmentManager", "Clearing non-config state for " + vzVar);
            }
            HashMap map = j00Var2.c;
            j00 j00Var3 = (j00) map.get(vzVar.I);
            if (j00Var3 != null) {
                j00Var3.b();
                map.remove(vzVar.I);
            }
            HashMap map2 = j00Var2.d;
            r51 r51Var = (r51) map2.get(vzVar.I);
            if (r51Var != null) {
                r51Var.a();
                map2.remove(vzVar.I);
            }
        }
        vzVar.X.k();
        vzVar.p0.e(Lifecycle$Event.ON_DESTROY);
        vzVar.E = 0;
        vzVar.n0 = false;
        vzVar.g0 = true;
        this.a.k(false);
        for (d dVar : ouVar.v()) {
            if (dVar != null) {
                String str2 = vzVar.I;
                vz vzVar2 = dVar.c;
                if (str2.equals(vzVar2.L)) {
                    vzVar2.K = vzVar;
                    vzVar2.L = null;
                }
            }
        }
        String str3 = vzVar.L;
        if (str3 != null) {
            vzVar.K = ouVar.s(str3);
        }
        ouVar.M(this);
    }

    public final void h() {
        View view;
        boolean zB = c.B(3);
        vz vzVar = this.c;
        if (zB) {
            Log.d("FragmentManager", "movefrom CREATE_VIEW: " + vzVar);
        }
        ViewGroup viewGroup = vzVar.h0;
        if (viewGroup != null && (view = vzVar.i0) != null) {
            viewGroup.removeView(view);
        }
        vzVar.X.p(1);
        if (vzVar.i0 != null) {
            n00 n00Var = vzVar.q0;
            n00Var.e();
            if (n00Var.F.c.isAtLeast(Lifecycle$State.CREATED)) {
                vzVar.q0.b(Lifecycle$Event.ON_DESTROY);
            }
        }
        vzVar.E = 1;
        vzVar.g0 = false;
        vzVar.n();
        if (!vzVar.g0) {
            throw new yx0("Fragment " + vzVar + " did not call through to super.onDestroyView()");
        }
        lw0 lw0Var = ((ga0) new z8(vzVar.d(), ga0.c).y(ga0.class)).b;
        if (lw0Var.G > 0) {
            lw0Var.F[0].getClass();
            throw new ClassCastException();
        }
        vzVar.T = false;
        this.a.w(false);
        vzVar.h0 = null;
        vzVar.i0 = null;
        vzVar.q0 = null;
        vzVar.r0.g(null);
        vzVar.R = false;
    }

    public final void i() {
        boolean zB = c.B(3);
        vz vzVar = this.c;
        if (zB) {
            Log.d("FragmentManager", "movefrom ATTACHED: " + vzVar);
        }
        vzVar.E = -1;
        vzVar.g0 = false;
        vzVar.o();
        if (!vzVar.g0) {
            throw new yx0("Fragment " + vzVar + " did not call through to super.onDetach()");
        }
        h00 h00Var = vzVar.X;
        if (!h00Var.B) {
            h00Var.k();
            vzVar.X = new h00();
        }
        this.a.m(false);
        vzVar.E = -1;
        vzVar.W = null;
        vzVar.Y = null;
        vzVar.V = null;
        if (!vzVar.P || vzVar.U > 0) {
            j00 j00Var = (j00) this.b.H;
            if (!((j00Var.b.containsKey(vzVar.I) && j00Var.e) ? j00Var.f : true)) {
                return;
            }
        }
        if (c.B(3)) {
            Log.d("FragmentManager", "initState called for fragment: " + vzVar);
        }
        vzVar.p0 = new z80(vzVar);
        vzVar.s0 = new i6(new jr0(vzVar, new qg(vzVar, 2)));
        vzVar.I = UUID.randomUUID().toString();
        vzVar.O = false;
        vzVar.P = false;
        vzVar.Q = false;
        vzVar.R = false;
        vzVar.S = false;
        vzVar.U = 0;
        vzVar.V = null;
        vzVar.X = new h00();
        vzVar.W = null;
        vzVar.Z = 0;
        vzVar.a0 = 0;
        vzVar.b0 = null;
        vzVar.c0 = false;
        vzVar.d0 = false;
    }

    public final void j() {
        vz vzVar = this.c;
        if (vzVar.Q && vzVar.R && !vzVar.T) {
            if (c.B(3)) {
                Log.d("FragmentManager", "moveto CREATE_VIEW: " + vzVar);
            }
            vzVar.u(vzVar.p(vzVar.F), null, vzVar.F);
            View view = vzVar.i0;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                vzVar.i0.setTag(R$id.fragment_container_view_tag, vzVar);
                if (vzVar.c0) {
                    vzVar.i0.setVisibility(8);
                }
                vzVar.X.p(2);
                this.a.v(false);
                vzVar.E = 2;
            }
        }
    }

    public final void k() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        ViewGroup viewGroup3;
        boolean z = this.d;
        vz vzVar = this.c;
        if (z) {
            if (c.B(2)) {
                Log.v("FragmentManager", "Ignoring re-entrant call to moveToExpectedState() for " + vzVar);
                return;
            }
            return;
        }
        try {
            this.d = true;
            while (true) {
                int iD = d();
                int i = vzVar.E;
                if (iD == i) {
                    if (vzVar.m0) {
                        if (vzVar.i0 != null && (viewGroup = vzVar.h0) != null) {
                            on onVarF = on.f(viewGroup, vzVar.i().z());
                            if (vzVar.c0) {
                                onVarF.getClass();
                                if (c.B(2)) {
                                    Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + vzVar);
                                }
                                onVarF.a(3, 1, this);
                            } else {
                                onVarF.getClass();
                                if (c.B(2)) {
                                    Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + vzVar);
                                }
                                onVarF.a(2, 1, this);
                            }
                        }
                        c cVar = vzVar.V;
                        if (cVar != null && vzVar.O && c.C(vzVar)) {
                            cVar.y = true;
                        }
                        vzVar.m0 = false;
                    }
                    this.d = false;
                    return;
                }
                if (iD <= i) {
                    switch (i - 1) {
                        case -1:
                            i();
                            break;
                        case 0:
                            g();
                            break;
                        case 1:
                            h();
                            vzVar.E = 1;
                            break;
                        case 2:
                            vzVar.R = false;
                            vzVar.E = 2;
                            break;
                        case 3:
                            if (c.B(3)) {
                                Log.d("FragmentManager", "movefrom ACTIVITY_CREATED: " + vzVar);
                            }
                            if (vzVar.i0 != null && vzVar.G == null) {
                                o();
                            }
                            if (vzVar.i0 != null && (viewGroup3 = vzVar.h0) != null) {
                                on onVarF2 = on.f(viewGroup3, vzVar.i().z());
                                onVarF2.getClass();
                                if (c.B(2)) {
                                    Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + vzVar);
                                }
                                onVarF2.a(1, 3, this);
                            }
                            vzVar.E = 3;
                            break;
                        case 4:
                            q();
                            break;
                        case 5:
                            vzVar.E = 5;
                            break;
                        case 6:
                            l();
                            break;
                    }
                } else {
                    switch (i + 1) {
                        case 0:
                            c();
                            break;
                        case 1:
                            e();
                            break;
                        case 2:
                            j();
                            f();
                            break;
                        case 3:
                            a();
                            break;
                        case 4:
                            if (vzVar.i0 != null && (viewGroup2 = vzVar.h0) != null) {
                                on onVarF3 = on.f(viewGroup2, vzVar.i().z());
                                int iB = zn.b(vzVar.i0.getVisibility());
                                onVarF3.getClass();
                                if (c.B(2)) {
                                    Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + vzVar);
                                }
                                onVarF3.a(iB, 2, this);
                            }
                            vzVar.E = 4;
                            break;
                        case 5:
                            p();
                            break;
                        case 6:
                            vzVar.E = 6;
                            break;
                        case 7:
                            n();
                            break;
                    }
                }
            }
        } catch (Throwable th) {
            this.d = false;
            throw th;
        }
    }

    public final void l() {
        boolean zB = c.B(3);
        vz vzVar = this.c;
        if (zB) {
            Log.d("FragmentManager", "movefrom RESUMED: " + vzVar);
        }
        vzVar.X.p(5);
        if (vzVar.i0 != null) {
            vzVar.q0.b(Lifecycle$Event.ON_PAUSE);
        }
        vzVar.p0.e(Lifecycle$Event.ON_PAUSE);
        vzVar.E = 6;
        vzVar.g0 = true;
        this.a.n(false);
    }

    public final void m(ClassLoader classLoader) {
        vz vzVar = this.c;
        Bundle bundle = vzVar.F;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        vzVar.G = vzVar.F.getSparseParcelableArray("android:view_state");
        vzVar.H = vzVar.F.getBundle("android:view_registry_state");
        String string = vzVar.F.getString("android:target_state");
        vzVar.L = string;
        if (string != null) {
            vzVar.M = vzVar.F.getInt("android:target_req_state", 0);
        }
        boolean z = vzVar.F.getBoolean("android:user_visible_hint", true);
        vzVar.k0 = z;
        if (z) {
            return;
        }
        vzVar.j0 = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void n() {
        /*
            r7 = this;
            r0 = 3
            boolean r0 = androidx.fragment.app.c.B(r0)
            java.lang.String r1 = "FragmentManager"
            vz r2 = r7.c
            if (r0 == 0) goto L1c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "moveto RESUMED: "
            r0.<init>(r3)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0)
        L1c:
            sz r0 = r2.l0
            r3 = 0
            if (r0 != 0) goto L23
            r0 = r3
            goto L25
        L23:
            android.view.View r0 = r0.k
        L25:
            if (r0 == 0) goto L7d
            android.view.View r4 = r2.i0
            if (r0 != r4) goto L2c
            goto L36
        L2c:
            android.view.ViewParent r4 = r0.getParent()
        L30:
            if (r4 == 0) goto L7d
            android.view.View r5 = r2.i0
            if (r4 != r5) goto L78
        L36:
            boolean r4 = r0.requestFocus()
            r5 = 2
            boolean r5 = androidx.fragment.app.c.B(r5)
            if (r5 == 0) goto L7d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "requestFocus: Restoring focused view "
            r5.<init>(r6)
            r5.append(r0)
            java.lang.String r0 = " "
            r5.append(r0)
            if (r4 == 0) goto L55
            java.lang.String r0 = "succeeded"
            goto L57
        L55:
            java.lang.String r0 = "failed"
        L57:
            r5.append(r0)
            java.lang.String r0 = " on Fragment "
            r5.append(r0)
            r5.append(r2)
            java.lang.String r0 = " resulting in focused view "
            r5.append(r0)
            android.view.View r0 = r2.i0
            android.view.View r0 = r0.findFocus()
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            android.util.Log.v(r1, r0)
            goto L7d
        L78:
            android.view.ViewParent r4 = r4.getParent()
            goto L30
        L7d:
            sz r0 = r2.e()
            r0.k = r3
            h00 r0 = r2.X
            r0.G()
            h00 r0 = r2.X
            r1 = 1
            r0.t(r1)
            r0 = 7
            r2.E = r0
            r2.g0 = r1
            z80 r1 = r2.p0
            androidx.lifecycle.Lifecycle$Event r4 = androidx.lifecycle.Lifecycle$Event.ON_RESUME
            r1.e(r4)
            android.view.View r1 = r2.i0
            if (r1 == 0) goto La5
            n00 r1 = r2.q0
            z80 r1 = r1.F
            r1.e(r4)
        La5:
            h00 r1 = r2.X
            r4 = 0
            r1.z = r4
            r1.A = r4
            j00 r5 = r1.G
            r5.g = r4
            r1.p(r0)
            i6 r0 = r7.a
            r0.r(r4)
            r2.F = r3
            r2.G = r3
            r2.H = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.d.n():void");
    }

    public final void o() {
        vz vzVar = this.c;
        if (vzVar.i0 == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        vzVar.i0.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            vzVar.G = sparseArray;
        }
        Bundle bundle = new Bundle();
        vzVar.q0.G.J(bundle);
        if (bundle.isEmpty()) {
            return;
        }
        vzVar.H = bundle;
    }

    public final void p() {
        boolean zB = c.B(3);
        vz vzVar = this.c;
        if (zB) {
            Log.d("FragmentManager", "moveto STARTED: " + vzVar);
        }
        vzVar.X.G();
        vzVar.X.t(true);
        vzVar.E = 5;
        vzVar.g0 = false;
        vzVar.r();
        if (!vzVar.g0) {
            throw new yx0("Fragment " + vzVar + " did not call through to super.onStart()");
        }
        z80 z80Var = vzVar.p0;
        Lifecycle$Event lifecycle$Event = Lifecycle$Event.ON_START;
        z80Var.e(lifecycle$Event);
        if (vzVar.i0 != null) {
            vzVar.q0.F.e(lifecycle$Event);
        }
        h00 h00Var = vzVar.X;
        h00Var.z = false;
        h00Var.A = false;
        h00Var.G.g = false;
        h00Var.p(5);
        this.a.t(false);
    }

    public final void q() {
        boolean zB = c.B(3);
        vz vzVar = this.c;
        if (zB) {
            Log.d("FragmentManager", "movefrom STARTED: " + vzVar);
        }
        h00 h00Var = vzVar.X;
        h00Var.A = true;
        h00Var.G.g = true;
        h00Var.p(4);
        if (vzVar.i0 != null) {
            vzVar.q0.b(Lifecycle$Event.ON_STOP);
        }
        vzVar.p0.e(Lifecycle$Event.ON_STOP);
        vzVar.E = 4;
        vzVar.g0 = false;
        vzVar.s();
        if (vzVar.g0) {
            this.a.u(false);
            return;
        }
        throw new yx0("Fragment " + vzVar + " did not call through to super.onStop()");
    }

    public d(i6 i6Var, ou ouVar, ClassLoader classLoader, b00 b00Var, FragmentState fragmentState) {
        this.a = i6Var;
        this.b = ouVar;
        vz vzVarA = b00Var.a(fragmentState.E);
        this.c = vzVarA;
        Bundle bundle = fragmentState.N;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        vzVarA.B(bundle);
        vzVarA.I = fragmentState.F;
        vzVarA.Q = fragmentState.G;
        vzVarA.S = true;
        vzVarA.Z = fragmentState.H;
        vzVarA.a0 = fragmentState.I;
        vzVarA.b0 = fragmentState.J;
        vzVarA.e0 = fragmentState.K;
        vzVarA.P = fragmentState.L;
        vzVarA.d0 = fragmentState.M;
        vzVarA.c0 = fragmentState.O;
        vzVarA.o0 = Lifecycle$State.values()[fragmentState.P];
        Bundle bundle2 = fragmentState.Q;
        if (bundle2 != null) {
            vzVarA.F = bundle2;
        } else {
            vzVarA.F = new Bundle();
        }
        if (c.B(2)) {
            Log.v("FragmentManager", "Instantiated fragment " + vzVarA);
        }
    }

    public d(i6 i6Var, ou ouVar, vz vzVar, FragmentState fragmentState) {
        this.a = i6Var;
        this.b = ouVar;
        this.c = vzVar;
        vzVar.G = null;
        vzVar.H = null;
        vzVar.U = 0;
        vzVar.R = false;
        vzVar.O = false;
        vz vzVar2 = vzVar.K;
        vzVar.L = vzVar2 != null ? vzVar2.I : null;
        vzVar.K = null;
        Bundle bundle = fragmentState.Q;
        if (bundle != null) {
            vzVar.F = bundle;
        } else {
            vzVar.F = new Bundle();
        }
    }
}
