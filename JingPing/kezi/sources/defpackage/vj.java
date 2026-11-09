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
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class vj {
    public final ViewGroup a;
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public boolean d = false;
    public boolean e = false;

    public vj(ViewGroup viewGroup) {
        this.a = viewGroup;
    }

    public static vj f(ViewGroup viewGroup, yo yoVar) {
        Object tag = viewGroup.getTag(R$id.special_effects_controller_view_tag);
        if (tag instanceof vj) {
            return (vj) tag;
        }
        yoVar.getClass();
        vj vjVar = new vj(viewGroup);
        viewGroup.setTag(R$id.special_effects_controller_view_tag, vjVar);
        return vjVar;
    }

    public final void a(int i, int i2, d dVar) {
        synchronized (this.b) {
            try {
                oa oaVar = new oa();
                zj0 zj0VarD = d(dVar.c);
                if (zj0VarD != null) {
                    zj0VarD.c(i, i2);
                    return;
                }
                zj0 zj0Var = new zj0(i, i2, dVar, oaVar);
                this.b.add(zj0Var);
                zj0Var.d.add(new yj0(this, zj0Var, 0));
                zj0Var.d.add(new yj0(this, zj0Var, 1));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b(ArrayList arrayList, boolean z) {
        int i;
        int i2;
        Iterator it = arrayList.iterator();
        zj0 zj0Var = null;
        zj0 zj0Var2 = null;
        while (true) {
            i = 2;
            if (!it.hasNext()) {
                break;
            }
            zj0 zj0Var3 = (zj0) it.next();
            int iC = hk.c(zj0Var3.c.F);
            int iO = hk.o(zj0Var3.a);
            if (iO != 0) {
                if (iO != 1) {
                    if (iO == 2 || iO == 3) {
                    }
                } else if (iC != 2) {
                    zj0Var2 = zj0Var3;
                }
            }
            if (iC == 2 && zj0Var == null) {
                zj0Var = zj0Var3;
            }
        }
        ArrayList<tj> arrayList2 = new ArrayList();
        ArrayList<uj> arrayList3 = new ArrayList();
        ArrayList<zj0> arrayList4 = new ArrayList(arrayList);
        Iterator it2 = arrayList.iterator();
        while (true) {
            boolean z2 = false;
            if (!it2.hasNext()) {
                break;
            }
            zj0 zj0Var4 = (zj0) it2.next();
            oa oaVar = new oa();
            zj0Var4.d();
            HashSet hashSet = zj0Var4.e;
            hashSet.add(oaVar);
            tj tjVar = new tj(zj0Var4, oaVar);
            tjVar.d = false;
            tjVar.c = z;
            arrayList2.add(tjVar);
            oa oaVar2 = new oa();
            zj0Var4.d();
            hashSet.add(oaVar2);
            if (!z ? zj0Var4 == zj0Var2 : zj0Var4 == zj0Var) {
                z2 = true;
            }
            uj ujVar = new uj(zj0Var4, oaVar2);
            int i3 = zj0Var4.a;
            jt jtVar = zj0Var4.c;
            if (i3 == 2) {
                if (z) {
                    jtVar.getClass();
                } else {
                    jtVar.getClass();
                }
                if (z) {
                    jtVar.getClass();
                } else {
                    jtVar.getClass();
                }
            } else if (z) {
                jtVar.getClass();
            } else {
                jtVar.getClass();
            }
            if (z2) {
                if (z) {
                    jtVar.getClass();
                } else {
                    jtVar.getClass();
                }
            }
            arrayList3.add(ujVar);
            zj0Var4.d.add(new qj(this, arrayList4, zj0Var4));
        }
        HashMap map = new HashMap();
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            zj0 zj0Var5 = (zj0) ((uj) it3.next()).a;
            hk.c(zj0Var5.c.F);
            int i4 = zj0Var5.a;
        }
        for (uj ujVar2 : arrayList3) {
            map.put((zj0) ujVar2.a, Boolean.FALSE);
            ujVar2.d();
        }
        boolean zContainsValue = map.containsValue(Boolean.TRUE);
        ViewGroup viewGroup = this.a;
        Context context = viewGroup.getContext();
        ArrayList<tj> arrayList5 = new ArrayList();
        boolean z3 = false;
        for (tj tjVar2 : arrayList2) {
            zj0 zj0Var6 = (zj0) tjVar2.a;
            int iC2 = hk.c(zj0Var6.c.F);
            int i5 = zj0Var6.a;
            if (iC2 == i5 || !(iC2 == i || i5 == i)) {
                i2 = i;
                tjVar2.d();
            } else {
                e5 e5VarJ = tjVar2.j(context);
                if (e5VarJ == null) {
                    tjVar2.d();
                } else {
                    Animator animator = (Animator) e5VarJ.d;
                    if (animator == null) {
                        arrayList5.add(tjVar2);
                    } else {
                        zj0 zj0Var7 = (zj0) tjVar2.a;
                        i2 = i;
                        boolean zEquals = Boolean.TRUE.equals(map.get(zj0Var7));
                        jt jtVar2 = zj0Var7.c;
                        if (zEquals) {
                            if (c.C(i2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + jtVar2 + " as this Fragment was involved in a Transition.");
                            }
                            tjVar2.d();
                        } else {
                            boolean z4 = zj0Var7.a == 3;
                            if (z4) {
                                arrayList4.remove(zj0Var7);
                            }
                            View view = jtVar2.F;
                            viewGroup.startViewTransition(view);
                            animator.addListener(new rj(viewGroup, view, z4, zj0Var7, tjVar2));
                            animator.setTarget(view);
                            animator.start();
                            v3 v3Var = new v3();
                            v3Var.b = animator;
                            ((oa) tjVar2.b).setOnCancelListener(v3Var);
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
        for (tj tjVar3 : arrayList5) {
            zj0 zj0Var8 = (zj0) tjVar3.a;
            jt jtVar3 = zj0Var8.c;
            if (zContainsValue) {
                if (c.C(i6)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + jtVar3 + " as Animations cannot run alongside Transitions.");
                }
                tjVar3.d();
            } else if (z3) {
                if (c.C(i6)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + jtVar3 + " as Animations cannot run alongside Animators.");
                }
                tjVar3.d();
            } else {
                View view2 = jtVar3.F;
                e5 e5VarJ2 = tjVar3.j(context);
                e5VarJ2.getClass();
                Animation animation = (Animation) e5VarJ2.c;
                animation.getClass();
                if (zj0Var8.a != 1) {
                    view2.startAnimation(animation);
                    tjVar3.d();
                } else {
                    viewGroup.startViewTransition(view2);
                    lt ltVar = new lt(animation, viewGroup, view2);
                    ltVar.setAnimationListener(new sj(viewGroup, view2, tjVar3));
                    view2.startAnimation(ltVar);
                }
                ((oa) tjVar3.b).setOnCancelListener(new po(view2, viewGroup, tjVar3, 5));
            }
        }
        for (zj0 zj0Var9 : arrayList4) {
            hk.a(zj0Var9.c.F, zj0Var9.a);
        }
        arrayList4.clear();
    }

    public final void c() {
        if (this.e) {
            return;
        }
        ViewGroup viewGroup = this.a;
        WeakHashMap weakHashMap = xr0.a;
        if (!viewGroup.isAttachedToWindow()) {
            e();
            this.d = false;
            return;
        }
        synchronized (this.b) {
            try {
                if (!this.b.isEmpty()) {
                    ArrayList<zj0> arrayList = new ArrayList(this.c);
                    this.c.clear();
                    for (zj0 zj0Var : arrayList) {
                        if (c.C(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Cancelling operation " + zj0Var);
                        }
                        zj0Var.a();
                        if (!zj0Var.g) {
                            this.c.add(zj0Var);
                        }
                    }
                    g();
                    ArrayList arrayList2 = new ArrayList(this.b);
                    this.b.clear();
                    this.c.addAll(arrayList2);
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        ((zj0) it.next()).d();
                    }
                    b(arrayList2, this.d);
                    this.d = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final zj0 d(jt jtVar) {
        for (zj0 zj0Var : this.b) {
            if (zj0Var.c.equals(jtVar) && !zj0Var.f) {
                return zj0Var;
            }
        }
        return null;
    }

    public final void e() {
        ViewGroup viewGroup = this.a;
        WeakHashMap weakHashMap = xr0.a;
        boolean zIsAttachedToWindow = viewGroup.isAttachedToWindow();
        synchronized (this.b) {
            try {
                g();
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    ((zj0) it.next()).d();
                }
                for (zj0 zj0Var : new ArrayList(this.c)) {
                    if (c.C(2)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("SpecialEffectsController: ");
                        sb.append(zIsAttachedToWindow ? ExtensionRequestData.EMPTY_VALUE : "Container " + this.a + " is not attached to window. ");
                        sb.append("Cancelling running operation ");
                        sb.append(zj0Var);
                        Log.v("FragmentManager", sb.toString());
                    }
                    zj0Var.a();
                }
                for (zj0 zj0Var2 : new ArrayList(this.b)) {
                    if (c.C(2)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("SpecialEffectsController: ");
                        sb2.append(zIsAttachedToWindow ? ExtensionRequestData.EMPTY_VALUE : "Container " + this.a + " is not attached to window. ");
                        sb2.append("Cancelling pending operation ");
                        sb2.append(zj0Var2);
                        Log.v("FragmentManager", sb2.toString());
                    }
                    zj0Var2.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void g() {
        for (zj0 zj0Var : this.b) {
            if (zj0Var.b == 2) {
                zj0Var.c(hk.b(zj0Var.c.z().getVisibility()), 1);
            }
        }
    }
}
