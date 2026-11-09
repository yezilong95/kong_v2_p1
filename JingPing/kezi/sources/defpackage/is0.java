package defpackage;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public final class is0 extends gq0 {
    public static is0 e;
    public static final gq0 f = new gq0();
    public final Application d;

    public is0(Application application) {
        this.d = application;
    }

    @Override // defpackage.gq0, defpackage.js0
    public final gs0 a(Class cls, b80 b80Var) {
        if (this.d != null) {
            return b(cls);
        }
        Application application = (Application) b80Var.a.get(f);
        if (application != null) {
            return d(cls, application);
        }
        if (j3.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("CreationExtras must have an application by `APPLICATION_KEY`");
        }
        return nv0.l(cls);
    }

    @Override // defpackage.gq0, defpackage.js0
    public final gs0 b(Class cls) {
        Application application = this.d;
        if (application != null) {
            return d(cls, application);
        }
        throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
    }

    public final gs0 d(Class cls, Application application) {
        if (!j3.class.isAssignableFrom(cls)) {
            return nv0.l(cls);
        }
        try {
            gs0 gs0Var = (gs0) cls.getConstructor(Application.class).newInstance(application);
            oy.g(gs0Var);
            return gs0Var;
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Cannot create an instance of " + cls, e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException("Cannot create an instance of " + cls, e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("Cannot create an instance of " + cls, e4);
        } catch (InvocationTargetException e5) {
            throw new RuntimeException("Cannot create an instance of " + cls, e5);
        }
    }
}
