package defpackage;

import android.animation.Animator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.fragment.R$id;
import androidx.fragment.app.c;
import androidx.fragment.app.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class on {
    public final ViewGroup a;
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public boolean d = false;
    public boolean e = false;

    public on(ViewGroup viewGroup) {
        this.a = viewGroup;
    }

    public static on f(ViewGroup viewGroup, di diVar) {
        Object tag = viewGroup.getTag(R$id.special_effects_controller_view_tag);
        if (tag instanceof on) {
            return (on) tag;
        }
        diVar.getClass();
        on onVar = new on(viewGroup);
        viewGroup.setTag(R$id.special_effects_controller_view_tag, onVar);
        return onVar;
    }

    public final void a(int i, int i2, d dVar) {
        synchronized (this.b) {
            try {
                gc gcVar = new gc();
                nw0 nw0VarD = d(dVar.c);
                if (nw0VarD != null) {
                    nw0VarD.c(i, i2);
                    return;
                }
                nw0 nw0Var = new nw0(i, i2, dVar, gcVar);
                this.b.add(nw0Var);
                nw0Var.d.add(new mw0(this, nw0Var, 0));
                nw0Var.d.add(new mw0(this, nw0Var, 1));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b(ArrayList arrayList, boolean z) {
        int i;
        int i2;
        Iterator it = arrayList.iterator();
        nw0 nw0Var = null;
        nw0 nw0Var2 = null;
        while (true) {
            i = 2;
            if (!it.hasNext()) {
                break;
            }
            nw0 nw0Var3 = (nw0) it.next();
            int iC = zn.c(nw0Var3.c.i0);
            int iW = zn.w(nw0Var3.a);
            if (iW != 0) {
                if (iW != 1) {
                    if (iW == 2 || iW == 3) {
                    }
                } else if (iC != 2) {
                    nw0Var2 = nw0Var3;
                }
            }
            if (iC == 2 && nw0Var == null) {
                nw0Var = nw0Var3;
            }
        }
        ArrayList<mn> arrayList2 = new ArrayList();
        ArrayList<nn> arrayList3 = new ArrayList();
        ArrayList<nw0> arrayList4 = new ArrayList(arrayList);
        Iterator it2 = arrayList.iterator();
        while (true) {
            boolean z2 = false;
            if (!it2.hasNext()) {
                break;
            }
            nw0 nw0Var4 = (nw0) it2.next();
            gc gcVar = new gc();
            nw0Var4.d();
            HashSet hashSet = nw0Var4.e;
            hashSet.add(gcVar);
            mn mnVar = new mn(nw0Var4, gcVar);
            mnVar.d = false;
            mnVar.c = z;
            arrayList2.add(mnVar);
            gc gcVar2 = new gc();
            nw0Var4.d();
            hashSet.add(gcVar2);
            if (!z ? nw0Var4 == nw0Var2 : nw0Var4 == nw0Var) {
                z2 = true;
            }
            nn nnVar = new nn(nw0Var4, gcVar2);
            int i3 = nw0Var4.a;
            vz vzVar = nw0Var4.c;
            if (i3 == 2) {
                if (z) {
                    vzVar.getClass();
                } else {
                    vzVar.getClass();
                }
                if (z) {
                    vzVar.getClass();
                } else {
                    vzVar.getClass();
                }
            } else if (z) {
                vzVar.getClass();
            } else {
                vzVar.getClass();
            }
            if (z2) {
                if (z) {
                    vzVar.getClass();
                } else {
                    vzVar.getClass();
                }
            }
            arrayList3.add(nnVar);
            nw0Var4.d.add(new jn(this, arrayList4, nw0Var4));
        }
        HashMap map = new HashMap();
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            nw0 nw0Var5 = (nw0) ((nn) it3.next()).a;
            zn.c(nw0Var5.c.i0);
            int i4 = nw0Var5.a;
        }
        for (nn nnVar2 : arrayList3) {
            map.put((nw0) nnVar2.a, Boolean.FALSE);
            nnVar2.e();
        }
        boolean zContainsValue = map.containsValue(Boolean.TRUE);
        ViewGroup viewGroup = this.a;
        Context context = viewGroup.getContext();
        ArrayList<mn> arrayList5 = new ArrayList();
        boolean z3 = false;
        for (mn mnVar2 : arrayList2) {
            nw0 nw0Var6 = (nw0) mnVar2.a;
            int iC2 = zn.c(nw0Var6.c.i0);
            int i5 = nw0Var6.a;
            if (iC2 == i5 || !(iC2 == i || i5 == i)) {
                i2 = i;
                mnVar2.e();
            } else {
                i6 i6VarQ = mnVar2.q(context);
                if (i6VarQ == null) {
                    mnVar2.e();
                } else {
                    Animator animator = (Animator) i6VarQ.G;
                    if (animator == null) {
                        arrayList5.add(mnVar2);
                    } else {
                        nw0 nw0Var7 = (nw0) mnVar2.a;
                        i2 = i;
                        boolean zEquals = Boolean.TRUE.equals(map.get(nw0Var7));
                        vz vzVar2 = nw0Var7.c;
                        if (zEquals) {
                            if (c.B(i2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + vzVar2 + " as this Fragment was involved in a Transition.");
                            }
                            mnVar2.e();
                        } else {
                            boolean z4 = nw0Var7.a == 3;
                            if (z4) {
                                arrayList4.remove(nw0Var7);
                            }
                            View view = vzVar2.i0;
                            viewGroup.startViewTransition(view);
                            animator.addListener(new kn(viewGroup, view, z4, nw0Var7, mnVar2));
                            animator.setTarget(view);
                            animator.start();
                            ((gc) mnVar2.b).setOnCancelListener(new z8(15, animator));
                            i = i2;
                            z3 = true;
                        }
                    }
                }
                i2 = i;
            }
            i = i2;
        }
        int i6 = i;
        for (mn mnVar3 : arrayList5) {
            nw0 nw0Var8 = (nw0) mnVar3.a;
            vz vzVar3 = nw0Var8.c;
            if (zContainsValue) {
                if (c.B(i6)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + vzVar3 + " as Animations cannot run alongside Transitions.");
                }
                mnVar3.e();
            } else if (z3) {
                if (c.B(i6)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + vzVar3 + " as Animations cannot run alongside Animators.");
                }
                mnVar3.e();
            } else {
                View view2 = vzVar3.i0;
                i6 i6VarQ2 = mnVar3.q(context);
                i6VarQ2.getClass();
                Animation animation = (Animation) i6VarQ2.F;
                animation.getClass();
                if (nw0Var8.a != 1) {
                    view2.startAnimation(animation);
                    mnVar3.e();
                } else {
                    viewGroup.startViewTransition(view2);
                    xz xzVar = new xz(animation, viewGroup, view2);
                    xzVar.setAnimationListener(new ln(viewGroup, view2, mnVar3));
                    view2.startAnimation(xzVar);
                }
                ((gc) mnVar3.b).setOnCancelListener(new ou(view2, viewGroup, mnVar3, 6));
            }
        }
        for (nw0 nw0Var9 : arrayList4) {
            zn.a(nw0Var9.c.i0, nw0Var9.a);
        }
        arrayList4.clear();
    }

    public final void c() {
        if (this.e) {
            return;
        }
        ViewGroup viewGroup = this.a;
        WeakHashMap weakHashMap = f51.a;
        if (!viewGroup.isAttachedToWindow()) {
            e();
            this.d = false;
            return;
        }
        synchronized (this.b) {
            try {
                if (!this.b.isEmpty()) {
                    ArrayList<nw0> arrayList = new ArrayList(this.c);
                    this.c.clear();
                    for (nw0 nw0Var : arrayList) {
                        if (c.B(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Cancelling operation " + nw0Var);
                        }
                        nw0Var.a();
                        if (!nw0Var.g) {
                            this.c.add(nw0Var);
                        }
                    }
                    g();
                    ArrayList arrayList2 = new ArrayList(this.b);
                    this.b.clear();
                    this.c.addAll(arrayList2);
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        ((nw0) it.next()).d();
                    }
                    b(arrayList2, this.d);
                    this.d = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final nw0 d(vz vzVar) {
        for (nw0 nw0Var : this.b) {
            if (nw0Var.c.equals(vzVar) && !nw0Var.f) {
                return nw0Var;
            }
        }
        return null;
    }

    public final void e() {
        ViewGroup viewGroup = this.a;
        WeakHashMap weakHashMap = f51.a;
        boolean zIsAttachedToWindow = viewGroup.isAttachedToWindow();
        synchronized (this.b) {
            try {
                g();
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    ((nw0) it.next()).d();
                }
                for (nw0 nw0Var : new ArrayList(this.c)) {
                    if (c.B(2)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("SpecialEffectsController: ");
                        sb.append(zIsAttachedToWindow ? "" : "Container " + this.a + " is not attached to window. ");
                        sb.append("Cancelling running operation ");
                        sb.append(nw0Var);
                        Log.v("FragmentManager", sb.toString());
                    }
                    nw0Var.a();
                }
                for (nw0 nw0Var2 : new ArrayList(this.b)) {
                    if (c.B(2)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("SpecialEffectsController: ");
                        sb2.append(zIsAttachedToWindow ? "" : "Container " + this.a + " is not attached to window. ");
                        sb2.append("Cancelling pending operation ");
                        sb2.append(nw0Var2);
                        Log.v("FragmentManager", sb2.toString());
                    }
                    nw0Var2.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void g() {
        for (nw0 nw0Var : this.b) {
            if (nw0Var.b == 2) {
                nw0Var.c(zn.b(nw0Var.c.z().getVisibility()), 1);
            }
        }
    }
}
