package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class ar0 {
    public final c7 a;
    public final c7 b;
    public final c7 c;

    public ar0(c7 c7Var, c7 c7Var2, c7 c7Var3) {
        this.a = c7Var;
        this.b = c7Var2;
        this.c = c7Var3;
    }

    public abstract br0 a();

    public final Class b(Class cls) throws ClassNotFoundException {
        String name = cls.getName();
        c7 c7Var = this.c;
        Class cls2 = (Class) c7Var.getOrDefault(name, null);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(cls.getPackage().getName() + "." + cls.getSimpleName() + "Parcelizer", false, cls.getClassLoader());
        c7Var.put(cls.getName(), cls3);
        return cls3;
    }

    public final Method c(String str) throws NoSuchMethodException {
        c7 c7Var = this.a;
        Method method = (Method) c7Var.getOrDefault(str, null);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, ar0.class.getClassLoader()).getDeclaredMethod("read", ar0.class);
        c7Var.put(str, declaredMethod);
        return declaredMethod;
    }

    public final Method d(Class cls) throws NoSuchMethodException, ClassNotFoundException {
        String name = cls.getName();
        c7 c7Var = this.b;
        Method method = (Method) c7Var.getOrDefault(name, null);
        if (method != null) {
            return method;
        }
        Class clsB = b(cls);
        System.currentTimeMillis();
        Method declaredMethod = clsB.getDeclaredMethod("write", cls, ar0.class);
        c7Var.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public abstract boolean e(int i);

    public final Parcelable f(Parcelable parcelable, int i) {
        if (!e(i)) {
            return parcelable;
        }
        return ((br0) this).e.readParcelable(br0.class.getClassLoader());
    }

    public final cr0 g() {
        String string = ((br0) this).e.readString();
        if (string == null) {
            return null;
        }
        try {
            return (cr0) c(string).invoke(null, a());
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

    public final void i(cr0 cr0Var) {
        if (cr0Var == null) {
            ((br0) this).e.writeString(null);
            return;
        }
        try {
            ((br0) this).e.writeString(b(cr0Var.getClass()).getName());
            br0 br0VarA = a();
            try {
                d(cr0Var.getClass()).invoke(null, cr0Var, br0VarA);
                int i = br0VarA.i;
                if (i >= 0) {
                    int i2 = br0VarA.d.get(i);
                    Parcel parcel = br0VarA.e;
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
            throw new RuntimeException(cr0Var.getClass().getSimpleName().concat(" does not have a Parcelizer"), e5);
        }
    }
}
