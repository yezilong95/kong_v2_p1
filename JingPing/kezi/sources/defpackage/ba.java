package defpackage;

import android.R;
import android.content.Context;
import android.graphics.Typeface;
import android.util.SparseArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import androidx.viewpager2.widget.ViewPager2;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ba {
    public Object a;
    public Object b;
    public Object c;
    public Object d;

    public ba(ks0 ks0Var, js0 js0Var, bi biVar) {
        oy.j(ks0Var, "store");
        oy.j(js0Var, "factory");
        oy.j(biVar, "defaultExtras");
        this.a = ks0Var;
        this.b = js0Var;
        this.c = biVar;
        this.d = new yo(24);
    }

    public void a(Object obj, ArrayList arrayList, HashSet hashSet) {
        if (arrayList.contains(obj)) {
            return;
        }
        if (hashSet.contains(obj)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(obj);
        ArrayList arrayList2 = (ArrayList) ((fj0) this.b).getOrDefault(obj, null);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                a(arrayList2.get(i), arrayList, hashSet);
            }
        }
        hashSet.remove(obj);
        arrayList.add(obj);
    }

    public void b(tc0 tc0Var) {
        tc0Var.c.decrementAndGet();
        ArrayDeque arrayDeque = (ArrayDeque) this.c;
        synchronized (this) {
            if (!arrayDeque.remove(tc0Var)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        g();
    }

    public ll0 c(q1 q1Var) {
        ArrayList arrayList = (ArrayList) this.c;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ll0 ll0Var = (ll0) arrayList.get(i);
            if (ll0Var != null && ll0Var.b == q1Var) {
                return ll0Var;
            }
        }
        ll0 ll0Var2 = new ll0((Context) this.b, q1Var);
        arrayList.add(ll0Var2);
        return ll0Var2;
    }

    public gs0 d(jd jdVar, String str) {
        gs0 gs0Var;
        boolean zIsInstance;
        gs0 gs0VarB;
        oy.j(str, "key");
        synchronized (((yo) this.d)) {
            try {
                ks0 ks0Var = (ks0) this.a;
                ks0Var.getClass();
                gs0Var = (gs0) ks0Var.a.get(str);
                Class clsY = jdVar.b;
                oy.j(clsY, "jClass");
                Map map = jd.c;
                oy.h(map, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>");
                Integer num = (Integer) map.get(clsY);
                if (num != null) {
                    zIsInstance = rp0.H(num.intValue(), gs0Var);
                } else {
                    if (clsY.isPrimitive()) {
                        clsY = jq0.y(ce0.a(clsY));
                    }
                    zIsInstance = clsY.isInstance(gs0Var);
                }
                if (zIsInstance) {
                    js0 js0Var = (js0) this.b;
                    if (js0Var instanceof wg0) {
                        wg0 wg0Var = (wg0) js0Var;
                        oy.g(gs0Var);
                        wg0Var.getClass();
                        p10 p10Var = wg0Var.e;
                        if (p10Var != null) {
                            e5 e5Var = wg0Var.f;
                            oy.g(e5Var);
                            za0.e(gs0Var, e5Var, p10Var);
                        }
                    }
                    oy.h(gs0Var, "null cannot be cast to non-null type T of androidx.lifecycle.viewmodel.ViewModelProviderImpl.getViewModel");
                } else {
                    b80 b80Var = new b80((bi) this.c);
                    b80Var.a.put(o70.e, str);
                    js0 js0Var2 = (js0) this.b;
                    oy.j(js0Var2, "factory");
                    try {
                        try {
                            gs0VarB = js0Var2.c(jdVar, b80Var);
                        } catch (AbstractMethodError unused) {
                            Class clsA = jdVar.a();
                            oy.h(clsA, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
                            gs0VarB = js0Var2.a(clsA, b80Var);
                        }
                    } catch (AbstractMethodError unused2) {
                        Class clsA2 = jdVar.a();
                        oy.h(clsA2, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
                        gs0VarB = js0Var2.b(clsA2);
                    }
                    gs0Var = gs0VarB;
                    ks0 ks0Var2 = (ks0) this.a;
                    ks0Var2.getClass();
                    oy.j(gs0Var, "viewModel");
                    gs0 gs0Var2 = (gs0) ks0Var2.a.put(str, gs0Var);
                    if (gs0Var2 != null) {
                        gs0Var2.a();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return gs0Var;
    }

    public boolean e(q1 q1Var, MenuItem menuItem) {
        return ((ActionMode.Callback) this.a).onActionItemClicked(c(q1Var), new q60((Context) this.b, (pl0) menuItem));
    }

    public boolean f(q1 q1Var, i60 i60Var) {
        ll0 ll0VarC = c(q1Var);
        fj0 fj0Var = (fj0) this.d;
        Menu c70Var = (Menu) fj0Var.getOrDefault(i60Var, null);
        if (c70Var == null) {
            c70Var = new c70((Context) this.b, i60Var);
            fj0Var.put(i60Var, c70Var);
        }
        return ((ActionMode.Callback) this.a).onCreateActionMode(ll0VarC, c70Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void g() {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ba.g():void");
    }

    public void h() {
        int iA;
        ViewPager2 viewPager2 = (ViewPager2) this.d;
        int i = R.id.accessibilityActionPageLeft;
        xr0.l(viewPager2, R.id.accessibilityActionPageLeft);
        xr0.m(viewPager2, R.id.accessibilityActionPageRight);
        xr0.i(viewPager2, 0);
        xr0.m(viewPager2, R.id.accessibilityActionPageUp);
        xr0.i(viewPager2, 0);
        xr0.m(viewPager2, R.id.accessibilityActionPageDown);
        xr0.i(viewPager2, 0);
        if (viewPager2.getAdapter() == null || (iA = viewPager2.getAdapter().a()) == 0 || !viewPager2.s) {
            return;
        }
        int orientation = viewPager2.getOrientation();
        ws0 ws0Var = (ws0) this.b;
        ws0 ws0Var2 = (ws0) this.a;
        if (orientation != 0) {
            if (viewPager2.e < iA - 1) {
                xr0.n(viewPager2, new j0(R.id.accessibilityActionPageDown), null, ws0Var2);
            }
            if (viewPager2.e > 0) {
                xr0.n(viewPager2, new j0(R.id.accessibilityActionPageUp), null, ws0Var);
                return;
            }
            return;
        }
        boolean z = viewPager2.h.A() == 1;
        int i2 = z ? 16908360 : 16908361;
        if (z) {
            i = 16908361;
        }
        if (viewPager2.e < iA - 1) {
            xr0.n(viewPager2, new j0(i2), null, ws0Var2);
        }
        if (viewPager2.e > 0) {
            xr0.n(viewPager2, new j0(i), null, ws0Var);
        }
    }

    public ba(int i) {
        switch (i) {
            case 2:
                this.b = new ArrayDeque();
                this.c = new ArrayDeque();
                this.d = new ArrayDeque();
                break;
            case 5:
                this.a = new c7();
                this.b = new SparseArray();
                this.c = new t30();
                this.d = new c7();
                break;
            default:
                this.a = new vb0(10);
                this.b = new fj0();
                this.c = new ArrayList();
                this.d = new HashSet();
                break;
        }
    }

    public ba(Typeface typeface, e70 e70Var) {
        int i;
        int i2;
        int i3;
        int i4;
        this.d = typeface;
        this.a = e70Var;
        this.c = new f70(1024);
        int iA = e70Var.a(6);
        if (iA != 0) {
            int i5 = iA + e70Var.b;
            i = ((ByteBuffer) e70Var.e).getInt(((ByteBuffer) e70Var.e).getInt(i5) + i5);
        } else {
            i = 0;
        }
        this.b = new char[i * 2];
        int iA2 = e70Var.a(6);
        if (iA2 != 0) {
            int i6 = iA2 + e70Var.b;
            i2 = ((ByteBuffer) e70Var.e).getInt(((ByteBuffer) e70Var.e).getInt(i6) + i6);
        } else {
            i2 = 0;
        }
        for (int i7 = 0; i7 < i2; i7++) {
            qn qnVar = new qn(this, i7);
            d70 d70VarB = qnVar.b();
            int iA3 = d70VarB.a(4);
            Character.toChars(iA3 != 0 ? ((ByteBuffer) d70VarB.e).getInt(iA3 + d70VarB.b) : 0, (char[]) this.b, i7 * 2);
            d70 d70VarB2 = qnVar.b();
            int iA4 = d70VarB2.a(16);
            if (iA4 != 0) {
                int i8 = iA4 + d70VarB2.b;
                i3 = ((ByteBuffer) d70VarB2.e).getInt(((ByteBuffer) d70VarB2.e).getInt(i8) + i8);
            } else {
                i3 = 0;
            }
            k9.f("invalid metadata codepoint length", i3 > 0);
            d70 d70VarB3 = qnVar.b();
            int iA5 = d70VarB3.a(16);
            if (iA5 != 0) {
                int i9 = iA5 + d70VarB3.b;
                i4 = ((ByteBuffer) d70VarB3.e).getInt(((ByteBuffer) d70VarB3.e).getInt(i9) + i9);
            } else {
                i4 = 0;
            }
            ((f70) this.c).a(qnVar, 0, i4 - 1);
        }
    }
}
