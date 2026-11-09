package defpackage;

import androidx.fragment.app.Fragment$InstantiationException;
import androidx.fragment.app.c;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public final class pt {
    public static final fj0 b = new fj0();
    public final /* synthetic */ c a;

    public pt(c cVar) {
        this.a = cVar;
    }

    public static Class b(ClassLoader classLoader, String str) throws ClassNotFoundException {
        fj0 fj0Var = b;
        fj0 fj0Var2 = (fj0) fj0Var.getOrDefault(classLoader, null);
        if (fj0Var2 == null) {
            fj0Var2 = new fj0();
            fj0Var.put(classLoader, fj0Var2);
        }
        Class cls = (Class) fj0Var2.getOrDefault(str, null);
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(str, false, classLoader);
        fj0Var2.put(str, cls2);
        return cls2;
    }

    public static Class c(ClassLoader classLoader, String str) {
        try {
            return b(classLoader, str);
        } catch (ClassCastException e) {
            throw new Fragment$InstantiationException(hk.j("Unable to instantiate fragment ", str, ": make sure class is a valid subclass of Fragment"), e);
        } catch (ClassNotFoundException e2) {
            throw new Fragment$InstantiationException(hk.j("Unable to instantiate fragment ", str, ": make sure class name exists"), e2);
        }
    }

    public final jt a(String str) {
        try {
            return (jt) c(this.a.o.u.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (IllegalAccessException e) {
            throw new Fragment$InstantiationException(hk.j("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e);
        } catch (InstantiationException e2) {
            throw new Fragment$InstantiationException(hk.j("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (NoSuchMethodException e3) {
            throw new Fragment$InstantiationException(hk.j("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e3);
        } catch (InvocationTargetException e4) {
            throw new Fragment$InstantiationException(hk.j("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e4);
        }
    }
}
