package androidx.fragment.app;

import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.R$id;
import androidx.lifecycle.Lifecycle$State;
import defpackage.a00;
import defpackage.b00;
import defpackage.bj0;
import defpackage.c00;
import defpackage.cc;
import defpackage.d00;
import defpackage.di;
import defpackage.dj0;
import defpackage.e00;
import defpackage.ej0;
import defpackage.f;
import defpackage.ga0;
import defpackage.h00;
import defpackage.i6;
import defpackage.j00;
import defpackage.k00;
import defpackage.lw0;
import defpackage.m00;
import defpackage.m9;
import defpackage.on;
import defpackage.ou;
import defpackage.s80;
import defpackage.sz;
import defpackage.t40;
import defpackage.ta0;
import defpackage.tg;
import defpackage.vz;
import defpackage.wz;
import defpackage.x80;
import defpackage.z3;
import defpackage.z8;
import defpackage.z80;
import defpackage.z91;
import defpackage.zn;
import defpackage.zz;
import io.nats.client.support.JsonUtils;
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

/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    public boolean A;
    public boolean B;
    public boolean C;
    public ArrayList D;
    public ArrayList E;
    public ArrayList F;
    public j00 G;
    public final f H;
    public boolean b;
    public ArrayList d;
    public ArrayList e;
    public ej0 g;
    public ArrayList k;
    public final i6 l;
    public final CopyOnWriteArrayList m;
    public int n;
    public wz o;
    public z91 p;
    public vz q;
    public vz r;
    public final b00 s;
    public final di t;
    public i6 u;
    public i6 v;
    public i6 w;
    public ArrayDeque x;
    public boolean y;
    public boolean z;
    public final ArrayList a = new ArrayList();
    public final ou c = new ou(8);
    public final zz f = new zz(this);
    public final a00 h = new a00(this);
    public final AtomicInteger i = new AtomicInteger();
    public final Map j = Collections.synchronizedMap(new HashMap());

    public c() {
        Collections.synchronizedMap(new HashMap());
        Collections.synchronizedMap(new HashMap());
        new di(this);
        this.l = new i6(this);
        this.m = new CopyOnWriteArrayList();
        this.n = -1;
        this.s = new b00(this);
        this.t = new di(16);
        this.x = new ArrayDeque();
        this.H = new f(14, this);
    }

    public static boolean B(int i) {
        return Log.isLoggable("FragmentManager", i);
    }

    public static boolean C(vz vzVar) {
        vzVar.getClass();
        ou ouVar = vzVar.X.c;
        ouVar.getClass();
        ArrayList<vz> arrayList = new ArrayList();
        for (d dVar : ((HashMap) ouVar.G).values()) {
            if (dVar != null) {
                arrayList.add(dVar.c);
            } else {
                arrayList.add(null);
            }
        }
        boolean zC = false;
        for (vz vzVar2 : arrayList) {
            if (vzVar2 != null) {
                zC = C(vzVar2);
            }
            if (zC) {
                return true;
            }
        }
        return false;
    }

    public static boolean D(vz vzVar) {
        if (vzVar == null) {
            return true;
        }
        if (vzVar.f0) {
            return vzVar.V == null || D(vzVar.Y);
        }
        return false;
    }

    public static boolean E(vz vzVar) {
        if (vzVar == null) {
            return true;
        }
        c cVar = vzVar.V;
        return vzVar.equals(cVar.r) && E(cVar.q);
    }

    public static void S(vz vzVar) {
        if (B(2)) {
            Log.v("FragmentManager", "show: " + vzVar);
        }
        if (vzVar.c0) {
            vzVar.c0 = false;
            vzVar.m0 = !vzVar.m0;
        }
    }

    public final void A(vz vzVar) {
        if (B(2)) {
            Log.v("FragmentManager", "hide: " + vzVar);
        }
        if (vzVar.c0) {
            return;
        }
        vzVar.c0 = true;
        vzVar.m0 = true ^ vzVar.m0;
        R(vzVar);
    }

    public final void F(int i, boolean z) {
        HashMap map;
        wz wzVar;
        if (this.o == null && i != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i != this.n) {
            this.n = i;
            ou ouVar = this.c;
            Iterator it = ((ArrayList) ouVar.F).iterator();
            while (true) {
                boolean zHasNext = it.hasNext();
                map = (HashMap) ouVar.G;
                if (!zHasNext) {
                    break;
                }
                d dVar = (d) map.get(((vz) it.next()).I);
                if (dVar != null) {
                    dVar.k();
                }
            }
            for (d dVar2 : map.values()) {
                if (dVar2 != null) {
                    dVar2.k();
                    vz vzVar = dVar2.c;
                    if (vzVar.P && vzVar.U <= 0) {
                        ouVar.M(dVar2);
                    }
                }
            }
            T();
            if (this.y && (wzVar = this.o) != null && this.n == 7) {
                wzVar.f0.e();
                this.y = false;
            }
        }
    }

    public final void G() {
        if (this.o == null) {
            return;
        }
        this.z = false;
        this.A = false;
        this.G.g = false;
        for (vz vzVar : this.c.D()) {
            if (vzVar != null) {
                vzVar.X.G();
            }
        }
    }

    public final boolean H() {
        t(false);
        s(true);
        vz vzVar = this.r;
        if (vzVar != null && vzVar.f().H()) {
            return true;
        }
        boolean zI = I(this.D, this.E, -1, 0);
        if (zI) {
            this.b = true;
            try {
                K(this.D, this.E);
            } finally {
                d();
            }
        }
        U();
        if (this.C) {
            this.C = false;
            T();
        }
        ((HashMap) this.c.G).values().removeAll(Collections.singleton(null));
        return zI;
    }

    public final boolean I(ArrayList arrayList, ArrayList arrayList2, int i, int i2) {
        int size;
        m9 m9Var;
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
                m9 m9Var2 = (m9) this.d.get(size);
                if (i >= 0 && i == m9Var2.r) {
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
                    m9Var = (m9) this.d.get(size);
                    if (i < 0) {
                        break;
                    }
                } while (i == m9Var.r);
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

    public final void J(vz vzVar) {
        if (B(2)) {
            Log.v("FragmentManager", "remove: " + vzVar + " nesting=" + vzVar.U);
        }
        boolean z = vzVar.U > 0;
        if (vzVar.d0 && z) {
            return;
        }
        ou ouVar = this.c;
        synchronized (((ArrayList) ouVar.F)) {
            ((ArrayList) ouVar.F).remove(vzVar);
        }
        vzVar.O = false;
        if (C(vzVar)) {
            this.y = true;
        }
        vzVar.P = true;
        R(vzVar);
    }

    public final void K(ArrayList arrayList, ArrayList arrayList2) {
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
            if (!((m9) arrayList.get(i)).o) {
                if (i2 != i) {
                    v(arrayList, arrayList2, i2, i);
                }
                i2 = i + 1;
                if (((Boolean) arrayList2.get(i)).booleanValue()) {
                    while (i2 < size && ((Boolean) arrayList2.get(i2)).booleanValue() && !((m9) arrayList.get(i2)).o) {
                        i2++;
                    }
                }
                v(arrayList, arrayList2, i, i2);
                i = i2 - 1;
            }
            i++;
        }
        if (i2 != size) {
            v(arrayList, arrayList2, i2, size);
        }
    }

    public final void L(Parcelable parcelable) {
        int i;
        i6 i6Var;
        int i2;
        d dVar;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.E == null) {
            return;
        }
        ou ouVar = this.c;
        ((HashMap) ouVar.G).clear();
        Iterator it = fragmentManagerState.E.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            i = 2;
            i6Var = this.l;
            if (!zHasNext) {
                break;
            }
            FragmentState fragmentState = (FragmentState) it.next();
            if (fragmentState != null) {
                vz vzVar = (vz) this.G.b.get(fragmentState.F);
                if (vzVar != null) {
                    if (B(2)) {
                        Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + vzVar);
                    }
                    dVar = new d(i6Var, ouVar, vzVar, fragmentState);
                } else {
                    dVar = new d(this.l, this.c, this.o.c0.getClassLoader(), y(), fragmentState);
                }
                vz vzVar2 = dVar.c;
                vzVar2.V = this;
                if (B(2)) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + vzVar2.I + "): " + vzVar2);
                }
                dVar.m(this.o.c0.getClassLoader());
                ouVar.L(dVar);
                dVar.e = this.n;
            }
        }
        j00 j00Var = this.G;
        j00Var.getClass();
        for (vz vzVar3 : new ArrayList(j00Var.b.values())) {
            if (!(((HashMap) ouVar.G).get(vzVar3.I) != null)) {
                if (B(2)) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + vzVar3 + " that was not found in the set of active Fragments " + fragmentManagerState.E);
                }
                this.G.c(vzVar3);
                vzVar3.V = this;
                d dVar2 = new d(i6Var, ouVar, vzVar3);
                dVar2.e = 1;
                dVar2.k();
                vzVar3.P = true;
                dVar2.k();
            }
        }
        ArrayList<String> arrayList = fragmentManagerState.F;
        ((ArrayList) ouVar.F).clear();
        if (arrayList != null) {
            for (String str : arrayList) {
                vz vzVarS = ouVar.s(str);
                if (vzVarS == null) {
                    throw new IllegalStateException(zn.n("No instantiated fragment for (", str, ")"));
                }
                if (B(2)) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + vzVarS);
                }
                ouVar.i(vzVarS);
            }
        }
        vz vzVar4 = null;
        if (fragmentManagerState.G != null) {
            this.d = new ArrayList(fragmentManagerState.G.length);
            int i3 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.G;
                if (i3 >= backStackStateArr.length) {
                    break;
                }
                BackStackState backStackState = backStackStateArr[i3];
                backStackState.getClass();
                m9 m9Var = new m9(this);
                int i4 = 0;
                int i5 = 0;
                while (true) {
                    int[] iArr = backStackState.E;
                    if (i4 >= iArr.length) {
                        break;
                    }
                    m00 m00Var = new m00();
                    int i6 = i4 + 1;
                    int i7 = i;
                    m00Var.a = iArr[i4];
                    if (B(i7)) {
                        Log.v("FragmentManager", "Instantiate " + m9Var + " op #" + i5 + " base fragment #" + iArr[i6]);
                    }
                    String str2 = (String) backStackState.F.get(i5);
                    if (str2 != null) {
                        m00Var.b = ouVar.s(str2);
                    } else {
                        m00Var.b = vzVar4;
                    }
                    m00Var.g = Lifecycle$State.values()[backStackState.G[i5]];
                    m00Var.h = Lifecycle$State.values()[backStackState.H[i5]];
                    int i8 = iArr[i6];
                    m00Var.c = i8;
                    int i9 = iArr[i4 + 2];
                    m00Var.d = i9;
                    int i10 = i4 + 4;
                    int i11 = iArr[i4 + 3];
                    m00Var.e = i11;
                    i4 += 5;
                    int i12 = iArr[i10];
                    m00Var.f = i12;
                    m9Var.b = i8;
                    m9Var.c = i9;
                    m9Var.d = i11;
                    m9Var.e = i12;
                    m9Var.b(m00Var);
                    i5++;
                    i = i7;
                    vzVar4 = null;
                }
                int i13 = i;
                m9Var.f = backStackState.I;
                m9Var.h = backStackState.J;
                m9Var.r = backStackState.K;
                m9Var.g = true;
                m9Var.i = backStackState.L;
                m9Var.j = backStackState.M;
                m9Var.k = backStackState.N;
                m9Var.l = backStackState.O;
                m9Var.m = backStackState.P;
                m9Var.n = backStackState.Q;
                m9Var.o = backStackState.R;
                m9Var.c(1);
                if (B(i13)) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i3 + " (index " + m9Var.r + "): " + m9Var);
                    PrintWriter printWriter = new PrintWriter(new ta0());
                    m9Var.f("  ", printWriter, false);
                    printWriter.close();
                }
                this.d.add(m9Var);
                i3++;
                i = i13;
                vzVar4 = null;
            }
            i2 = 0;
        } else {
            i2 = 0;
            this.d = null;
        }
        this.i.set(fragmentManagerState.H);
        String str3 = fragmentManagerState.I;
        if (str3 != null) {
            vz vzVarS2 = ouVar.s(str3);
            this.r = vzVarS2;
            n(vzVarS2);
        }
        ArrayList arrayList2 = fragmentManagerState.J;
        if (arrayList2 != null) {
            for (int i14 = i2; i14 < arrayList2.size(); i14++) {
                Bundle bundle = (Bundle) fragmentManagerState.K.get(i14);
                bundle.setClassLoader(this.o.c0.getClassLoader());
                this.j.put(arrayList2.get(i14), bundle);
            }
        }
        this.x = new ArrayDeque(fragmentManagerState.L);
    }

    public final Parcelable M() {
        int i;
        ArrayList arrayList;
        BackStackState[] backStackStateArr;
        int size;
        Iterator it = e().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            on onVar = (on) it.next();
            if (onVar.e) {
                onVar.e = false;
                onVar.c();
            }
        }
        Iterator it2 = e().iterator();
        while (it2.hasNext()) {
            ((on) it2.next()).e();
        }
        t(true);
        this.z = true;
        this.G.g = true;
        ou ouVar = this.c;
        ouVar.getClass();
        HashMap map = (HashMap) ouVar.G;
        ArrayList arrayList2 = new ArrayList(map.size());
        Iterator it3 = map.values().iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            d dVar = (d) it3.next();
            if (dVar != null) {
                vz vzVar = dVar.c;
                FragmentState fragmentState = new FragmentState(vzVar);
                if (vzVar.E <= -1 || fragmentState.Q != null) {
                    fragmentState.Q = vzVar.F;
                } else {
                    Bundle bundle = new Bundle();
                    vzVar.q(bundle);
                    vzVar.s0.J(bundle);
                    Parcelable parcelableM = vzVar.X.M();
                    if (parcelableM != null) {
                        bundle.putParcelable("android:support:fragments", parcelableM);
                    }
                    dVar.a.s(false);
                    Bundle bundle2 = bundle.isEmpty() ? null : bundle;
                    if (vzVar.i0 != null) {
                        dVar.o();
                    }
                    if (vzVar.G != null) {
                        if (bundle2 == null) {
                            bundle2 = new Bundle();
                        }
                        bundle2.putSparseParcelableArray("android:view_state", vzVar.G);
                    }
                    if (vzVar.H != null) {
                        if (bundle2 == null) {
                            bundle2 = new Bundle();
                        }
                        bundle2.putBundle("android:view_registry_state", vzVar.H);
                    }
                    if (!vzVar.k0) {
                        if (bundle2 == null) {
                            bundle2 = new Bundle();
                        }
                        bundle2.putBoolean("android:user_visible_hint", vzVar.k0);
                    }
                    fragmentState.Q = bundle2;
                    if (vzVar.L != null) {
                        if (bundle2 == null) {
                            fragmentState.Q = new Bundle();
                        }
                        fragmentState.Q.putString("android:target_state", vzVar.L);
                        int i2 = vzVar.M;
                        if (i2 != 0) {
                            fragmentState.Q.putInt("android:target_req_state", i2);
                        }
                    }
                }
                arrayList2.add(fragmentState);
                if (B(2)) {
                    Log.v("FragmentManager", "Saved state of " + vzVar + ": " + fragmentState.Q);
                }
            }
        }
        if (arrayList2.isEmpty()) {
            if (B(2)) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        ou ouVar2 = this.c;
        synchronized (((ArrayList) ouVar2.F)) {
            try {
                if (((ArrayList) ouVar2.F).isEmpty()) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList(((ArrayList) ouVar2.F).size());
                    for (vz vzVar2 : (ArrayList) ouVar2.F) {
                        arrayList.add(vzVar2.I);
                        if (B(2)) {
                            Log.v("FragmentManager", "saveAllState: adding fragment (" + vzVar2.I + "): " + vzVar2);
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
                backStackStateArr[i] = new BackStackState((m9) this.d.get(i));
                if (B(2)) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i + ": " + this.d.get(i));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.I = null;
        ArrayList arrayList4 = new ArrayList();
        fragmentManagerState.J = arrayList4;
        ArrayList arrayList5 = new ArrayList();
        fragmentManagerState.K = arrayList5;
        fragmentManagerState.E = arrayList2;
        fragmentManagerState.F = arrayList;
        fragmentManagerState.G = backStackStateArr;
        fragmentManagerState.H = this.i.get();
        vz vzVar3 = this.r;
        if (vzVar3 != null) {
            fragmentManagerState.I = vzVar3.I;
        }
        arrayList4.addAll(this.j.keySet());
        arrayList5.addAll(this.j.values());
        fragmentManagerState.L = new ArrayList(this.x);
        return fragmentManagerState;
    }

    public final void N() {
        synchronized (this.a) {
            try {
                if (this.a.size() == 1) {
                    this.o.d0.removeCallbacks(this.H);
                    this.o.d0.post(this.H);
                    U();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void O(vz vzVar, boolean z) {
        ViewGroup viewGroupX = x(vzVar);
        if (viewGroupX == null || !(viewGroupX instanceof FragmentContainerView)) {
            return;
        }
        ((FragmentContainerView) viewGroupX).setDrawDisappearingViewsLast(!z);
    }

    public final void P(vz vzVar, Lifecycle$State lifecycle$State) {
        if (vzVar.equals(this.c.s(vzVar.I)) && (vzVar.W == null || vzVar.V == this)) {
            vzVar.o0 = lifecycle$State;
            return;
        }
        throw new IllegalArgumentException("Fragment " + vzVar + " is not an active fragment of FragmentManager " + this);
    }

    public final void Q(vz vzVar) {
        if (vzVar != null) {
            if (!vzVar.equals(this.c.s(vzVar.I)) || (vzVar.W != null && vzVar.V != this)) {
                throw new IllegalArgumentException("Fragment " + vzVar + " is not an active fragment of FragmentManager " + this);
            }
        }
        vz vzVar2 = this.r;
        this.r = vzVar;
        n(vzVar2);
        n(this.r);
    }

    public final void R(vz vzVar) {
        ViewGroup viewGroupX = x(vzVar);
        if (viewGroupX != null) {
            sz szVar = vzVar.l0;
            if ((szVar == null ? 0 : szVar.e) + (szVar == null ? 0 : szVar.d) + (szVar == null ? 0 : szVar.c) + (szVar == null ? 0 : szVar.b) > 0) {
                if (viewGroupX.getTag(R$id.visible_removing_fragment_view_tag) == null) {
                    viewGroupX.setTag(R$id.visible_removing_fragment_view_tag, vzVar);
                }
                vz vzVar2 = (vz) viewGroupX.getTag(R$id.visible_removing_fragment_view_tag);
                sz szVar2 = vzVar.l0;
                boolean z = szVar2 != null ? szVar2.a : false;
                if (vzVar2.l0 == null) {
                    return;
                }
                vzVar2.e().a = z;
            }
        }
    }

    public final void T() {
        for (d dVar : this.c.v()) {
            vz vzVar = dVar.c;
            if (vzVar.j0) {
                if (this.b) {
                    this.C = true;
                } else {
                    vzVar.j0 = false;
                    dVar.k();
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [a10, kotlin.jvm.internal.FunctionReferenceImpl] */
    /* JADX WARN: Type inference failed for: r1v10, types: [a10, kotlin.jvm.internal.FunctionReferenceImpl] */
    public final void U() {
        synchronized (this.a) {
            try {
                if (!this.a.isEmpty()) {
                    a00 a00Var = this.h;
                    a00Var.a = true;
                    ?? r1 = a00Var.c;
                    if (r1 != 0) {
                        r1.invoke();
                    }
                    return;
                }
                a00 a00Var2 = this.h;
                ArrayList arrayList = this.d;
                a00Var2.a = (arrayList != null ? arrayList.size() : 0) > 0 && E(this.q);
                ?? r0 = a00Var2.c;
                if (r0 != 0) {
                    r0.invoke();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final d a(vz vzVar) {
        if (B(2)) {
            Log.v("FragmentManager", "add: " + vzVar);
        }
        d dVarF = f(vzVar);
        vzVar.V = this;
        ou ouVar = this.c;
        ouVar.L(dVarF);
        if (!vzVar.d0) {
            ouVar.i(vzVar);
            vzVar.P = false;
            if (vzVar.i0 == null) {
                vzVar.m0 = false;
            }
            if (C(vzVar)) {
                this.y = true;
            }
        }
        return dVarF;
    }

    public void addFragmentOnAttachListener(k00 k00Var) {
        this.m.add(k00Var);
    }

    public void addOnBackStackChangedListener(d00 d00Var) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.add(d00Var);
    }

    public final void b(wz wzVar, z91 z91Var, vz vzVar) {
        if (this.o != null) {
            throw new IllegalStateException("Already attached");
        }
        this.o = wzVar;
        this.p = z91Var;
        this.q = vzVar;
        if (vzVar != null) {
            addFragmentOnAttachListener(new c00(vzVar));
        } else if (wzVar != null) {
            addFragmentOnAttachListener(wzVar);
        }
        if (this.q != null) {
            U();
        }
        if (wzVar != null) {
            ej0 ej0VarG = wzVar.f0.g();
            this.g = ej0VarG;
            x80 x80Var = vzVar != null ? vzVar : wzVar;
            ej0VarG.getClass();
            a00 a00Var = this.h;
            t40.n(a00Var, "onBackPressedCallback");
            s80 lifecycle = x80Var.getLifecycle();
            if (((z80) lifecycle).c != Lifecycle$State.DESTROYED) {
                a00Var.b.add(new bj0(ej0VarG, lifecycle, a00Var));
                ej0VarG.c();
                a00Var.c = new dj0(0, ej0VarG, ej0.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0, 0);
            }
        }
        if (vzVar != null) {
            j00 j00Var = vzVar.V.G;
            HashMap map = j00Var.c;
            j00 j00Var2 = (j00) map.get(vzVar.I);
            if (j00Var2 == null) {
                j00Var2 = new j00(j00Var.e);
                map.put(vzVar.I, j00Var2);
            }
            this.G = j00Var2;
        } else if (wzVar != null) {
            this.G = (j00) new z8(wzVar.f0.d(), j00.h).y(j00.class);
        } else {
            this.G = new j00(false);
        }
        j00 j00Var3 = this.G;
        j00Var3.g = this.z || this.A;
        this.c.H = j00Var3;
        wz wzVar2 = this.o;
        if (wzVar2 != null) {
            FragmentActivity fragmentActivity = wzVar2.f0;
            String strV = zn.v("FragmentManager:", vzVar != null ? zn.o(new StringBuilder(), vzVar.I, ":") : "");
            String strM = zn.m(strV, "StartActivityForResult");
            z3 z3Var = new z3(1);
            b bVar = new b(this, 2);
            tg tgVar = fragmentActivity.N;
            this.u = tgVar.b(strM, z3Var, bVar);
            this.v = tgVar.b(zn.m(strV, "StartIntentSenderForResult"), new z3(2), new b(this, 0));
            this.w = tgVar.b(zn.m(strV, "RequestPermissions"), new z3(0), new b(this, 1));
        }
    }

    public final void c(vz vzVar) {
        if (B(2)) {
            Log.v("FragmentManager", "attach: " + vzVar);
        }
        if (vzVar.d0) {
            vzVar.d0 = false;
            if (vzVar.O) {
                return;
            }
            this.c.i(vzVar);
            if (B(2)) {
                Log.v("FragmentManager", "add from attach: " + vzVar);
            }
            if (C(vzVar)) {
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
        Iterator it = this.c.v().iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = ((d) it.next()).c.h0;
            if (viewGroup != null) {
                hashSet.add(on.f(viewGroup, z()));
            }
        }
        return hashSet;
    }

    public final d f(vz vzVar) {
        String str = vzVar.I;
        ou ouVar = this.c;
        d dVar = (d) ((HashMap) ouVar.G).get(str);
        if (dVar != null) {
            return dVar;
        }
        d dVar2 = new d(this.l, ouVar, vzVar);
        dVar2.m(this.o.c0.getClassLoader());
        dVar2.e = this.n;
        return dVar2;
    }

    public final void g(vz vzVar) {
        if (B(2)) {
            Log.v("FragmentManager", "detach: " + vzVar);
        }
        if (vzVar.d0) {
            return;
        }
        vzVar.d0 = true;
        if (vzVar.O) {
            if (B(2)) {
                Log.v("FragmentManager", "remove from detach: " + vzVar);
            }
            ou ouVar = this.c;
            synchronized (((ArrayList) ouVar.F)) {
                ((ArrayList) ouVar.F).remove(vzVar);
            }
            vzVar.O = false;
            if (C(vzVar)) {
                this.y = true;
            }
            R(vzVar);
        }
    }

    public final void h() {
        for (vz vzVar : this.c.D()) {
            if (vzVar != null) {
                vzVar.g0 = true;
                vzVar.X.h();
            }
        }
    }

    public final boolean i() {
        if (this.n >= 1) {
            for (vz vzVar : this.c.D()) {
                if (vzVar != null) {
                    if (!vzVar.c0 ? vzVar.X.i() : false) {
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
        for (vz vzVar : this.c.D()) {
            if (vzVar != null && D(vzVar)) {
                if (!vzVar.c0 ? vzVar.X.j() : false) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(vzVar);
                    z = true;
                }
            }
        }
        if (this.e != null) {
            for (int i = 0; i < this.e.size(); i++) {
                vz vzVar2 = (vz) this.e.get(i);
                if (arrayList == null || !arrayList.contains(vzVar2)) {
                    vzVar2.getClass();
                }
            }
        }
        this.e = arrayList;
        return z;
    }

    public final void k() {
        this.B = true;
        t(true);
        Iterator it = e().iterator();
        while (it.hasNext()) {
            ((on) it.next()).e();
        }
        p(-1);
        this.o = null;
        this.p = null;
        this.q = null;
        if (this.g != null) {
            Iterator it2 = this.h.b.iterator();
            while (it2.hasNext()) {
                ((cc) it2.next()).cancel();
            }
            this.g = null;
        }
        i6 i6Var = this.u;
        if (i6Var != null) {
            i6Var.R();
            this.v.R();
            this.w.R();
        }
    }

    public final boolean l() {
        if (this.n >= 1) {
            for (vz vzVar : this.c.D()) {
                if (vzVar != null) {
                    if (!vzVar.c0 ? vzVar.X.l() : false) {
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
        for (vz vzVar : this.c.D()) {
            if (vzVar != null && !vzVar.c0) {
                vzVar.X.m();
            }
        }
    }

    public final void n(vz vzVar) {
        if (vzVar != null) {
            if (vzVar.equals(this.c.s(vzVar.I))) {
                vzVar.V.getClass();
                boolean zE = E(vzVar);
                Boolean bool = vzVar.N;
                if (bool == null || bool.booleanValue() != zE) {
                    vzVar.N = Boolean.valueOf(zE);
                    h00 h00Var = vzVar.X;
                    h00Var.U();
                    h00Var.n(h00Var.r);
                }
            }
        }
    }

    public final boolean o() {
        boolean z = false;
        if (this.n < 1) {
            return false;
        }
        for (vz vzVar : this.c.D()) {
            if (vzVar != null && D(vzVar)) {
                if (!vzVar.c0 ? vzVar.X.o() : false) {
                    z = true;
                }
            }
        }
        return z;
    }

    public final void p(int i) {
        try {
            this.b = true;
            for (d dVar : ((HashMap) this.c.G).values()) {
                if (dVar != null) {
                    dVar.e = i;
                }
            }
            F(i, false);
            Iterator it = e().iterator();
            while (it.hasNext()) {
                ((on) it.next()).e();
            }
            this.b = false;
            t(true);
        } catch (Throwable th) {
            this.b = false;
            throw th;
        }
    }

    public final void q(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String str2;
        String strM = zn.m(str, "    ");
        ou ouVar = this.c;
        ouVar.getClass();
        String str3 = str + "    ";
        HashMap map = (HashMap) ouVar.G;
        if (!map.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (d dVar : map.values()) {
                printWriter.print(str);
                if (dVar != null) {
                    vz vzVar = dVar.c;
                    printWriter.println(vzVar);
                    vzVar.getClass();
                    printWriter.print(str3);
                    printWriter.print("mFragmentId=#");
                    printWriter.print(Integer.toHexString(vzVar.Z));
                    printWriter.print(" mContainerId=#");
                    printWriter.print(Integer.toHexString(vzVar.a0));
                    printWriter.print(" mTag=");
                    printWriter.println(vzVar.b0);
                    printWriter.print(str3);
                    printWriter.print("mState=");
                    printWriter.print(vzVar.E);
                    printWriter.print(" mWho=");
                    printWriter.print(vzVar.I);
                    printWriter.print(" mBackStackNesting=");
                    printWriter.println(vzVar.U);
                    printWriter.print(str3);
                    printWriter.print("mAdded=");
                    printWriter.print(vzVar.O);
                    printWriter.print(" mRemoving=");
                    printWriter.print(vzVar.P);
                    printWriter.print(" mFromLayout=");
                    printWriter.print(vzVar.Q);
                    printWriter.print(" mInLayout=");
                    printWriter.println(vzVar.R);
                    printWriter.print(str3);
                    printWriter.print("mHidden=");
                    printWriter.print(vzVar.c0);
                    printWriter.print(" mDetached=");
                    printWriter.print(vzVar.d0);
                    printWriter.print(" mMenuVisible=");
                    printWriter.print(vzVar.f0);
                    printWriter.print(" mHasMenu=");
                    printWriter.println(false);
                    printWriter.print(str3);
                    printWriter.print("mRetainInstance=");
                    printWriter.print(vzVar.e0);
                    printWriter.print(" mUserVisibleHint=");
                    printWriter.println(vzVar.k0);
                    if (vzVar.V != null) {
                        printWriter.print(str3);
                        printWriter.print("mFragmentManager=");
                        printWriter.println(vzVar.V);
                    }
                    if (vzVar.W != null) {
                        printWriter.print(str3);
                        printWriter.print("mHost=");
                        printWriter.println(vzVar.W);
                    }
                    if (vzVar.Y != null) {
                        printWriter.print(str3);
                        printWriter.print("mParentFragment=");
                        printWriter.println(vzVar.Y);
                    }
                    if (vzVar.J != null) {
                        printWriter.print(str3);
                        printWriter.print("mArguments=");
                        printWriter.println(vzVar.J);
                    }
                    if (vzVar.F != null) {
                        printWriter.print(str3);
                        printWriter.print("mSavedFragmentState=");
                        printWriter.println(vzVar.F);
                    }
                    if (vzVar.G != null) {
                        printWriter.print(str3);
                        printWriter.print("mSavedViewState=");
                        printWriter.println(vzVar.G);
                    }
                    if (vzVar.H != null) {
                        printWriter.print(str3);
                        printWriter.print("mSavedViewRegistryState=");
                        printWriter.println(vzVar.H);
                    }
                    Object objS = vzVar.K;
                    if (objS == null) {
                        c cVar = vzVar.V;
                        objS = (cVar == null || (str2 = vzVar.L) == null) ? null : cVar.c.s(str2);
                    }
                    if (objS != null) {
                        printWriter.print(str3);
                        printWriter.print("mTarget=");
                        printWriter.print(objS);
                        printWriter.print(" mTargetRequestCode=");
                        printWriter.println(vzVar.M);
                    }
                    printWriter.print(str3);
                    printWriter.print("mPopDirection=");
                    sz szVar = vzVar.l0;
                    printWriter.println(szVar == null ? false : szVar.a);
                    sz szVar2 = vzVar.l0;
                    if ((szVar2 == null ? 0 : szVar2.b) != 0) {
                        printWriter.print(str3);
                        printWriter.print("getEnterAnim=");
                        sz szVar3 = vzVar.l0;
                        printWriter.println(szVar3 == null ? 0 : szVar3.b);
                    }
                    sz szVar4 = vzVar.l0;
                    if ((szVar4 == null ? 0 : szVar4.c) != 0) {
                        printWriter.print(str3);
                        printWriter.print("getExitAnim=");
                        sz szVar5 = vzVar.l0;
                        printWriter.println(szVar5 == null ? 0 : szVar5.c);
                    }
                    sz szVar6 = vzVar.l0;
                    if ((szVar6 == null ? 0 : szVar6.d) != 0) {
                        printWriter.print(str3);
                        printWriter.print("getPopEnterAnim=");
                        sz szVar7 = vzVar.l0;
                        printWriter.println(szVar7 == null ? 0 : szVar7.d);
                    }
                    sz szVar8 = vzVar.l0;
                    if ((szVar8 == null ? 0 : szVar8.e) != 0) {
                        printWriter.print(str3);
                        printWriter.print("getPopExitAnim=");
                        sz szVar9 = vzVar.l0;
                        printWriter.println(szVar9 == null ? 0 : szVar9.e);
                    }
                    if (vzVar.h0 != null) {
                        printWriter.print(str3);
                        printWriter.print("mContainer=");
                        printWriter.println(vzVar.h0);
                    }
                    if (vzVar.i0 != null) {
                        printWriter.print(str3);
                        printWriter.print("mView=");
                        printWriter.println(vzVar.i0);
                    }
                    if (vzVar.g() != null) {
                        lw0 lw0Var = ((ga0) new z8(vzVar.d(), ga0.c).y(ga0.class)).b;
                        if (lw0Var.G > 0) {
                            printWriter.print(str3);
                            printWriter.println("Loaders:");
                            if (lw0Var.G > 0) {
                                if (lw0Var.F[0] != null) {
                                    throw new ClassCastException();
                                }
                                printWriter.print(str3);
                                printWriter.print("  #");
                                printWriter.print(lw0Var.E[0]);
                                printWriter.print(": ");
                                throw null;
                            }
                        }
                    }
                    printWriter.print(str3);
                    printWriter.println("Child " + vzVar.X + ":");
                    vzVar.X.q(zn.m(str3, "  "), fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        ArrayList arrayList = (ArrayList) ouVar.F;
        int size3 = arrayList.size();
        if (size3 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i = 0; i < size3; i++) {
                vz vzVar2 = (vz) arrayList.get(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(vzVar2.toString());
            }
        }
        ArrayList arrayList2 = this.e;
        if (arrayList2 != null && (size2 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size2; i2++) {
                vz vzVar3 = (vz) this.e.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(vzVar3.toString());
            }
        }
        ArrayList arrayList3 = this.d;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size; i3++) {
                m9 m9Var = (m9) this.d.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(m9Var.toString());
                m9Var.f(strM, printWriter, true);
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
                        Object obj = (e00) this.a.get(i4);
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

    public final void r(e00 e00Var, boolean z) {
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
                    this.a.add(e00Var);
                    N();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void removeFragmentOnAttachListener(k00 k00Var) {
        this.m.remove(k00Var);
    }

    public void removeOnBackStackChangedListener(d00 d00Var) {
        ArrayList arrayList = this.k;
        if (arrayList != null) {
            arrayList.remove(d00Var);
        }
    }

    public final void s(boolean z) {
        if (this.b) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.o == null) {
            if (!this.B) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.o.d0.getLooper()) {
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

    public final boolean t(boolean z) {
        boolean zA;
        s(z);
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
                            zA |= ((e00) this.a.get(i)).a(arrayList, arrayList2);
                        }
                        this.a.clear();
                        this.o.d0.removeCallbacks(this.H);
                    }
                } finally {
                }
            }
            if (!zA) {
                break;
            }
            z2 = true;
            this.b = true;
            try {
                K(this.D, this.E);
            } finally {
                d();
            }
        }
        U();
        if (this.C) {
            this.C = false;
            T();
        }
        ((HashMap) this.c.G).values().removeAll(Collections.singleton(null));
        return z2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(Uuid.SIZE_BITS);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        vz vzVar = this.q;
        if (vzVar != null) {
            sb.append(vzVar.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.q)));
            sb.append(JsonUtils.CLOSE);
        } else {
            wz wzVar = this.o;
            if (wzVar != null) {
                sb.append(wzVar.getClass().getSimpleName());
                sb.append("{");
                sb.append(Integer.toHexString(System.identityHashCode(this.o)));
                sb.append(JsonUtils.CLOSE);
            } else {
                sb.append("null");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    public final void u(m9 m9Var, boolean z) {
        if (z && (this.o == null || this.B)) {
            return;
        }
        s(z);
        m9Var.a(this.D, this.E);
        this.b = true;
        try {
            K(this.D, this.E);
            d();
            U();
            if (this.C) {
                this.C = false;
                T();
            }
            ((HashMap) this.c.G).values().removeAll(Collections.singleton(null));
        } catch (Throwable th) {
            d();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x0156  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void v(java.util.ArrayList r23, java.util.ArrayList r24, int r25, int r26) {
        /*
            Method dump skipped, instruction units count: 1258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.c.v(java.util.ArrayList, java.util.ArrayList, int, int):void");
    }

    public final vz w(int i) {
        ou ouVar = this.c;
        ArrayList arrayList = (ArrayList) ouVar.F;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            vz vzVar = (vz) arrayList.get(size);
            if (vzVar != null && vzVar.Z == i) {
                return vzVar;
            }
        }
        for (d dVar : ((HashMap) ouVar.G).values()) {
            if (dVar != null) {
                vz vzVar2 = dVar.c;
                if (vzVar2.Z == i) {
                    return vzVar2;
                }
            }
        }
        return null;
    }

    public final ViewGroup x(vz vzVar) {
        ViewGroup viewGroup = vzVar.h0;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (vzVar.a0 <= 0 || !this.p.X()) {
            return null;
        }
        View viewW = this.p.W(vzVar.a0);
        if (viewW instanceof ViewGroup) {
            return (ViewGroup) viewW;
        }
        return null;
    }

    public final b00 y() {
        vz vzVar = this.q;
        return vzVar != null ? vzVar.V.y() : this.s;
    }

    public final di z() {
        vz vzVar = this.q;
        return vzVar != null ? vzVar.V.z() : this.t;
    }
}
