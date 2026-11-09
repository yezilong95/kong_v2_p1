package defpackage;

import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle$State;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class wg0 implements js0 {
    public final Application b;
    public final is0 c;
    public final Bundle d;
    public final p10 e;
    public final e5 f;

    public wg0(Application application, vg0 vg0Var, Bundle bundle) {
        is0 is0Var;
        this.f = vg0Var.b();
        this.e = vg0Var.getLifecycle();
        this.d = bundle;
        this.b = application;
        if (application != null) {
            if (is0.e == null) {
                is0.e = new is0(application);
            }
            is0Var = is0.e;
            oy.g(is0Var);
        } else {
            is0Var = new is0(null);
        }
        this.c = is0Var;
    }

    @Override // defpackage.js0
    public final gs0 a(Class cls, b80 b80Var) {
        gq0 gq0Var = o70.e;
        LinkedHashMap linkedHashMap = b80Var.a;
        String str = (String) linkedHashMap.get(gq0Var);
        if (str == null) {
            throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
        }
        if (linkedHashMap.get(xi.k) == null || linkedHashMap.get(xi.l) == null) {
            if (this.e != null) {
                return d(cls, str);
            }
            throw new IllegalStateException("SAVED_STATE_REGISTRY_OWNER_KEY andVIEW_MODEL_STORE_OWNER_KEY must be provided in the creation extras tosuccessfully create a ViewModel.");
        }
        Application application = (Application) linkedHashMap.get(is0.f);
        boolean zIsAssignableFrom = j3.class.isAssignableFrom(cls);
        Constructor constructorA = (!zIsAssignableFrom || application == null) ? xg0.a(cls, xg0.b) : xg0.a(cls, xg0.a);
        return constructorA == null ? this.c.a(cls, b80Var) : (!zIsAssignableFrom || application == null) ? xg0.b(cls, constructorA, xi.n(b80Var)) : xg0.b(cls, constructorA, application, xi.n(b80Var));
    }

    @Override // defpackage.js0
    public final gs0 b(Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return d(cls, canonicalName);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // defpackage.js0
    public final gs0 c(jd jdVar, b80 b80Var) {
        Class clsA = jdVar.a();
        oy.h(clsA, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        return a(clsA, b80Var);
    }

    public final gs0 d(Class cls, String str) {
        AutoCloseable autoCloseable;
        Application application;
        p10 p10Var = this.e;
        if (p10Var == null) {
            throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        }
        boolean zIsAssignableFrom = j3.class.isAssignableFrom(cls);
        Constructor constructorA = (!zIsAssignableFrom || this.b == null) ? xg0.a(cls, xg0.b) : xg0.a(cls, xg0.a);
        if (constructorA == null) {
            if (this.b != null) {
                return this.c.b(cls);
            }
            if (gq0.c == null) {
                gq0.c = new gq0();
            }
            oy.g(gq0.c);
            return nv0.l(cls);
        }
        e5 e5Var = this.f;
        oy.g(e5Var);
        mg0 mg0VarJ = nv0.j(e5Var.d(str), this.d);
        ng0 ng0Var = new ng0(str, mg0VarJ);
        ng0Var.t(e5Var, p10Var);
        Lifecycle$State lifecycle$State = ((w10) p10Var).c;
        if (lifecycle$State == Lifecycle$State.INITIALIZED || lifecycle$State.isAtLeast(Lifecycle$State.STARTED)) {
            e5Var.P();
        } else {
            p10Var.a(new oj(p10Var, 1, e5Var));
        }
        gs0 gs0VarB = (!zIsAssignableFrom || (application = this.b) == null) ? xg0.b(cls, constructorA, mg0VarJ) : xg0.b(cls, constructorA, application, mg0VarJ);
        gs0VarB.getClass();
        hs0 hs0Var = gs0VarB.a;
        if (hs0Var == null) {
            return gs0VarB;
        }
        if (hs0Var.d) {
            hs0.a(ng0Var);
            return gs0VarB;
        }
        synchronized (hs0Var.a) {
            autoCloseable = (AutoCloseable) hs0Var.b.put("androidx.lifecycle.savedstate.vm.tag", ng0Var);
        }
        hs0.a(autoCloseable);
        return gs0VarB;
    }
}
