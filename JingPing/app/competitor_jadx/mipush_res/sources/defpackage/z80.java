package defpackage;

import android.os.Looper;
import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;
import io.nats.client.support.ApiConstants;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class z80 extends s80 {
    public final boolean a;
    public tv b;
    public Lifecycle$State c;
    public final WeakReference d;
    public int e;
    public boolean f;
    public boolean g;
    public final ArrayList h;
    public final cx0 i;

    public z80(x80 x80Var) {
        new AtomicReference(null);
        this.a = true;
        this.b = new tv();
        Lifecycle$State lifecycle$State = Lifecycle$State.INITIALIZED;
        this.c = lifecycle$State;
        this.h = new ArrayList();
        this.d = new WeakReference(x80Var);
        this.i = new cx0(lifecycle$State == null ? j31.w : lifecycle$State);
    }

    @Override // defpackage.s80
    public final void a(w80 w80Var) {
        v80 hnVar;
        x80 x80Var;
        ArrayList arrayList = this.h;
        Object obj = null;
        int i = 1;
        d("addObserver");
        Lifecycle$State lifecycle$State = this.c;
        Lifecycle$State lifecycle$State2 = Lifecycle$State.DESTROYED;
        if (lifecycle$State != lifecycle$State2) {
            lifecycle$State2 = Lifecycle$State.INITIALIZED;
        }
        t40.n(lifecycle$State2, "initialState");
        y80 y80Var = new y80();
        HashMap map = a90.a;
        boolean z = w80Var instanceof v80;
        boolean z2 = w80Var instanceof at;
        if (z && z2) {
            hnVar = new hn((at) w80Var, i, (v80) w80Var);
        } else if (z2) {
            hnVar = new hn((at) w80Var, i, obj);
        } else if (z) {
            hnVar = (v80) w80Var;
        } else {
            Class<?> cls = w80Var.getClass();
            if (a90.c(cls) == 2) {
                Object obj2 = a90.b.get(cls);
                t40.k(obj2);
                List list = (List) obj2;
                if (list.size() == 1) {
                    a90.a((Constructor) list.get(0), w80Var);
                    throw null;
                }
                int size = list.size();
                b20[] b20VarArr = new b20[size];
                if (size > 0) {
                    a90.a((Constructor) list.get(0), w80Var);
                    throw null;
                }
                hnVar = new ym0(i, b20VarArr);
            } else {
                hnVar = new hn(w80Var);
            }
        }
        y80Var.b = hnVar;
        y80Var.a = lifecycle$State2;
        tv tvVar = this.b;
        vq0 vq0VarA = tvVar.a(w80Var);
        if (vq0VarA != null) {
            obj = vq0VarA.F;
        } else {
            HashMap map2 = tvVar.I;
            vq0 vq0Var = new vq0(w80Var, y80Var);
            tvVar.H++;
            vq0 vq0Var2 = tvVar.F;
            if (vq0Var2 == null) {
                tvVar.E = vq0Var;
                tvVar.F = vq0Var;
            } else {
                vq0Var2.G = vq0Var;
                vq0Var.H = vq0Var2;
                tvVar.F = vq0Var;
            }
            map2.put(w80Var, vq0Var);
        }
        if (((y80) obj) == null && (x80Var = (x80) this.d.get()) != null) {
            i = (this.e != 0 || this.f) ? 1 : 0;
            Lifecycle$State lifecycle$StateC = c(w80Var);
            this.e++;
            while (y80Var.a.compareTo(lifecycle$StateC) < 0 && this.b.I.containsKey(w80Var)) {
                arrayList.add(y80Var.a);
                q80 q80Var = Lifecycle$Event.Companion;
                Lifecycle$State lifecycle$State3 = y80Var.a;
                q80Var.getClass();
                Lifecycle$Event lifecycle$EventB = q80.b(lifecycle$State3);
                if (lifecycle$EventB == null) {
                    throw new IllegalStateException("no event up from " + y80Var.a);
                }
                y80Var.a(x80Var, lifecycle$EventB);
                arrayList.remove(arrayList.size() - 1);
                lifecycle$StateC = c(w80Var);
            }
            if (i == 0) {
                h();
            }
            this.e--;
        }
    }

    @Override // defpackage.s80
    public final void b(w80 w80Var) {
        d("removeObserver");
        this.b.b(w80Var);
    }

    public final Lifecycle$State c(w80 w80Var) {
        HashMap map = this.b.I;
        vq0 vq0Var = map.containsKey(w80Var) ? ((vq0) map.get(w80Var)).H : null;
        Lifecycle$State lifecycle$State = vq0Var != null ? ((y80) vq0Var.F).a : null;
        ArrayList arrayList = this.h;
        Lifecycle$State lifecycle$State2 = arrayList.isEmpty() ? null : (Lifecycle$State) arrayList.get(arrayList.size() - 1);
        Lifecycle$State lifecycle$State3 = this.c;
        t40.n(lifecycle$State3, "state1");
        if (lifecycle$State == null || lifecycle$State.compareTo(lifecycle$State3) >= 0) {
            lifecycle$State = lifecycle$State3;
        }
        return (lifecycle$State2 == null || lifecycle$State2.compareTo(lifecycle$State) >= 0) ? lifecycle$State : lifecycle$State2;
    }

    public final void d(String str) {
        if (this.a) {
            e8.k0().j.getClass();
            if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
                throw new IllegalStateException(zn.n("Method ", str, " must be called on the main thread").toString());
            }
        }
    }

    public final void e(Lifecycle$Event lifecycle$Event) {
        t40.n(lifecycle$Event, "event");
        d("handleLifecycleEvent");
        f(lifecycle$Event.getTargetState());
    }

    public final void f(Lifecycle$State lifecycle$State) {
        if (this.c == lifecycle$State) {
            return;
        }
        x80 x80Var = (x80) this.d.get();
        Lifecycle$State lifecycle$State2 = this.c;
        t40.n(lifecycle$State2, ApiConstants.CURRENT);
        t40.n(lifecycle$State, "next");
        if (lifecycle$State2 == Lifecycle$State.INITIALIZED && lifecycle$State == Lifecycle$State.DESTROYED) {
            throw new IllegalStateException(("State must be at least '" + Lifecycle$State.CREATED + "' to be moved to '" + lifecycle$State + "' in component " + x80Var).toString());
        }
        Lifecycle$State lifecycle$State3 = Lifecycle$State.DESTROYED;
        if (lifecycle$State2 == lifecycle$State3 && lifecycle$State2 != lifecycle$State) {
            throw new IllegalStateException(("State is '" + lifecycle$State3 + "' and cannot be moved to `" + lifecycle$State + "` in component " + x80Var).toString());
        }
        this.c = lifecycle$State;
        if (this.f || this.e != 0) {
            this.g = true;
            return;
        }
        this.f = true;
        h();
        this.f = false;
        if (this.c == lifecycle$State3) {
            this.b = new tv();
        }
    }

    public final void g(Lifecycle$State lifecycle$State) {
        t40.n(lifecycle$State, ApiConstants.STATE);
        d("setCurrentState");
        f(lifecycle$State);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0030, code lost:
    
        r7.g = false;
        r7.i.b(r7.c);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0039, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void h() {
        /*
            Method dump skipped, instruction units count: 372
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.z80.h():void");
    }
}
