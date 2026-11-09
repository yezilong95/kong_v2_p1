package defpackage;

import android.util.Log;
import android.view.View;
import androidx.fragment.app.c;
import androidx.fragment.app.d;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class zj0 {
    public int a;
    public int b;
    public final jt c;
    public final ArrayList d = new ArrayList();
    public final HashSet e = new HashSet();
    public boolean f = false;
    public boolean g = false;
    public final d h;

    public zj0(int i, int i2, d dVar, oa oaVar) {
        this.a = i;
        this.b = i2;
        this.c = dVar.c;
        oaVar.setOnCancelListener(new d90(this));
        this.h = dVar;
    }

    public final void a() {
        if (this.f) {
            return;
        }
        this.f = true;
        HashSet hashSet = this.e;
        if (hashSet.isEmpty()) {
            b();
            return;
        }
        for (oa oaVar : new ArrayList(hashSet)) {
            synchronized (oaVar) {
                try {
                    if (!oaVar.a) {
                        oaVar.a = true;
                        oaVar.c = true;
                        na naVar = oaVar.b;
                        if (naVar != null) {
                            try {
                                naVar.h();
                            } catch (Throwable th) {
                                synchronized (oaVar) {
                                    oaVar.c = false;
                                    oaVar.notifyAll();
                                    throw th;
                                }
                            }
                        }
                        synchronized (oaVar) {
                            oaVar.c = false;
                            oaVar.notifyAll();
                        }
                    }
                } finally {
                }
            }
        }
    }

    public final void b() {
        if (!this.g) {
            if (c.C(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: " + this + " has called complete.");
            }
            this.g = true;
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }
        this.h.k();
    }

    public final void c(int i, int i2) {
        int iO = hk.o(i2);
        jt jtVar = this.c;
        if (iO == 0) {
            if (this.a != 1) {
                if (c.C(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + jtVar + " mFinalState = " + hk.q(this.a) + " -> " + hk.q(i) + ". ");
                }
                this.a = i;
                return;
            }
            return;
        }
        if (iO == 1) {
            if (this.a == 1) {
                if (c.C(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + jtVar + " mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = " + hk.p(this.b) + " to ADDING.");
                }
                this.a = 2;
                this.b = 2;
                return;
            }
            return;
        }
        if (iO != 2) {
            return;
        }
        if (c.C(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: For fragment " + jtVar + " mFinalState = " + hk.q(this.a) + " -> REMOVED. mLifecycleImpact  = " + hk.p(this.b) + " to REMOVING.");
        }
        this.a = 1;
        this.b = 3;
    }

    public final void d() {
        if (this.b == 2) {
            d dVar = this.h;
            jt jtVar = dVar.c;
            View viewFindFocus = jtVar.F.findFocus();
            if (viewFindFocus != null) {
                jtVar.e().k = viewFindFocus;
                if (c.C(2)) {
                    Log.v("FragmentManager", "requestFocus: Saved focused view " + viewFindFocus + " for Fragment " + jtVar);
                }
            }
            View viewZ = this.c.z();
            if (viewZ.getParent() == null) {
                dVar.b();
                viewZ.setAlpha(0.0f);
            }
            if (viewZ.getAlpha() == 0.0f && viewZ.getVisibility() == 0) {
                viewZ.setVisibility(4);
            }
            ft ftVar = jtVar.I;
            viewZ.setAlpha(ftVar == null ? 1.0f : ftVar.j);
        }
    }

    public final String toString() {
        return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {mFinalState = " + hk.q(this.a) + "} {mLifecycleImpact = " + hk.p(this.b) + "} {mFragment = " + this.c + "}";
    }
}
