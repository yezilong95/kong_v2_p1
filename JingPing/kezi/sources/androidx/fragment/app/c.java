package androidx.fragment.app;

import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.R$id;
import androidx.lifecycle.Lifecycle$State;
import defpackage.au;
import defpackage.c30;
import defpackage.e5;
import defpackage.ft;
import defpackage.h2;
import defpackage.hk;
import defpackage.jt;
import defpackage.k2;
import defpackage.ka;
import defpackage.kt;
import defpackage.m30;
import defpackage.na0;
import defpackage.nt;
import defpackage.nv0;
import defpackage.o70;
import defpackage.ot;
import defpackage.oy;
import defpackage.p10;
import defpackage.pa0;
import defpackage.po;
import defpackage.pt;
import defpackage.qa0;
import defpackage.qt;
import defpackage.rt;
import defpackage.s7;
import defpackage.st;
import defpackage.u10;
import defpackage.v6;
import defpackage.vj;
import defpackage.vt;
import defpackage.w10;
import defpackage.xj0;
import defpackage.xt;
import defpackage.yo;
import defpackage.yt;
import defpackage.ze;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.uuid.Uuid;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    public boolean A;
    public boolean B;
    public boolean C;
    public ArrayList D;
    public ArrayList E;
    public ArrayList F;
    public xt G;
    public final v6 H;
    public boolean b;
    public ArrayList d;
    public ArrayList e;
    public qa0 g;
    public ArrayList k;
    public final e5 l;
    public final CopyOnWriteArrayList m;
    public int n;
    public kt o;
    public nv0 p;
    public jt q;
    public jt r;
    public final pt s;
    public final yo t;
    public k2 u;
    public k2 v;
    public k2 w;
    public ArrayDeque x;
    public boolean y;
    public boolean z;
    public final ArrayList a = new ArrayList();
    public final po c = new po(7);
    public final nt f = new nt(this);
    public final ot h = new ot(this);
    public final AtomicInteger i = new AtomicInteger();
    public final Map j = Collections.synchronizedMap(new HashMap());

    public c() {
        Collections.synchronizedMap(new HashMap());
        Collections.synchronizedMap(new HashMap());
        new yo(this);
        this.l = new e5(this);
        this.m = new CopyOnWriteArrayList();
        this.n = -1;
        this.s = new pt(this);
        this.t = new yo(3);
        this.x = new ArrayDeque();
        this.H = new v6(10, this);
    }

    public static boolean C(int i) {
        return Log.isLoggable("FragmentManager", i);
    }

    public static boolean D(jt jtVar) {
        jtVar.getClass();
        po poVar = jtVar.u.c;
        poVar.getClass();
        ArrayList<jt> arrayList = new ArrayList();
        for (d dVar : ((HashMap) poVar.d).values()) {
            if (dVar != null) {
                arrayList.add(dVar.c);
            } else {
                arrayList.add(null);
            }
        }
        boolean zD = false;
        for (jt jtVar2 : arrayList) {
            if (jtVar2 != null) {
                zD = D(jtVar2);
            }
            if (zD) {
                return true;
            }
        }
        return false;
    }

    public static boolean E(jt jtVar) {
        if (jtVar == null) {
            return true;
        }
        if (jtVar.C) {
            return jtVar.s == null || E(jtVar.v);
        }
        return false;
    }

    public static boolean F(jt jtVar) {
        if (jtVar == null) {
            return true;
        }
        c cVar = jtVar.s;
        return jtVar.equals(cVar.r) && F(cVar.q);
    }

    public static void T(jt jtVar) {
        if (C(2)) {
            Log.v("FragmentManager", "show: " + jtVar);
        }
        if (jtVar.z) {
            jtVar.z = false;
            jtVar.J = !jtVar.J;
        }
    }

    public final yo A() {
        jt jtVar = this.q;
        return jtVar != null ? jtVar.s.A() : this.t;
    }

    public final void B(jt jtVar) {
        if (C(2)) {
            Log.v("FragmentManager", "hide: " + jtVar);
        }
        if (jtVar.z) {
            return;
        }
        jtVar.z = true;
        jtVar.J = true ^ jtVar.J;
        S(jtVar);
    }

    public final void G(int i, boolean z) {
        HashMap map;
        kt ktVar;
        if (this.o == null && i != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i != this.n) {
            this.n = i;
            po poVar = this.c;
            Iterator it = ((ArrayList) poVar.c).iterator();
            while (true) {
                boolean zHasNext = it.hasNext();
                map = (HashMap) poVar.d;
                if (!zHasNext) {
                    break;
                }
                d dVar = (d) map.get(((jt) it.next()).f);
                if (dVar != null) {
                    dVar.k();
                }
            }
            for (d dVar2 : map.values()) {
                if (dVar2 != null) {
                    dVar2.k();
                    jt jtVar = dVar2.c;
                    if (jtVar.m && jtVar.r <= 0) {
                        poVar.K(dVar2);
                    }
                }
            }
            U();
            if (this.y && (ktVar = this.o) != null && this.n == 7) {
                ktVar.x.e();
                this.y = false;
            }
        }
    }

    public final void H() {
        if (this.o == null) {
            return;
        }
        this.z = false;
        this.A = false;
        this.G.g = false;
        for (jt jtVar : this.c.B()) {
            if (jtVar != null) {
                jtVar.u.H();
            }
        }
    }

    public final boolean I() {
        u(false);
        t(true);
        jt jtVar = this.r;
        if (jtVar != null && jtVar.f().I()) {
            return true;
        }
        boolean zJ = J(this.D, this.E, -1, 0);
        if (zJ) {
            this.b = true;
            try {
                L(this.D, this.E);
            } finally {
                d();
            }
        }
        V();
        q();
        ((HashMap) this.c.d).values().removeAll(Collections.singleton(null));
        return zJ;
    }

    public final boolean J(ArrayList arrayList, ArrayList arrayList2, int i, int i2) {
        int size;
        s7 s7Var;
        ArrayList arrayList3 = this.d;
        if (arrayList3 == null) {
            return false;
        }
        if (i < 0 && (i2 & 1) == 0) {
            int size2 = arrayList3.size() - 1;
            if (size2 < 0) {
                return false;
            }
            arrayList.add(this.d.remove(size2));
            arrayList2.add(Boolean.TRUE);
            return true;
        }
        if (i >= 0) {
            size = arrayList3.size() - 1;
            while (size >= 0) {
                s7 s7Var2 = (s7) this.d.get(size);
                if (i >= 0 && i == s7Var2.r) {
                    break;
                }
                size--;
            }
            if (size < 0) {
                return false;
            }
            if ((i2 & 1) != 0) {
                do {
                    size--;
                    if (size < 0) {
                        break;
                    }
                    s7Var = (s7) this.d.get(size);
                    if (i < 0) {
                        break;
                    }
                } while (i == s7Var.r);
            }
        } else {
            size = -1;
        }
        if (size == this.d.size() - 1) {
            return false;
        }
        for (int size3 = this.d.size() - 1; size3 > size; size3--) {
            arrayList.add(this.d.remove(size3));
            arrayList2.add(Boolean.TRUE);
        }
        return true;
    }

    public final void K(jt jtVar) {
        if (C(2)) {
            Log.v("FragmentManager", "remove: " + jtVar + " nesting=" + jtVar.r);
        }
        boolean z = jtVar.r > 0;
        if (jtVar.A && z) {
            return;
        }
        po poVar = this.c;
        synchronized (((ArrayList) poVar.c)) {
            ((ArrayList) poVar.c).remove(jtVar);
        }
        jtVar.l = false;
        if (D(jtVar)) {
            this.y = true;
        }
        jtVar.m = true;
        S(jtVar);
    }

    public final void L(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            if (!((s7) arrayList.get(i)).o) {
                if (i2 != i) {
                    w(arrayList, arrayList2, i2, i);
                }
                i2 = i + 1;
                if (((Boolean) arrayList2.get(i)).booleanValue()) {
                    while (i2 < size && ((Boolean) arrayList2.get(i2)).booleanValue() && !((s7) arrayList.get(i2)).o) {
                        i2++;
                    }
                }
                w(arrayList, arrayList2, i, i2);
                i = i2 - 1;
            }
            i++;
        }
        if (i2 != size) {
            w(arrayList, arrayList2, i2, size);
        }
    }

    public final void M(Parcelable parcelable) {
        int i;
        e5 e5Var;
        int i2;
        d dVar;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.b == null) {
            return;
        }
        po poVar = this.c;
        ((HashMap) poVar.d).clear();
        Iterator it = fragmentManagerState.b.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            i = 2;
            e5Var = this.l;
            if (!zHasNext) {
                break;
            }
            FragmentState fragmentState = (FragmentState) it.next();
            if (fragmentState != null) {
                jt jtVar = (jt) this.G.b.get(fragmentState.c);
                if (jtVar != null) {
                    if (C(2)) {
                        Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + jtVar);
                    }
                    dVar = new d(e5Var, poVar, jtVar, fragmentState);
                } else {
                    dVar = new d(this.l, this.c, this.o.u.getClassLoader(), z(), fragmentState);
                }
                jt jtVar2 = dVar.c;
                jtVar2.s = this;
                if (C(2)) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + jtVar2.f + "): " + jtVar2);
                }
                dVar.m(this.o.u.getClassLoader());
                poVar.J(dVar);
                dVar.e = this.n;
            }
        }
        xt xtVar = this.G;
        xtVar.getClass();
        for (jt jtVar3 : new ArrayList(xtVar.b.values())) {
            if (!(((HashMap) poVar.d).get(jtVar3.f) != null)) {
                if (C(2)) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + jtVar3 + " that was not found in the set of active Fragments " + fragmentManagerState.b);
                }
                this.G.c(jtVar3);
                jtVar3.s = this;
                d dVar2 = new d(e5Var, poVar, jtVar3);
                dVar2.e = 1;
                dVar2.k();
                jtVar3.m = true;
                dVar2.k();
            }
        }
        ArrayList<String> arrayList = fragmentManagerState.c;
        ((ArrayList) poVar.c).clear();
        if (arrayList != null) {
            for (String str : arrayList) {
                jt jtVarR = poVar.r(str);
                if (jtVarR == null) {
                    throw new IllegalStateException(hk.j("No instantiated fragment for (", str, ")"));
                }
                if (C(2)) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + jtVarR);
                }
                poVar.j(jtVarR);
            }
        }
        jt jtVar4 = null;
        if (fragmentManagerState.d != null) {
            this.d = new ArrayList(fragmentManagerState.d.length);
            int i3 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.d;
                if (i3 >= backStackStateArr.length) {
                    break;
                }
                BackStackState backStackState = backStackStateArr[i3];
                backStackState.getClass();
                s7 s7Var = new s7(this);
                int i4 = 0;
                int i5 = 0;
                while (true) {
                    int[] iArr = backStackState.b;
                    if (i4 >= iArr.length) {
                        break;
                    }
                    au auVar = new au();
                    int i6 = i4 + 1;
                    int i7 = i;
                    auVar.a = iArr[i4];
                    if (C(i7)) {
                        Log.v("FragmentManager", "Instantiate " + s7Var + " op #" + i5 + " base fragment #" + iArr[i6]);
                    }
                    String str2 = (String) backStackState.c.get(i5);
                    if (str2 != null) {
                        auVar.b = poVar.r(str2);
                    } else {
                        auVar.b = jtVar4;
                    }
                    auVar.g = Lifecycle$State.values()[backStackState.d[i5]];
                    auVar.h = Lifecycle$State.values()[backStackState.e[i5]];
                    int i8 = iArr[i6];
                    auVar.c = i8;
                    int i9 = iArr[i4 + 2];
                    auVar.d = i9;
                    int i10 = i4 + 4;
                    int i11 = iArr[i4 + 3];
                    auVar.e = i11;
                    i4 += 5;
                    int i12 = iArr[i10];
                    auVar.f = i12;
                    s7Var.b = i8;
                    s7Var.c = i9;
                    s7Var.d = i11;
                    s7Var.e = i12;
                    s7Var.b(auVar);
                    i5++;
                    i = i7;
                    jtVar4 = null;
                }
                int i13 = i;
                s7Var.f = backStackState.f;
                s7Var.h = backStackState.g;
                s7Var.r = backStackState.h;
                s7Var.g = true;
                s7Var.i = backStackState.i;
                s7Var.j = backStackState.j;
                s7Var.k = backStackState.k;
                s7Var.l = backStackState.l;
                s7Var.m = backStackState.m;
                s7Var.n = backStackState.n;
                s7Var.o = backStackState.o;
                s7Var.c(1);
                if (C(i13)) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i3 + " (index " + s7Var.r + "): " + s7Var);
                    PrintWriter printWriter = new PrintWriter(new m30());
                    s7Var.f("  ", printWriter, false);
                    printWriter.close();
                }
                this.d.add(s7Var);
                i3++;
                i = i13;
                jtVar4 = null;
            }
            i2 = 0;
        } else {
            i2 = 0;
            this.d = null;
        }
        this.i.set(fragmentManagerState.e);
        String str3 = fragmentManagerState.f;
        if (str3 != null) {
            jt jtVarR2 = poVar.r(str3);
            this.r = jtVarR2;
            n(jtVarR2);
        }
        ArrayList arrayList2 = fragmentManagerState.g;
        if (arrayList2 != null) {
            for (int i14 = i2; i14 < arrayList2.size(); i14++) {
                Bundle bundle = (Bundle) fragmentManagerState.h.get(i14);
                bundle.setClassLoader(this.o.u.getClassLoader());
                this.j.put(arrayList2.get(i14), bundle);
            }
        }
        this.x = new ArrayDeque(fragmentManagerState.i);
    }

    public final Parcelable N() {
        int i;
        ArrayList arrayList;
        BackStackState[] backStackStateArr;
        int size;
        Iterator it = e().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            vj vjVar = (vj) it.next();
            if (vjVar.e) {
                vjVar.e = false;
                vjVar.c();
            }
        }
        Iterator it2 = e().iterator();
        while (it2.hasNext()) {
            ((vj) it2.next()).e();
        }
        u(true);
        this.z = true;
        this.G.g = true;
        po poVar = this.c;
        poVar.getClass();
        HashMap map = (HashMap) poVar.d;
        ArrayList arrayList2 = new ArrayList(map.size());
        Iterator it3 = map.values().iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            d dVar = (d) it3.next();
            if (dVar != null) {
                jt jtVar = dVar.c;
                FragmentState fragmentState = new FragmentState(jtVar);
                if (jtVar.b <= -1 || fragmentState.n != null) {
                    fragmentState.n = jtVar.c;
                } else {
                    Bundle bundle = new Bundle();
                    jtVar.q(bundle);
                    jtVar.P.K(bundle);
                    Parcelable parcelableN = jtVar.u.N();
                    if (parcelableN != null) {
                        bundle.putParcelable("android:support:fragments", parcelableN);
                    }
                    dVar.a.r(false);
                    Bundle bundle2 = bundle.isEmpty() ? null : bundle;
                    if (jtVar.F != null) {
                        dVar.o();
                    }
                    if (jtVar.d != null) {
                        if (bundle2 == null) {
                            bundle2 = new Bundle();
                        }
                        bundle2.putSparseParcelableArray("android:view_state", jtVar.d);
                    }
                    if (jtVar.e != null) {
                        if (bundle2 == null) {
                            bundle2 = new Bundle();
                        }
                        bundle2.putBundle("android:view_registry_state", jtVar.e);
                    }
                    if (!jtVar.H) {
                        if (bundle2 == null) {
                            bundle2 = new Bundle();
                        }
                        bundle2.putBoolean("android:user_visible_hint", jtVar.H);
                    }
                    fragmentState.n = bundle2;
                    if (jtVar.i != null) {
                        if (bundle2 == null) {
                            fragmentState.n = new Bundle();
                        }
                        fragmentState.n.putString("android:target_state", jtVar.i);
                        int i2 = jtVar.j;
                        if (i2 != 0) {
                            fragmentState.n.putInt("android:target_req_state", i2);
                        }
                    }
                }
                arrayList2.add(fragmentState);
                if (C(2)) {
                    Log.v("FragmentManager", "Saved state of " + jtVar + ": " + fragmentState.n);
                }
            }
        }
        if (arrayList2.isEmpty()) {
            if (C(2)) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        po poVar2 = this.c;
        synchronized (((ArrayList) poVar2.c)) {
            try {
                if (((ArrayList) poVar2.c).isEmpty()) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList(((ArrayList) poVar2.c).size());
                    for (jt jtVar2 : (ArrayList) poVar2.c) {
                        arrayList.add(jtVar2.f);
                        if (C(2)) {
                            Log.v("FragmentManager", "saveAllState: adding fragment (" + jtVar2.f + "): " + jtVar2);
                        }
                    }
                }
            } finally {
            }
        }
        ArrayList arrayList3 = this.d;
        if (arrayList3 == null || (size = arrayList3.size()) <= 0) {
            backStackStateArr = null;
        } else {
            backStackStateArr = new BackStackState[size];
            for (i = 0; i < size; i++) {
                backStackStateArr[i] = new BackStackState((s7) this.d.get(i));
                if (C(2)) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i + ": " + this.d.get(i));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f = null;
        ArrayList arrayList4 = new ArrayList();
        fragmentManagerState.g = arrayList4;
        ArrayList arrayList5 = new ArrayList();
        fragmentManagerState.h = arrayList5;
        fragmentManagerState.b = arrayList2;
        fragmentManagerState.c = arrayList;
        fragmentManagerState.d = backStackStateArr;
        fragmentManagerState.e = this.i.get();
        jt jtVar3 = this.r;
        if (jtVar3 != null) {
            fragmentManagerState.f = jtVar3.f;
        }
        arrayList4.addAll(this.j.keySet());
        arrayList5.addAll(this.j.values());
        fragmentManagerState.i = new ArrayList(this.x);
        return fragmentManagerState;
    }

    public final void O() {
        synchronized (this.a) {
            try {
                if (this.a.size() == 1) {
                    this.o.v.removeCallbacks(this.H);
                    this.o.v.post(this.H);
                    V();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void P(jt jtVar, boolean z) {
        ViewGroup viewGroupY = y(jtVar);
        if (viewGroupY == null || !(viewGroupY instanceof FragmentContainerView)) {
            return;
        }
        ((FragmentContainerView) viewGroupY).setDrawDisappearingViewsLast(!z);
    }

    public final void Q(jt jtVar, Lifecycle$State lifecycle$State) {
        if (jtVar.equals(this.c.r(jtVar.f)) && (jtVar.t == null || jtVar.s == this)) {
            jtVar.L = lifecycle$State;
            return;
        }
        throw new IllegalArgumentException("Fragment " + jtVar + " is not an active fragment of FragmentManager " + this);
    }

    public final void R(jt jtVar) {
        if (jtVar != null) {
            if (!jtVar.equals(this.c.r(jtVar.f)) || (jtVar.t != null && jtVar.s != this)) {
                throw new IllegalArgumentException("Fragment " + jtVar + " is not an active fragment of FragmentManager " + this);
            }
        }
        jt jtVar2 = this.r;
        this.r = jtVar;
        n(jtVar2);
        n(this.r);
    }

    public final void S(jt jtVar) {
        ViewGroup viewGroupY = y(jtVar);
        if (viewGroupY != null) {
            ft ftVar = jtVar.I;
            if ((ftVar == null ? 0 : ftVar.e) + (ftVar == null ? 0 : ftVar.d) + (ftVar == null ? 0 : ftVar.c) + (ftVar == null ? 0 : ftVar.b) > 0) {
                if (viewGroupY.getTag(R$id.visible_removing_fragment_view_tag) == null) {
                    viewGroupY.setTag(R$id.visible_removing_fragment_view_tag, jtVar);
                }
                jt jtVar2 = (jt) viewGroupY.getTag(R$id.visible_removing_fragment_view_tag);
                ft ftVar2 = jtVar.I;
                boolean z = ftVar2 != null ? ftVar2.a : false;
                if (jtVar2.I == null) {
                    return;
                }
                jtVar2.e().a = z;
            }
        }
    }

    public final void U() {
        for (d dVar : this.c.t()) {
            jt jtVar = dVar.c;
            if (jtVar.G) {
                if (this.b) {
                    this.C = true;
                } else {
                    jtVar.G = false;
                    dVar.k();
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.internal.FunctionReferenceImpl, ou] */
    /* JADX WARN: Type inference failed for: r1v10, types: [kotlin.jvm.internal.FunctionReferenceImpl, ou] */
    public final void V() {
        synchronized (this.a) {
            try {
                if (!this.a.isEmpty()) {
                    ot otVar = this.h;
                    otVar.a = true;
                    ?? r1 = otVar.c;
                    if (r1 != 0) {
                        r1.invoke();
                    }
                    return;
                }
                ot otVar2 = this.h;
                ArrayList arrayList = this.d;
                otVar2.a = (arrayList != null ? arrayList.size() : 0) > 0 && F(this.q);
                ?? r0 = otVar2.c;
                if (r0 != 0) {
                    r0.invoke();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final d a(jt jtVar) {
        if (C(2)) {
            Log.v("FragmentManager", "add: " + jtVar);
        }
        d dVarF = f(jtVar);
        jtVar.s = this;
        po poVar = this.c;
        poVar.J(dVarF);
        if (!jtVar.A) {
            poVar.j(jtVar);
            jtVar.m = false;
            if (jtVar.F == null) {
                jtVar.J = false;
            }
            if (D(jtVar)) {
                this.y = true;
            }
        }
        return dVarF;
    }

    public void addFragmentOnAttachListener(yt ytVar) {
        this.m.add(ytVar);
    }

    public void addOnBackStackChangedListener(rt rtVar) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.add(rtVar);
    }

    public final void b(kt ktVar, nv0 nv0Var, jt jtVar) {
        if (this.o != null) {
            throw new IllegalStateException("Already attached");
        }
        this.o = ktVar;
        this.p = nv0Var;
        this.q = jtVar;
        if (jtVar != null) {
            addFragmentOnAttachListener(new qt(jtVar));
        } else if (ktVar != null) {
            addFragmentOnAttachListener(ktVar);
        }
        if (this.q != null) {
            V();
        }
        if (ktVar != null) {
            qa0 qa0VarG = ktVar.x.g();
            this.g = qa0VarG;
            u10 u10Var = jtVar != null ? jtVar : ktVar;
            qa0VarG.getClass();
            ot otVar = this.h;
            oy.j(otVar, "onBackPressedCallback");
            p10 lifecycle = u10Var.getLifecycle();
            if (((w10) lifecycle).c != Lifecycle$State.DESTROYED) {
                otVar.b.add(new na0(qa0VarG, lifecycle, otVar));
                qa0VarG.c();
                otVar.c = new pa0(0, qa0VarG, qa0.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0, 0);
            }
        }
        if (jtVar != null) {
            xt xtVar = jtVar.s.G;
            HashMap map = xtVar.c;
            xt xtVar2 = (xt) map.get(jtVar.f);
            if (xtVar2 == null) {
                xtVar2 = new xt(xtVar.e);
                map.put(jtVar.f, xtVar2);
            }
            this.G = xtVar2;
        } else if (ktVar != null) {
            this.G = (xt) new o70(ktVar.x.d(), xt.h).w(xt.class);
        } else {
            this.G = new xt(false);
        }
        xt xtVar3 = this.G;
        xtVar3.g = this.z || this.A;
        this.c.e = xtVar3;
        kt ktVar2 = this.o;
        if (ktVar2 != null) {
            FragmentActivity fragmentActivity = ktVar2.x;
            String strN = hk.n("FragmentManager:", jtVar != null ? hk.k(new StringBuilder(), jtVar.f, ":") : ExtensionRequestData.EMPTY_VALUE);
            String strI = hk.i(strN, "StartActivityForResult");
            h2 h2Var = new h2(1);
            b bVar = new b(this, 2);
            ze zeVar = fragmentActivity.m;
            this.u = zeVar.c(strI, h2Var, bVar);
            this.v = zeVar.c(hk.i(strN, "StartIntentSenderForResult"), new h2(2), new b(this, 0));
            this.w = zeVar.c(hk.i(strN, "RequestPermissions"), new h2(0), new b(this, 1));
        }
    }

    public final void c(jt jtVar) {
        if (C(2)) {
            Log.v("FragmentManager", "attach: " + jtVar);
        }
        if (jtVar.A) {
            jtVar.A = false;
            if (jtVar.l) {
                return;
            }
            this.c.j(jtVar);
            if (C(2)) {
                Log.v("FragmentManager", "add from attach: " + jtVar);
            }
            if (D(jtVar)) {
                this.y = true;
            }
        }
    }

    public final void d() {
        this.b = false;
        this.E.clear();
        this.D.clear();
    }

    public final HashSet e() {
        HashSet hashSet = new HashSet();
        Iterator it = this.c.t().iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = ((d) it.next()).c.E;
            if (viewGroup != null) {
                hashSet.add(vj.f(viewGroup, A()));
            }
        }
        return hashSet;
    }

    public final d f(jt jtVar) {
        String str = jtVar.f;
        po poVar = this.c;
        d dVar = (d) ((HashMap) poVar.d).get(str);
        if (dVar != null) {
            return dVar;
        }
        d dVar2 = new d(this.l, poVar, jtVar);
        dVar2.m(this.o.u.getClassLoader());
        dVar2.e = this.n;
        return dVar2;
    }

    public final void g(jt jtVar) {
        if (C(2)) {
            Log.v("FragmentManager", "detach: " + jtVar);
        }
        if (jtVar.A) {
            return;
        }
        jtVar.A = true;
        if (jtVar.l) {
            if (C(2)) {
                Log.v("FragmentManager", "remove from detach: " + jtVar);
            }
            po poVar = this.c;
            synchronized (((ArrayList) poVar.c)) {
                ((ArrayList) poVar.c).remove(jtVar);
            }
            jtVar.l = false;
            if (D(jtVar)) {
                this.y = true;
            }
            S(jtVar);
        }
    }

    public final void h() {
        for (jt jtVar : this.c.B()) {
            if (jtVar != null) {
                jtVar.D = true;
                jtVar.u.h();
            }
        }
    }

    public final boolean i() {
        if (this.n >= 1) {
            for (jt jtVar : this.c.B()) {
                if (jtVar != null) {
                    if (!jtVar.z ? jtVar.u.i() : false) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final boolean j() {
        if (this.n < 1) {
            return false;
        }
        ArrayList arrayList = null;
        boolean z = false;
        for (jt jtVar : this.c.B()) {
            if (jtVar != null && E(jtVar)) {
                if (!jtVar.z ? jtVar.u.j() : false) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(jtVar);
                    z = true;
                }
            }
        }
        if (this.e != null) {
            for (int i = 0; i < this.e.size(); i++) {
                jt jtVar2 = (jt) this.e.get(i);
                if (arrayList == null || !arrayList.contains(jtVar2)) {
                    jtVar2.getClass();
                }
            }
        }
        this.e = arrayList;
        return z;
    }

    public final void k() {
        this.B = true;
        u(true);
        Iterator it = e().iterator();
        while (it.hasNext()) {
            ((vj) it.next()).e();
        }
        p(-1);
        this.o = null;
        this.p = null;
        this.q = null;
        if (this.g != null) {
            Iterator it2 = this.h.b.iterator();
            while (it2.hasNext()) {
                ((ka) it2.next()).cancel();
            }
            this.g = null;
        }
        k2 k2Var = this.u;
        if (k2Var != null) {
            k2Var.I();
            this.v.I();
            this.w.I();
        }
    }

    public final boolean l() {
        if (this.n >= 1) {
            for (jt jtVar : this.c.B()) {
                if (jtVar != null) {
                    if (!jtVar.z ? jtVar.u.l() : false) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final void m() {
        if (this.n < 1) {
            return;
        }
        for (jt jtVar : this.c.B()) {
            if (jtVar != null && !jtVar.z) {
                jtVar.u.m();
            }
        }
    }

    public final void n(jt jtVar) {
        if (jtVar != null) {
            if (jtVar.equals(this.c.r(jtVar.f))) {
                jtVar.s.getClass();
                boolean zF = F(jtVar);
                Boolean bool = jtVar.k;
                if (bool == null || bool.booleanValue() != zF) {
                    jtVar.k = Boolean.valueOf(zF);
                    vt vtVar = jtVar.u;
                    vtVar.V();
                    vtVar.n(vtVar.r);
                }
            }
        }
    }

    public final boolean o() {
        boolean z = false;
        if (this.n < 1) {
            return false;
        }
        for (jt jtVar : this.c.B()) {
            if (jtVar != null && E(jtVar)) {
                if (!jtVar.z ? jtVar.u.o() : false) {
                    z = true;
                }
            }
        }
        return z;
    }

    public final void p(int i) {
        try {
            this.b = true;
            for (d dVar : ((HashMap) this.c.d).values()) {
                if (dVar != null) {
                    dVar.e = i;
                }
            }
            G(i, false);
            Iterator it = e().iterator();
            while (it.hasNext()) {
                ((vj) it.next()).e();
            }
            this.b = false;
            u(true);
        } catch (Throwable th) {
            this.b = false;
            throw th;
        }
    }

    public final void q() {
        if (this.C) {
            this.C = false;
            U();
        }
    }

    public final void r(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String str2;
        String strI = hk.i(str, "    ");
        po poVar = this.c;
        poVar.getClass();
        String str3 = str + "    ";
        HashMap map = (HashMap) poVar.d;
        if (!map.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (d dVar : map.values()) {
                printWriter.print(str);
                if (dVar != null) {
                    jt jtVar = dVar.c;
                    printWriter.println(jtVar);
                    jtVar.getClass();
                    printWriter.print(str3);
                    printWriter.print("mFragmentId=#");
                    printWriter.print(Integer.toHexString(jtVar.w));
                    printWriter.print(" mContainerId=#");
                    printWriter.print(Integer.toHexString(jtVar.x));
                    printWriter.print(" mTag=");
                    printWriter.println(jtVar.y);
                    printWriter.print(str3);
                    printWriter.print("mState=");
                    printWriter.print(jtVar.b);
                    printWriter.print(" mWho=");
                    printWriter.print(jtVar.f);
                    printWriter.print(" mBackStackNesting=");
                    printWriter.println(jtVar.r);
                    printWriter.print(str3);
                    printWriter.print("mAdded=");
                    printWriter.print(jtVar.l);
                    printWriter.print(" mRemoving=");
                    printWriter.print(jtVar.m);
                    printWriter.print(" mFromLayout=");
                    printWriter.print(jtVar.n);
                    printWriter.print(" mInLayout=");
                    printWriter.println(jtVar.o);
                    printWriter.print(str3);
                    printWriter.print("mHidden=");
                    printWriter.print(jtVar.z);
                    printWriter.print(" mDetached=");
                    printWriter.print(jtVar.A);
                    printWriter.print(" mMenuVisible=");
                    printWriter.print(jtVar.C);
                    printWriter.print(" mHasMenu=");
                    printWriter.println(false);
                    printWriter.print(str3);
                    printWriter.print("mRetainInstance=");
                    printWriter.print(jtVar.B);
                    printWriter.print(" mUserVisibleHint=");
                    printWriter.println(jtVar.H);
                    if (jtVar.s != null) {
                        printWriter.print(str3);
                        printWriter.print("mFragmentManager=");
                        printWriter.println(jtVar.s);
                    }
                    if (jtVar.t != null) {
                        printWriter.print(str3);
                        printWriter.print("mHost=");
                        printWriter.println(jtVar.t);
                    }
                    if (jtVar.v != null) {
                        printWriter.print(str3);
                        printWriter.print("mParentFragment=");
                        printWriter.println(jtVar.v);
                    }
                    if (jtVar.g != null) {
                        printWriter.print(str3);
                        printWriter.print("mArguments=");
                        printWriter.println(jtVar.g);
                    }
                    if (jtVar.c != null) {
                        printWriter.print(str3);
                        printWriter.print("mSavedFragmentState=");
                        printWriter.println(jtVar.c);
                    }
                    if (jtVar.d != null) {
                        printWriter.print(str3);
                        printWriter.print("mSavedViewState=");
                        printWriter.println(jtVar.d);
                    }
                    if (jtVar.e != null) {
                        printWriter.print(str3);
                        printWriter.print("mSavedViewRegistryState=");
                        printWriter.println(jtVar.e);
                    }
                    Object objR = jtVar.h;
                    if (objR == null) {
                        c cVar = jtVar.s;
                        objR = (cVar == null || (str2 = jtVar.i) == null) ? null : cVar.c.r(str2);
                    }
                    if (objR != null) {
                        printWriter.print(str3);
                        printWriter.print("mTarget=");
                        printWriter.print(objR);
                        printWriter.print(" mTargetRequestCode=");
                        printWriter.println(jtVar.j);
                    }
                    printWriter.print(str3);
                    printWriter.print("mPopDirection=");
                    ft ftVar = jtVar.I;
                    printWriter.println(ftVar == null ? false : ftVar.a);
                    ft ftVar2 = jtVar.I;
                    if ((ftVar2 == null ? 0 : ftVar2.b) != 0) {
                        printWriter.print(str3);
                        printWriter.print("getEnterAnim=");
                        ft ftVar3 = jtVar.I;
                        printWriter.println(ftVar3 == null ? 0 : ftVar3.b);
                    }
                    ft ftVar4 = jtVar.I;
                    if ((ftVar4 == null ? 0 : ftVar4.c) != 0) {
                        printWriter.print(str3);
                        printWriter.print("getExitAnim=");
                        ft ftVar5 = jtVar.I;
                        printWriter.println(ftVar5 == null ? 0 : ftVar5.c);
                    }
                    ft ftVar6 = jtVar.I;
                    if ((ftVar6 == null ? 0 : ftVar6.d) != 0) {
                        printWriter.print(str3);
                        printWriter.print("getPopEnterAnim=");
                        ft ftVar7 = jtVar.I;
                        printWriter.println(ftVar7 == null ? 0 : ftVar7.d);
                    }
                    ft ftVar8 = jtVar.I;
                    if ((ftVar8 == null ? 0 : ftVar8.e) != 0) {
                        printWriter.print(str3);
                        printWriter.print("getPopExitAnim=");
                        ft ftVar9 = jtVar.I;
                        printWriter.println(ftVar9 == null ? 0 : ftVar9.e);
                    }
                    if (jtVar.E != null) {
                        printWriter.print(str3);
                        printWriter.print("mContainer=");
                        printWriter.println(jtVar.E);
                    }
                    if (jtVar.F != null) {
                        printWriter.print(str3);
                        printWriter.print("mView=");
                        printWriter.println(jtVar.F);
                    }
                    if (jtVar.g() != null) {
                        xj0 xj0Var = ((c30) new o70(jtVar.d(), c30.c).w(c30.class)).b;
                        if (xj0Var.d > 0) {
                            printWriter.print(str3);
                            printWriter.println("Loaders:");
                            if (xj0Var.d > 0) {
                                if (xj0Var.c[0] != null) {
                                    throw new ClassCastException();
                                }
                                printWriter.print(str3);
                                printWriter.print("  #");
                                printWriter.print(xj0Var.b[0]);
                                printWriter.print(": ");
                                throw null;
                            }
                        }
                    }
                    printWriter.print(str3);
                    printWriter.println("Child " + jtVar.u + ":");
                    jtVar.u.r(hk.i(str3, "  "), fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        ArrayList arrayList = (ArrayList) poVar.c;
        int size3 = arrayList.size();
        if (size3 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i = 0; i < size3; i++) {
                jt jtVar2 = (jt) arrayList.get(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(jtVar2.toString());
            }
        }
        ArrayList arrayList2 = this.e;
        if (arrayList2 != null && (size2 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size2; i2++) {
                jt jtVar3 = (jt) this.e.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(jtVar3.toString());
            }
        }
        ArrayList arrayList3 = this.d;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size; i3++) {
                s7 s7Var = (s7) this.d.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(s7Var.toString());
                s7Var.f(strI, printWriter, true);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.i.get());
        synchronized (this.a) {
            try {
                int size4 = this.a.size();
                if (size4 > 0) {
                    printWriter.print(str);
                    printWriter.println("Pending Actions:");
                    for (int i4 = 0; i4 < size4; i4++) {
                        Object obj = (st) this.a.get(i4);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i4);
                        printWriter.print(": ");
                        printWriter.println(obj);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.o);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.p);
        if (this.q != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.q);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.n);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.z);
        printWriter.print(" mStopped=");
        printWriter.print(this.A);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.B);
        if (this.y) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.y);
        }
    }

    public void removeFragmentOnAttachListener(yt ytVar) {
        this.m.remove(ytVar);
    }

    public void removeOnBackStackChangedListener(rt rtVar) {
        ArrayList arrayList = this.k;
        if (arrayList != null) {
            arrayList.remove(rtVar);
        }
    }

    public final void s(st stVar, boolean z) {
        if (!z) {
            if (this.o == null) {
                if (!this.B) {
                    throw new IllegalStateException("FragmentManager has not been attached to a host.");
                }
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            if (this.z || this.A) {
                throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
            }
        }
        synchronized (this.a) {
            try {
                if (this.o == null) {
                    if (!z) {
                        throw new IllegalStateException("Activity has been destroyed");
                    }
                } else {
                    this.a.add(stVar);
                    O();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void t(boolean z) {
        if (this.b) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.o == null) {
            if (!this.B) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.o.v.getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z && (this.z || this.A)) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.D == null) {
            this.D = new ArrayList();
            this.E = new ArrayList();
        }
        this.b = false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(Uuid.SIZE_BITS);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        jt jtVar = this.q;
        if (jtVar != null) {
            sb.append(jtVar.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.q)));
            sb.append("}");
        } else {
            kt ktVar = this.o;
            if (ktVar != null) {
                sb.append(ktVar.getClass().getSimpleName());
                sb.append("{");
                sb.append(Integer.toHexString(System.identityHashCode(this.o)));
                sb.append("}");
            } else {
                sb.append("null");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    public final boolean u(boolean z) {
        boolean zA;
        t(z);
        boolean z2 = false;
        while (true) {
            ArrayList arrayList = this.D;
            ArrayList arrayList2 = this.E;
            synchronized (this.a) {
                try {
                    if (this.a.isEmpty()) {
                        zA = false;
                    } else {
                        int size = this.a.size();
                        zA = false;
                        for (int i = 0; i < size; i++) {
                            zA |= ((st) this.a.get(i)).a(arrayList, arrayList2);
                        }
                        this.a.clear();
                        this.o.v.removeCallbacks(this.H);
                    }
                } finally {
                }
            }
            if (!zA) {
                V();
                q();
                ((HashMap) this.c.d).values().removeAll(Collections.singleton(null));
                return z2;
            }
            z2 = true;
            this.b = true;
            try {
                L(this.D, this.E);
            } finally {
                d();
            }
        }
    }

    public final void v(s7 s7Var, boolean z) {
        if (z && (this.o == null || this.B)) {
            return;
        }
        t(z);
        s7Var.a(this.D, this.E);
        this.b = true;
        try {
            L(this.D, this.E);
            d();
            V();
            q();
            ((HashMap) this.c.d).values().removeAll(Collections.singleton(null));
        } catch (Throwable th) {
            d();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x0156  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void w(java.util.ArrayList r23, java.util.ArrayList r24, int r25, int r26) {
        /*
            Method dump skipped, instruction units count: 1258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.c.w(java.util.ArrayList, java.util.ArrayList, int, int):void");
    }

    public final jt x(int i) {
        po poVar = this.c;
        ArrayList arrayList = (ArrayList) poVar.c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            jt jtVar = (jt) arrayList.get(size);
            if (jtVar != null && jtVar.w == i) {
                return jtVar;
            }
        }
        for (d dVar : ((HashMap) poVar.d).values()) {
            if (dVar != null) {
                jt jtVar2 = dVar.c;
                if (jtVar2.w == i) {
                    return jtVar2;
                }
            }
        }
        return null;
    }

    public final ViewGroup y(jt jtVar) {
        ViewGroup viewGroup = jtVar.E;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (jtVar.x <= 0 || !this.p.F()) {
            return null;
        }
        View viewE = this.p.E(jtVar.x);
        if (viewE instanceof ViewGroup) {
            return (ViewGroup) viewE;
        }
        return null;
    }

    public final pt z() {
        jt jtVar = this.q;
        return jtVar != null ? jtVar.s.z() : this.s;
    }
}
