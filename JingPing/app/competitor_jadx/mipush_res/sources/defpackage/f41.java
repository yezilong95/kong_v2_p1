package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import io.nats.client.support.NatsConstants;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class f41 {
    public final k8 a;
    public final k8 b;
    public final k8 c;

    public f41(k8 k8Var, k8 k8Var2, k8 k8Var3) {
        this.a = k8Var;
        this.b = k8Var2;
        this.c = k8Var3;
    }

    public abstract g41 a();

    public final Class b(Class cls) throws ClassNotFoundException {
        String name = cls.getName();
        k8 k8Var = this.c;
        Class cls2 = (Class) k8Var.getOrDefault(name, null);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(cls.getPackage().getName() + NatsConstants.DOT + cls.getSimpleName() + "Parcelizer", false, cls.getClassLoader());
        k8Var.put(cls.getName(), cls3);
        return cls3;
    }

    public final Method c(String str) throws NoSuchMethodException {
        k8 k8Var = this.a;
        Method method = (Method) k8Var.getOrDefault(str, null);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, f41.class.getClassLoader()).getDeclaredMethod("read", f41.class);
        k8Var.put(str, declaredMethod);
        return declaredMethod;
    }

    public final Method d(Class cls) throws NoSuchMethodException, ClassNotFoundException {
        String name = cls.getName();
        k8 k8Var = this.b;
        Method method = (Method) k8Var.getOrDefault(name, null);
        if (method != null) {
            return method;
        }
        Class clsB = b(cls);
        System.currentTimeMillis();
        Method declaredMethod = clsB.getDeclaredMethod("write", cls, f41.class);
        k8Var.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public abstract boolean e(int i);

    public final Parcelable f(Parcelable parcelable, int i) {
        if (!e(i)) {
            return parcelable;
        }
        return ((g41) this).e.readParcelable(g41.class.getClassLoader());
    }

    public final h41 g() {
        String string = ((g41) this).e.readString();
        if (string == null) {
            return null;
        }
        try {
            return (h41) c(string).invoke(null, a());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
        }
    }

    public abstract void h(int i);

    public final void i(h41 h41Var) {
        if (h41Var == null) {
            ((g41) this).e.writeString(null);
            return;
        }
        try {
            ((g41) this).e.writeString(b(h41Var.getClass()).getName());
            g41 g41VarA = a();
            try {
                d(h41Var.getClass()).invoke(null, h41Var, g41VarA);
                int i = g41VarA.i;
                if (i >= 0) {
                    int i2 = g41VarA.d.get(i);
                    Parcel parcel = g41VarA.e;
                    int iDataPosition = parcel.dataPosition();
                    parcel.setDataPosition(i2);
                    parcel.writeInt(iDataPosition - i2);
                    parcel.setDataPosition(iDataPosition);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
            } catch (InvocationTargetException e4) {
                if (!(e4.getCause() instanceof RuntimeException)) {
                    throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
                }
                throw ((RuntimeException) e4.getCause());
            }
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException(h41Var.getClass().getSimpleName().concat(" does not have a Parcelizer"), e5);
        }
    }
}
