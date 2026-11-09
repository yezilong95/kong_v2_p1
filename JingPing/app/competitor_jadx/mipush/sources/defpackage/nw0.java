package defpackage;

import android.util.Log;
import android.view.View;
import androidx.fragment.app.c;
import androidx.fragment.app.d;
import io.nats.client.support.JsonUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class nw0 {
    public int a;
    public int b;
    public final vz c;
    public final ArrayList d = new ArrayList();
    public final HashSet e = new HashSet();
    public boolean f = false;
    public boolean g = false;
    public final d h;

    public nw0(int i, int i2, d dVar, gc gcVar) {
        this.a = i;
        this.b = i2;
        this.c = dVar.c;
        gcVar.setOnCancelListener(new wl0(this));
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
        for (gc gcVar : new ArrayList(hashSet)) {
            synchronized (gcVar) {
                try {
                    if (!gcVar.a) {
                        gcVar.a = true;
                        gcVar.c = true;
                        fc fcVar = gcVar.b;
                        if (fcVar != null) {
                            try {
                                fcVar.d();
                            } catch (Throwable th) {
                                synchronized (gcVar) {
                                    gcVar.c = false;
                                    gcVar.notifyAll();
                                    throw th;
                                }
                            }
                        }
                        synchronized (gcVar) {
                            gcVar.c = false;
                            gcVar.notifyAll();
                        }
                    }
                } finally {
                }
            }
        }
    }

    public final void b() {
        if (!this.g) {
            if (c.B(2)) {
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
        int iW = zn.w(i2);
        vz vzVar = this.c;
        if (iW == 0) {
            if (this.a != 1) {
                if (c.B(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + vzVar + " mFinalState = " + zn.y(this.a) + " -> " + zn.y(i) + ". ");
                }
                this.a = i;
                return;
            }
            return;
        }
        if (iW == 1) {
            if (this.a == 1) {
                if (c.B(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + vzVar + " mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = " + zn.x(this.b) + " to ADDING.");
                }
                this.a = 2;
                this.b = 2;
                return;
            }
            return;
        }
        if (iW != 2) {
            return;
        }
        if (c.B(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: For fragment " + vzVar + " mFinalState = " + zn.y(this.a) + " -> REMOVED. mLifecycleImpact  = " + zn.x(this.b) + " to REMOVING.");
        }
        this.a = 1;
        this.b = 3;
    }

    public final void d() {
        if (this.b == 2) {
            d dVar = this.h;
            vz vzVar = dVar.c;
            View viewFindFocus = vzVar.i0.findFocus();
            if (viewFindFocus != null) {
                vzVar.e().k = viewFindFocus;
                if (c.B(2)) {
                    Log.v("FragmentManager", "requestFocus: Saved focused view " + viewFindFocus + " for Fragment " + vzVar);
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
            sz szVar = vzVar.l0;
            viewZ.setAlpha(szVar == null ? 1.0f : szVar.j);
        }
    }

    public final String toString() {
        return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {mFinalState = " + zn.y(this.a) + "} {mLifecycleImpact = " + zn.x(this.b) + "} {mFragment = " + this.c + JsonUtils.CLOSE;
    }
}
