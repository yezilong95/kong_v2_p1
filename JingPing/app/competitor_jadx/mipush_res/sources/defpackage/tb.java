package defpackage;

import android.R;
import android.content.Context;
import android.graphics.Typeface;
import android.util.SparseArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import androidx.viewpager2.widget.ViewPager2;
import io.nats.client.support.NatsJetStreamConstants;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class tb {
    public Object a;
    public Object b;
    public Object c;
    public Object d;

    public tb(r51 r51Var, q51 q51Var, pj pjVar) {
        t40.n(r51Var, "store");
        t40.n(pjVar, "defaultExtras");
        this.a = r51Var;
        this.b = q51Var;
        this.c = pjVar;
        this.d = new er0();
    }

    public void a(Object obj, ArrayList arrayList, HashSet hashSet) {
        if (arrayList.contains(obj)) {
            return;
        }
        if (hashSet.contains(obj)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(obj);
        ArrayList arrayList2 = (ArrayList) ((vv0) this.b).getOrDefault(obj, null);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                a(arrayList2.get(i), arrayList, hashSet);
            }
        }
        hashSet.remove(obj);
        arrayList.add(obj);
    }

    public zx0 b(l3 l3Var) {
        ArrayList arrayList = (ArrayList) this.c;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            zx0 zx0Var = (zx0) arrayList.get(i);
            if (zx0Var != null && zx0Var.b == l3Var) {
                return zx0Var;
            }
        }
        zx0 zx0Var2 = new zx0((Context) this.b, l3Var);
        arrayList.add(zx0Var2);
        return zx0Var2;
    }

    public o51 c(df dfVar, String str) {
        o51 o51Var;
        boolean zIsInstance;
        o51 o51VarA;
        t40.n(str, "key");
        synchronized (((er0) this.d)) {
            try {
                r51 r51Var = (r51) this.a;
                r51Var.getClass();
                o51Var = (o51) r51Var.a.get(str);
                Class clsX = dfVar.E;
                t40.n(clsX, "jClass");
                Map map = df.F;
                t40.l(map, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>");
                Integer num = (Integer) map.get(clsX);
                if (num != null) {
                    zIsInstance = l21.a0(num.intValue(), o51Var);
                } else {
                    if (clsX.isPrimitive()) {
                        clsX = z91.x(yn0.a(clsX));
                    }
                    zIsInstance = clsX.isInstance(o51Var);
                }
                if (zIsInstance) {
                    q51 q51Var = (q51) this.b;
                    if (q51Var instanceof lr0) {
                        t40.k(o51Var);
                    }
                    t40.l(o51Var, "null cannot be cast to non-null type T of androidx.lifecycle.viewmodel.ViewModelProviderImpl.getViewModel");
                } else {
                    vf0 vf0Var = new vf0((pj) this.c);
                    vf0Var.a.put(z8.H, str);
                    q51 q51Var2 = (q51) this.b;
                    try {
                        try {
                            o51VarA = q51Var2.c(dfVar, vf0Var);
                        } catch (AbstractMethodError unused) {
                            Class clsA = dfVar.a();
                            t40.l(clsA, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
                            o51VarA = q51Var2.a(clsA);
                        }
                    } catch (AbstractMethodError unused2) {
                        Class clsA2 = dfVar.a();
                        t40.l(clsA2, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
                        o51VarA = q51Var2.b(clsA2, vf0Var);
                    }
                    o51Var = o51VarA;
                    r51 r51Var2 = (r51) this.a;
                    r51Var2.getClass();
                    t40.n(o51Var, "viewModel");
                    o51 o51Var2 = (o51) r51Var2.a.put(str, o51Var);
                    if (o51Var2 != null) {
                        o51Var2.a();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return o51Var;
    }

    public boolean d(l3 l3Var, MenuItem menuItem) {
        return ((ActionMode.Callback) this.a).onActionItemClicked(b(l3Var), new he0((Context) this.b, (dy0) menuItem));
    }

    public boolean e(l3 l3Var, zd0 zd0Var) {
        zx0 zx0VarB = b(l3Var);
        vv0 vv0Var = (vv0) this.d;
        Menu te0Var = (Menu) vv0Var.getOrDefault(zd0Var, null);
        if (te0Var == null) {
            te0Var = new te0((Context) this.b, zd0Var);
            vv0Var.put(zd0Var, te0Var);
        }
        return ((ActionMode.Callback) this.a).onCreateActionMode(zx0VarB, te0Var);
    }

    public void f() {
        int iA;
        int i = R.id.accessibilityActionPageLeft;
        ViewPager2 viewPager2 = (ViewPager2) this.d;
        f51.l(viewPager2, R.id.accessibilityActionPageLeft);
        f51.i(viewPager2, 0);
        f51.l(viewPager2, R.id.accessibilityActionPageRight);
        f51.i(viewPager2, 0);
        f51.l(viewPager2, R.id.accessibilityActionPageUp);
        f51.i(viewPager2, 0);
        f51.l(viewPager2, R.id.accessibilityActionPageDown);
        f51.i(viewPager2, 0);
        if (viewPager2.getAdapter() == null || (iA = viewPager2.getAdapter().a()) == 0 || !viewPager2.V) {
            return;
        }
        int orientation = viewPager2.getOrientation();
        wl0 wl0Var = (wl0) this.b;
        cw0 cw0Var = (cw0) this.a;
        if (orientation != 0) {
            if (viewPager2.H < iA - 1) {
                f51.m(viewPager2, new e2(R.id.accessibilityActionPageDown), null, cw0Var);
            }
            if (viewPager2.H > 0) {
                f51.m(viewPager2, new e2(R.id.accessibilityActionPageUp), null, wl0Var);
                return;
            }
            return;
        }
        boolean z = viewPager2.K.C() == 1;
        int i2 = z ? 16908360 : 16908361;
        if (z) {
            i = 16908361;
        }
        if (viewPager2.H < iA - 1) {
            f51.m(viewPager2, new e2(i2), null, cw0Var);
        }
        if (viewPager2.H > 0) {
            f51.m(viewPager2, new e2(i), null, wl0Var);
        }
    }

    public tb(int i) {
        switch (i) {
            case 5:
                this.a = new gq0(Uuid.SIZE_BITS);
                this.d = new gq0(NatsJetStreamConstants.MAX_PULL_SIZE);
                this.c = new dq0(NatsJetStreamConstants.MAX_PULL_SIZE);
                this.b = new dq0(512);
                break;
            case 6:
                this.a = new k8();
                this.b = new SparseArray();
                this.c = new cb0();
                this.d = new k8();
                break;
            default:
                this.a = new al0(10);
                this.b = new vv0();
                this.c = new ArrayList();
                this.d = new HashSet();
                break;
        }
    }

    public tb(Typeface typeface, we0 we0Var) {
        int i;
        int i2;
        int i3;
        int i4;
        this.d = typeface;
        this.a = we0Var;
        this.c = new xe0(1024);
        int iA = we0Var.a(6);
        if (iA != 0) {
            int i5 = iA + we0Var.E;
            i = ((ByteBuffer) we0Var.H).getInt(((ByteBuffer) we0Var.H).getInt(i5) + i5);
        } else {
            i = 0;
        }
        this.b = new char[i * 2];
        int iA2 = we0Var.a(6);
        if (iA2 != 0) {
            int i6 = iA2 + we0Var.E;
            i2 = ((ByteBuffer) we0Var.H).getInt(((ByteBuffer) we0Var.H).getInt(i6) + i6);
        } else {
            i2 = 0;
        }
        for (int i7 = 0; i7 < i2; i7++) {
            ht htVar = new ht(this, i7);
            ve0 ve0VarB = htVar.b();
            int iA3 = ve0VarB.a(4);
            Character.toChars(iA3 != 0 ? ((ByteBuffer) ve0VarB.H).getInt(iA3 + ve0VarB.E) : 0, (char[]) this.b, i7 * 2);
            ve0 ve0VarB2 = htVar.b();
            int iA4 = ve0VarB2.a(16);
            if (iA4 != 0) {
                int i8 = iA4 + ve0VarB2.E;
                i3 = ((ByteBuffer) ve0VarB2.H).getInt(((ByteBuffer) ve0VarB2.H).getInt(i8) + i8);
            } else {
                i3 = 0;
            }
            la0.f("invalid metadata codepoint length", i3 > 0);
            ve0 ve0VarB3 = htVar.b();
            int iA5 = ve0VarB3.a(16);
            if (iA5 != 0) {
                int i9 = iA5 + ve0VarB3.E;
                i4 = ((ByteBuffer) ve0VarB3.H).getInt(((ByteBuffer) ve0VarB3.H).getInt(i9) + i9);
            } else {
                i4 = 0;
            }
            ((xe0) this.c).a(htVar, 0, i4 - 1);
        }
    }
}
