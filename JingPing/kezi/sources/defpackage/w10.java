package defpackage;

import android.os.Looper;
import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class w10 extends p10 {
    public final boolean a;
    public np b;
    public Lifecycle$State c;
    public final WeakReference d;
    public int e;
    public boolean f;
    public boolean g;
    public final ArrayList h;
    public final ok0 i;

    public w10(u10 u10Var) {
        new AtomicReference(null);
        this.a = true;
        this.b = new np();
        Lifecycle$State lifecycle$State = Lifecycle$State.INITIALIZED;
        this.c = lifecycle$State;
        this.h = new ArrayList();
        this.d = new WeakReference(u10Var);
        this.i = new ok0(lifecycle$State == null ? oy.d : lifecycle$State);
    }

    @Override // defpackage.p10
    public final void a(t10 t10Var) {
        s10 ojVar;
        u10 u10Var;
        ArrayList arrayList = this.h;
        Object obj = null;
        int i = 1;
        d("addObserver");
        Lifecycle$State lifecycle$State = this.c;
        Lifecycle$State lifecycle$State2 = Lifecycle$State.DESTROYED;
        if (lifecycle$State != lifecycle$State2) {
            lifecycle$State2 = Lifecycle$State.INITIALIZED;
        }
        oy.j(lifecycle$State2, "initialState");
        v10 v10Var = new v10();
        HashMap map = x10.a;
        boolean z = t10Var instanceof s10;
        boolean z2 = t10Var instanceof in;
        if (z && z2) {
            ojVar = new oj((in) t10Var, i, (s10) t10Var);
        } else if (z2) {
            ojVar = new oj((in) t10Var, i, obj);
        } else if (z) {
            ojVar = (s10) t10Var;
        } else {
            Class<?> cls = t10Var.getClass();
            if (x10.b(cls) == 2) {
                Object obj2 = x10.b.get(cls);
                oy.g(obj2);
                List list = (List) obj2;
                if (list.size() == 1) {
                    x10.a((Constructor) list.get(0), t10Var);
                    throw null;
                }
                int size = list.size();
                cv[] cvVarArr = new cv[size];
                if (size > 0) {
                    x10.a((Constructor) list.get(0), t10Var);
                    throw null;
                }
                ojVar = new dd0(i, cvVarArr);
            } else {
                ojVar = new oj(t10Var);
            }
        }
        v10Var.b = ojVar;
        v10Var.a = lifecycle$State2;
        np npVar = this.b;
        ig0 ig0VarA = npVar.a(t10Var);
        if (ig0VarA != null) {
            obj = ig0VarA.c;
        } else {
            HashMap map2 = npVar.f;
            ig0 ig0Var = new ig0(t10Var, v10Var);
            npVar.e++;
            ig0 ig0Var2 = npVar.c;
            if (ig0Var2 == null) {
                npVar.b = ig0Var;
                npVar.c = ig0Var;
            } else {
                ig0Var2.d = ig0Var;
                ig0Var.e = ig0Var2;
                npVar.c = ig0Var;
            }
            map2.put(t10Var, ig0Var);
        }
        if (((v10) obj) == null && (u10Var = (u10) this.d.get()) != null) {
            i = (this.e != 0 || this.f) ? 1 : 0;
            Lifecycle$State lifecycle$StateC = c(t10Var);
            this.e++;
            while (v10Var.a.compareTo(lifecycle$StateC) < 0 && this.b.f.containsKey(t10Var)) {
                arrayList.add(v10Var.a);
                n10 n10Var = Lifecycle$Event.Companion;
                Lifecycle$State lifecycle$State3 = v10Var.a;
                n10Var.getClass();
                Lifecycle$Event lifecycle$EventB = n10.b(lifecycle$State3);
                if (lifecycle$EventB == null) {
                    throw new IllegalStateException("no event up from " + v10Var.a);
                }
                v10Var.a(u10Var, lifecycle$EventB);
                arrayList.remove(arrayList.size() - 1);
                lifecycle$StateC = c(t10Var);
            }
            if (i == 0) {
                h();
            }
            this.e--;
        }
    }

    @Override // defpackage.p10
    public final void b(t10 t10Var) {
        oy.j(t10Var, "observer");
        d("removeObserver");
        this.b.b(t10Var);
    }

    public final Lifecycle$State c(t10 t10Var) {
        HashMap map = this.b.f;
        ig0 ig0Var = map.containsKey(t10Var) ? ((ig0) map.get(t10Var)).e : null;
        Lifecycle$State lifecycle$State = ig0Var != null ? ((v10) ig0Var.c).a : null;
        ArrayList arrayList = this.h;
        Lifecycle$State lifecycle$State2 = arrayList.isEmpty() ? null : (Lifecycle$State) arrayList.get(arrayList.size() - 1);
        Lifecycle$State lifecycle$State3 = this.c;
        oy.j(lifecycle$State3, "state1");
        if (lifecycle$State == null || lifecycle$State.compareTo(lifecycle$State3) >= 0) {
            lifecycle$State = lifecycle$State3;
        }
        return (lifecycle$State2 == null || lifecycle$State2.compareTo(lifecycle$State) >= 0) ? lifecycle$State : lifecycle$State2;
    }

    public final void d(String str) {
        if (this.a) {
            t6.W().r.getClass();
            if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
                throw new IllegalStateException(hk.j("Method ", str, " must be called on the main thread").toString());
            }
        }
    }

    public final void e(Lifecycle$Event lifecycle$Event) {
        oy.j(lifecycle$Event, "event");
        d("handleLifecycleEvent");
        f(lifecycle$Event.getTargetState());
    }

    public final void f(Lifecycle$State lifecycle$State) {
        if (this.c == lifecycle$State) {
            return;
        }
        u10 u10Var = (u10) this.d.get();
        Lifecycle$State lifecycle$State2 = this.c;
        oy.j(lifecycle$State2, "current");
        oy.j(lifecycle$State, "next");
        if (lifecycle$State2 == Lifecycle$State.INITIALIZED && lifecycle$State == Lifecycle$State.DESTROYED) {
            throw new IllegalStateException(("State must be at least '" + Lifecycle$State.CREATED + "' to be moved to '" + lifecycle$State + "' in component " + u10Var).toString());
        }
        Lifecycle$State lifecycle$State3 = Lifecycle$State.DESTROYED;
        if (lifecycle$State2 == lifecycle$State3 && lifecycle$State2 != lifecycle$State) {
            throw new IllegalStateException(("State is '" + lifecycle$State3 + "' and cannot be moved to `" + lifecycle$State + "` in component " + u10Var).toString());
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
            this.b = new np();
        }
    }

    public final void g(Lifecycle$State lifecycle$State) {
        oy.j(lifecycle$State, "state");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h() {
        /*
            Method dump skipped, instruction units count: 372
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.w10.h():void");
    }
}
