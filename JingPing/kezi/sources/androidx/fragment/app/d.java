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
import defpackage.bu;
import defpackage.c30;
import defpackage.dd0;
import defpackage.e5;
import defpackage.hk;
import defpackage.jr0;
import defpackage.jt;
import defpackage.kl0;
import defpackage.ks0;
import defpackage.kt;
import defpackage.o70;
import defpackage.po;
import defpackage.pt;
import defpackage.ug0;
import defpackage.vj;
import defpackage.vt;
import defpackage.w10;
import defpackage.wa;
import defpackage.we;
import defpackage.xj0;
import defpackage.xr0;
import defpackage.xt;
import defpackage.yt;
import defpackage.zj0;
import defpackage.zt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    public final e5 a;
    public final po b;
    public final jt c;
    public boolean d = false;
    public int e = -1;

    public d(e5 e5Var, po poVar, jt jtVar) {
        this.a = e5Var;
        this.b = poVar;
        this.c = jtVar;
    }

    public final void a() {
        boolean zC = c.C(3);
        jt jtVar = this.c;
        if (zC) {
            Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + jtVar);
        }
        Bundle bundle = jtVar.c;
        jtVar.u.H();
        jtVar.b = 3;
        jtVar.D = true;
        if (c.C(3)) {
            Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + jtVar);
        }
        View view = jtVar.F;
        if (view != null) {
            Bundle bundle2 = jtVar.c;
            SparseArray<Parcelable> sparseArray = jtVar.d;
            if (sparseArray != null) {
                view.restoreHierarchyState(sparseArray);
                jtVar.d = null;
            }
            if (jtVar.F != null) {
                jtVar.N.d.J(jtVar.e);
                jtVar.e = null;
            }
            jtVar.D = false;
            jtVar.t(bundle2);
            if (!jtVar.D) {
                throw new kl0("Fragment " + jtVar + " did not call through to super.onViewStateRestored()");
            }
            if (jtVar.F != null) {
                jtVar.N.c(Lifecycle$Event.ON_CREATE);
            }
        }
        jtVar.c = null;
        vt vtVar = jtVar.u;
        vtVar.z = false;
        vtVar.A = false;
        vtVar.G.g = false;
        vtVar.p(4);
        this.a.i(false);
    }

    public final void b() {
        View view;
        View view2;
        po poVar = this.b;
        poVar.getClass();
        jt jtVar = this.c;
        ViewGroup viewGroup = jtVar.E;
        int iIndexOfChild = -1;
        if (viewGroup != null) {
            ArrayList arrayList = (ArrayList) poVar.c;
            int iIndexOf = arrayList.indexOf(jtVar);
            int i = iIndexOf - 1;
            while (true) {
                if (i < 0) {
                    while (true) {
                        iIndexOf++;
                        if (iIndexOf >= arrayList.size()) {
                            break;
                        }
                        jt jtVar2 = (jt) arrayList.get(iIndexOf);
                        if (jtVar2.E == viewGroup && (view = jtVar2.F) != null) {
                            iIndexOfChild = viewGroup.indexOfChild(view);
                            break;
                        }
                    }
                } else {
                    jt jtVar3 = (jt) arrayList.get(i);
                    if (jtVar3.E == viewGroup && (view2 = jtVar3.F) != null) {
                        iIndexOfChild = viewGroup.indexOfChild(view2) + 1;
                        break;
                    }
                    i--;
                }
            }
        }
        jtVar.E.addView(jtVar.F, iIndexOfChild);
    }

    public final void c() {
        boolean zC = c.C(3);
        jt jtVar = this.c;
        if (zC) {
            Log.d("FragmentManager", "moveto ATTACHED: " + jtVar);
        }
        jt jtVar2 = jtVar.h;
        po poVar = this.b;
        d dVar = null;
        if (jtVar2 != null) {
            d dVar2 = (d) ((HashMap) poVar.d).get(jtVar2.f);
            if (dVar2 == null) {
                throw new IllegalStateException("Fragment " + jtVar + " declared target fragment " + jtVar.h + " that does not belong to this FragmentManager!");
            }
            jtVar.i = jtVar.h.f;
            jtVar.h = null;
            dVar = dVar2;
        } else {
            String str = jtVar.i;
            if (str != null && (dVar = (d) ((HashMap) poVar.d).get(str)) == null) {
                StringBuilder sb = new StringBuilder("Fragment ");
                sb.append(jtVar);
                sb.append(" declared target fragment ");
                throw new IllegalStateException(hk.k(sb, jtVar.i, " that does not belong to this FragmentManager!"));
            }
        }
        if (dVar != null) {
            dVar.k();
        }
        c cVar = jtVar.s;
        jtVar.t = cVar.o;
        jtVar.v = cVar.q;
        e5 e5Var = this.a;
        e5Var.o(false);
        ArrayList arrayList = jtVar.Q;
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            throw hk.e(it);
        }
        arrayList.clear();
        jtVar.u.b(jtVar.t, jtVar.c(), jtVar);
        jtVar.b = 0;
        jtVar.D = false;
        jtVar.k(jtVar.t.u);
        if (!jtVar.D) {
            throw new kl0("Fragment " + jtVar + " did not call through to super.onAttach()");
        }
        Iterator it2 = jtVar.s.m.iterator();
        while (it2.hasNext()) {
            ((yt) it2.next()).a();
        }
        vt vtVar = jtVar.u;
        vtVar.z = false;
        vtVar.A = false;
        vtVar.G.g = false;
        vtVar.p(0);
        e5Var.j(false);
    }

    public final int d() {
        zj0 zj0Var;
        jt jtVar = this.c;
        if (jtVar.s == null) {
            return jtVar.b;
        }
        int iMin = this.e;
        int i = zt.a[jtVar.L.ordinal()];
        if (i != 1) {
            iMin = i != 2 ? i != 3 ? i != 4 ? Math.min(iMin, -1) : Math.min(iMin, 0) : Math.min(iMin, 1) : Math.min(iMin, 5);
        }
        if (jtVar.n) {
            if (jtVar.o) {
                iMin = Math.max(this.e, 2);
                View view = jtVar.F;
                if (view != null && view.getParent() == null) {
                    iMin = Math.min(iMin, 2);
                }
            } else {
                iMin = this.e < 4 ? Math.min(iMin, jtVar.b) : Math.min(iMin, 1);
            }
        }
        if (!jtVar.l) {
            iMin = Math.min(iMin, 1);
        }
        ViewGroup viewGroup = jtVar.E;
        if (viewGroup != null) {
            vj vjVarF = vj.f(viewGroup, jtVar.i().A());
            vjVarF.getClass();
            zj0 zj0VarD = vjVarF.d(jtVar);
            i = zj0VarD != null ? zj0VarD.b : 0;
            Iterator it = vjVarF.c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    zj0Var = null;
                    break;
                }
                zj0Var = (zj0) it.next();
                if (zj0Var.c.equals(jtVar) && !zj0Var.f) {
                    break;
                }
            }
            if (zj0Var != null && (i == 0 || i == 1)) {
                i = zj0Var.b;
            }
        }
        if (i == 2) {
            iMin = Math.min(iMin, 6);
        } else if (i == 3) {
            iMin = Math.max(iMin, 3);
        } else if (jtVar.m) {
            iMin = jtVar.r > 0 ? Math.min(iMin, 1) : Math.min(iMin, -1);
        }
        if (jtVar.G && jtVar.b < 5) {
            iMin = Math.min(iMin, 4);
        }
        if (c.C(2)) {
            Log.v("FragmentManager", "computeExpectedState() of " + iMin + " for " + jtVar);
        }
        return iMin;
    }

    public final void e() {
        Parcelable parcelable;
        boolean zC = c.C(3);
        jt jtVar = this.c;
        if (zC) {
            Log.d("FragmentManager", "moveto CREATED: " + jtVar);
        }
        if (jtVar.K) {
            Bundle bundle = jtVar.c;
            if (bundle != null && (parcelable = bundle.getParcelable("android:support:fragments")) != null) {
                jtVar.u.M(parcelable);
                vt vtVar = jtVar.u;
                vtVar.z = false;
                vtVar.A = false;
                vtVar.G.g = false;
                vtVar.p(1);
            }
            jtVar.b = 1;
            return;
        }
        e5 e5Var = this.a;
        e5Var.p(false);
        Bundle bundle2 = jtVar.c;
        jtVar.u.H();
        jtVar.b = 1;
        jtVar.D = false;
        jtVar.M.a(new dd0(2, jtVar));
        jtVar.P.J(bundle2);
        jtVar.l(bundle2);
        jtVar.K = true;
        if (jtVar.D) {
            jtVar.M.e(Lifecycle$Event.ON_CREATE);
            e5Var.k(false);
        } else {
            throw new kl0("Fragment " + jtVar + " did not call through to super.onCreate()");
        }
    }

    public final void f() {
        String resourceName;
        int i = 2;
        jt jtVar = this.c;
        if (jtVar.n) {
            return;
        }
        if (c.C(3)) {
            Log.d("FragmentManager", "moveto CREATE_VIEW: " + jtVar);
        }
        LayoutInflater layoutInflaterP = jtVar.p(jtVar.c);
        ViewGroup viewGroup = jtVar.E;
        if (viewGroup == null) {
            int i2 = jtVar.x;
            if (i2 == 0) {
                viewGroup = null;
            } else {
                if (i2 == -1) {
                    throw new IllegalArgumentException("Cannot create fragment " + jtVar + " for a container view with no id");
                }
                viewGroup = (ViewGroup) jtVar.s.p.E(i2);
                if (viewGroup == null && !jtVar.p) {
                    try {
                        resourceName = jtVar.y().getResources().getResourceName(jtVar.x);
                    } catch (Resources.NotFoundException unused) {
                        resourceName = "unknown";
                    }
                    throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(jtVar.x) + " (" + resourceName + ") for fragment " + jtVar);
                }
            }
        }
        jtVar.E = viewGroup;
        jtVar.u(layoutInflaterP, viewGroup, jtVar.c);
        View view = jtVar.F;
        if (view != null) {
            view.setSaveFromParentEnabled(false);
            jtVar.F.setTag(R$id.fragment_container_view_tag, jtVar);
            if (viewGroup != null) {
                b();
            }
            if (jtVar.z) {
                jtVar.F.setVisibility(8);
            }
            View view2 = jtVar.F;
            WeakHashMap weakHashMap = xr0.a;
            if (view2.isAttachedToWindow()) {
                jr0.c(jtVar.F);
            } else {
                View view3 = jtVar.F;
                view3.addOnAttachStateChangeListener(new wa(i, view3));
            }
            jtVar.u.p(2);
            this.a.v(false);
            int visibility = jtVar.F.getVisibility();
            jtVar.e().j = jtVar.F.getAlpha();
            if (jtVar.E != null && visibility == 0) {
                View viewFindFocus = jtVar.F.findFocus();
                if (viewFindFocus != null) {
                    jtVar.e().k = viewFindFocus;
                    if (c.C(2)) {
                        Log.v("FragmentManager", "requestFocus: Saved focused view " + viewFindFocus + " for Fragment " + jtVar);
                    }
                }
                jtVar.F.setAlpha(0.0f);
            }
        }
        jtVar.b = 2;
    }

    public final void g() {
        boolean z;
        jt jtVarR;
        boolean zC = c.C(3);
        jt jtVar = this.c;
        if (zC) {
            Log.d("FragmentManager", "movefrom CREATED: " + jtVar);
        }
        boolean z2 = jtVar.m && jtVar.r <= 0;
        po poVar = this.b;
        if (!z2) {
            xt xtVar = (xt) poVar.e;
            if (!((xtVar.b.containsKey(jtVar.f) && xtVar.e) ? xtVar.f : true)) {
                String str = jtVar.i;
                if (str != null && (jtVarR = poVar.r(str)) != null && jtVarR.B) {
                    jtVar.h = jtVarR;
                }
                jtVar.b = 0;
                return;
            }
        }
        kt ktVar = jtVar.t;
        if (ktVar != null) {
            z = ((xt) poVar.e).f;
        } else {
            z = ktVar.u != null ? !r7.isChangingConfigurations() : true;
        }
        if (z2 || z) {
            xt xtVar2 = (xt) poVar.e;
            xtVar2.getClass();
            if (c.C(3)) {
                Log.d("FragmentManager", "Clearing non-config state for " + jtVar);
            }
            HashMap map = xtVar2.c;
            xt xtVar3 = (xt) map.get(jtVar.f);
            if (xtVar3 != null) {
                xtVar3.b();
                map.remove(jtVar.f);
            }
            HashMap map2 = xtVar2.d;
            ks0 ks0Var = (ks0) map2.get(jtVar.f);
            if (ks0Var != null) {
                ks0Var.a();
                map2.remove(jtVar.f);
            }
        }
        jtVar.u.k();
        jtVar.M.e(Lifecycle$Event.ON_DESTROY);
        jtVar.b = 0;
        jtVar.K = false;
        jtVar.D = true;
        this.a.l(false);
        for (d dVar : poVar.t()) {
            if (dVar != null) {
                String str2 = jtVar.f;
                jt jtVar2 = dVar.c;
                if (str2.equals(jtVar2.i)) {
                    jtVar2.h = jtVar;
                    jtVar2.i = null;
                }
            }
        }
        String str3 = jtVar.i;
        if (str3 != null) {
            jtVar.h = poVar.r(str3);
        }
        poVar.K(this);
    }

    public final void h() {
        View view;
        boolean zC = c.C(3);
        jt jtVar = this.c;
        if (zC) {
            Log.d("FragmentManager", "movefrom CREATE_VIEW: " + jtVar);
        }
        ViewGroup viewGroup = jtVar.E;
        if (viewGroup != null && (view = jtVar.F) != null) {
            viewGroup.removeView(view);
        }
        jtVar.u.p(1);
        if (jtVar.F != null) {
            bu buVar = jtVar.N;
            buVar.e();
            if (buVar.c.c.isAtLeast(Lifecycle$State.CREATED)) {
                jtVar.N.c(Lifecycle$Event.ON_DESTROY);
            }
        }
        jtVar.b = 1;
        jtVar.D = false;
        jtVar.n();
        if (!jtVar.D) {
            throw new kl0("Fragment " + jtVar + " did not call through to super.onDestroyView()");
        }
        xj0 xj0Var = ((c30) new o70(jtVar.d(), c30.c).w(c30.class)).b;
        if (xj0Var.d > 0) {
            xj0Var.c[0].getClass();
            throw new ClassCastException();
        }
        jtVar.q = false;
        this.a.w(false);
        jtVar.E = null;
        jtVar.F = null;
        jtVar.N = null;
        jtVar.O.i(null);
        jtVar.o = false;
    }

    public final void i() {
        boolean zC = c.C(3);
        jt jtVar = this.c;
        if (zC) {
            Log.d("FragmentManager", "movefrom ATTACHED: " + jtVar);
        }
        jtVar.b = -1;
        jtVar.D = false;
        jtVar.o();
        if (!jtVar.D) {
            throw new kl0("Fragment " + jtVar + " did not call through to super.onDetach()");
        }
        vt vtVar = jtVar.u;
        if (!vtVar.B) {
            vtVar.k();
            jtVar.u = new vt();
        }
        this.a.m(false);
        jtVar.b = -1;
        jtVar.t = null;
        jtVar.v = null;
        jtVar.s = null;
        if (!jtVar.m || jtVar.r > 0) {
            xt xtVar = (xt) this.b.e;
            if (!((xtVar.b.containsKey(jtVar.f) && xtVar.e) ? xtVar.f : true)) {
                return;
            }
        }
        if (c.C(3)) {
            Log.d("FragmentManager", "initState called for fragment: " + jtVar);
        }
        jtVar.M = new w10(jtVar);
        jtVar.P = new e5(new ug0(jtVar, new we(jtVar, 2)));
        jtVar.f = UUID.randomUUID().toString();
        jtVar.l = false;
        jtVar.m = false;
        jtVar.n = false;
        jtVar.o = false;
        jtVar.p = false;
        jtVar.r = 0;
        jtVar.s = null;
        jtVar.u = new vt();
        jtVar.t = null;
        jtVar.w = 0;
        jtVar.x = 0;
        jtVar.y = null;
        jtVar.z = false;
        jtVar.A = false;
    }

    public final void j() {
        jt jtVar = this.c;
        if (jtVar.n && jtVar.o && !jtVar.q) {
            if (c.C(3)) {
                Log.d("FragmentManager", "moveto CREATE_VIEW: " + jtVar);
            }
            jtVar.u(jtVar.p(jtVar.c), null, jtVar.c);
            View view = jtVar.F;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                jtVar.F.setTag(R$id.fragment_container_view_tag, jtVar);
                if (jtVar.z) {
                    jtVar.F.setVisibility(8);
                }
                jtVar.u.p(2);
                this.a.v(false);
                jtVar.b = 2;
            }
        }
    }

    public final void k() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        ViewGroup viewGroup3;
        boolean z = this.d;
        jt jtVar = this.c;
        if (z) {
            if (c.C(2)) {
                Log.v("FragmentManager", "Ignoring re-entrant call to moveToExpectedState() for " + jtVar);
                return;
            }
            return;
        }
        try {
            this.d = true;
            while (true) {
                int iD = d();
                int i = jtVar.b;
                if (iD == i) {
                    if (jtVar.J) {
                        if (jtVar.F != null && (viewGroup = jtVar.E) != null) {
                            vj vjVarF = vj.f(viewGroup, jtVar.i().A());
                            if (jtVar.z) {
                                vjVarF.getClass();
                                if (c.C(2)) {
                                    Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + jtVar);
                                }
                                vjVarF.a(3, 1, this);
                            } else {
                                vjVarF.getClass();
                                if (c.C(2)) {
                                    Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + jtVar);
                                }
                                vjVarF.a(2, 1, this);
                            }
                        }
                        c cVar = jtVar.s;
                        if (cVar != null && jtVar.l && c.D(jtVar)) {
                            cVar.y = true;
                        }
                        jtVar.J = false;
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
                            jtVar.b = 1;
                            break;
                        case 2:
                            jtVar.o = false;
                            jtVar.b = 2;
                            break;
                        case 3:
                            if (c.C(3)) {
                                Log.d("FragmentManager", "movefrom ACTIVITY_CREATED: " + jtVar);
                            }
                            if (jtVar.F != null && jtVar.d == null) {
                                o();
                            }
                            if (jtVar.F != null && (viewGroup3 = jtVar.E) != null) {
                                vj vjVarF2 = vj.f(viewGroup3, jtVar.i().A());
                                vjVarF2.getClass();
                                if (c.C(2)) {
                                    Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + jtVar);
                                }
                                vjVarF2.a(1, 3, this);
                            }
                            jtVar.b = 3;
                            break;
                        case 4:
                            q();
                            break;
                        case 5:
                            jtVar.b = 5;
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
                            if (jtVar.F != null && (viewGroup2 = jtVar.E) != null) {
                                vj vjVarF3 = vj.f(viewGroup2, jtVar.i().A());
                                int iB = hk.b(jtVar.F.getVisibility());
                                vjVarF3.getClass();
                                if (c.C(2)) {
                                    Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + jtVar);
                                }
                                vjVarF3.a(iB, 2, this);
                            }
                            jtVar.b = 4;
                            break;
                        case 5:
                            p();
                            break;
                        case 6:
                            jtVar.b = 6;
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
        boolean zC = c.C(3);
        jt jtVar = this.c;
        if (zC) {
            Log.d("FragmentManager", "movefrom RESUMED: " + jtVar);
        }
        jtVar.u.p(5);
        if (jtVar.F != null) {
            jtVar.N.c(Lifecycle$Event.ON_PAUSE);
        }
        jtVar.M.e(Lifecycle$Event.ON_PAUSE);
        jtVar.b = 6;
        jtVar.D = true;
        this.a.n(false);
    }

    public final void m(ClassLoader classLoader) {
        jt jtVar = this.c;
        Bundle bundle = jtVar.c;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        jtVar.d = jtVar.c.getSparseParcelableArray("android:view_state");
        jtVar.e = jtVar.c.getBundle("android:view_registry_state");
        String string = jtVar.c.getString("android:target_state");
        jtVar.i = string;
        if (string != null) {
            jtVar.j = jtVar.c.getInt("android:target_req_state", 0);
        }
        boolean z = jtVar.c.getBoolean("android:user_visible_hint", true);
        jtVar.H = z;
        if (z) {
            return;
        }
        jtVar.G = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void n() {
        /*
            r7 = this;
            r0 = 3
            boolean r0 = androidx.fragment.app.c.C(r0)
            java.lang.String r1 = "FragmentManager"
            jt r2 = r7.c
            if (r0 == 0) goto L1c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "moveto RESUMED: "
            r0.<init>(r3)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0)
        L1c:
            ft r0 = r2.I
            r3 = 0
            if (r0 != 0) goto L23
            r0 = r3
            goto L25
        L23:
            android.view.View r0 = r0.k
        L25:
            if (r0 == 0) goto L7d
            android.view.View r4 = r2.F
            if (r0 != r4) goto L2c
            goto L36
        L2c:
            android.view.ViewParent r4 = r0.getParent()
        L30:
            if (r4 == 0) goto L7d
            android.view.View r5 = r2.F
            if (r4 != r5) goto L78
        L36:
            boolean r4 = r0.requestFocus()
            r5 = 2
            boolean r5 = androidx.fragment.app.c.C(r5)
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
            android.view.View r0 = r2.F
            android.view.View r0 = r0.findFocus()
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            android.util.Log.v(r1, r0)
            goto L7d
        L78:
            android.view.ViewParent r4 = r4.getParent()
            goto L30
        L7d:
            ft r0 = r2.e()
            r0.k = r3
            vt r0 = r2.u
            r0.H()
            vt r0 = r2.u
            r1 = 1
            r0.u(r1)
            r0 = 7
            r2.b = r0
            r2.D = r1
            w10 r1 = r2.M
            androidx.lifecycle.Lifecycle$Event r4 = androidx.lifecycle.Lifecycle$Event.ON_RESUME
            r1.e(r4)
            android.view.View r1 = r2.F
            if (r1 == 0) goto La5
            bu r1 = r2.N
            w10 r1 = r1.c
            r1.e(r4)
        La5:
            vt r1 = r2.u
            r4 = 0
            r1.z = r4
            r1.A = r4
            xt r5 = r1.G
            r5.g = r4
            r1.p(r0)
            e5 r0 = r7.a
            r0.q(r4)
            r2.c = r3
            r2.d = r3
            r2.e = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.d.n():void");
    }

    public final void o() {
        jt jtVar = this.c;
        if (jtVar.F == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        jtVar.F.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            jtVar.d = sparseArray;
        }
        Bundle bundle = new Bundle();
        jtVar.N.d.K(bundle);
        if (bundle.isEmpty()) {
            return;
        }
        jtVar.e = bundle;
    }

    public final void p() {
        boolean zC = c.C(3);
        jt jtVar = this.c;
        if (zC) {
            Log.d("FragmentManager", "moveto STARTED: " + jtVar);
        }
        jtVar.u.H();
        jtVar.u.u(true);
        jtVar.b = 5;
        jtVar.D = false;
        jtVar.r();
        if (!jtVar.D) {
            throw new kl0("Fragment " + jtVar + " did not call through to super.onStart()");
        }
        w10 w10Var = jtVar.M;
        Lifecycle$Event lifecycle$Event = Lifecycle$Event.ON_START;
        w10Var.e(lifecycle$Event);
        if (jtVar.F != null) {
            jtVar.N.c.e(lifecycle$Event);
        }
        vt vtVar = jtVar.u;
        vtVar.z = false;
        vtVar.A = false;
        vtVar.G.g = false;
        vtVar.p(5);
        this.a.s(false);
    }

    public final void q() {
        boolean zC = c.C(3);
        jt jtVar = this.c;
        if (zC) {
            Log.d("FragmentManager", "movefrom STARTED: " + jtVar);
        }
        vt vtVar = jtVar.u;
        vtVar.A = true;
        vtVar.G.g = true;
        vtVar.p(4);
        if (jtVar.F != null) {
            jtVar.N.c(Lifecycle$Event.ON_STOP);
        }
        jtVar.M.e(Lifecycle$Event.ON_STOP);
        jtVar.b = 4;
        jtVar.D = false;
        jtVar.s();
        if (jtVar.D) {
            this.a.u(false);
            return;
        }
        throw new kl0("Fragment " + jtVar + " did not call through to super.onStop()");
    }

    public d(e5 e5Var, po poVar, ClassLoader classLoader, pt ptVar, FragmentState fragmentState) {
        this.a = e5Var;
        this.b = poVar;
        jt jtVarA = ptVar.a(fragmentState.b);
        this.c = jtVarA;
        Bundle bundle = fragmentState.k;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        jtVarA.B(bundle);
        jtVarA.f = fragmentState.c;
        jtVarA.n = fragmentState.d;
        jtVarA.p = true;
        jtVarA.w = fragmentState.e;
        jtVarA.x = fragmentState.f;
        jtVarA.y = fragmentState.g;
        jtVarA.B = fragmentState.h;
        jtVarA.m = fragmentState.i;
        jtVarA.A = fragmentState.j;
        jtVarA.z = fragmentState.l;
        jtVarA.L = Lifecycle$State.values()[fragmentState.m];
        Bundle bundle2 = fragmentState.n;
        if (bundle2 != null) {
            jtVarA.c = bundle2;
        } else {
            jtVarA.c = new Bundle();
        }
        if (c.C(2)) {
            Log.v("FragmentManager", "Instantiated fragment " + jtVarA);
        }
    }

    public d(e5 e5Var, po poVar, jt jtVar, FragmentState fragmentState) {
        this.a = e5Var;
        this.b = poVar;
        this.c = jtVar;
        jtVar.d = null;
        jtVar.e = null;
        jtVar.r = 0;
        jtVar.o = false;
        jtVar.l = false;
        jt jtVar2 = jtVar.h;
        jtVar.i = jtVar2 != null ? jtVar2.f : null;
        jtVar.h = null;
        Bundle bundle = fragmentState.n;
        if (bundle != null) {
            jtVar.c = bundle;
        } else {
            jtVar.c = new Bundle();
        }
    }
}
