package defpackage;

import androidx.fragment.app.Fragment$InstantiationException;
import androidx.fragment.app.c;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public final class b00 {
    public static final vv0 b = new vv0();
    public final /* synthetic */ c a;

    public b00(c cVar) {
        this.a = cVar;
    }

    public static Class b(ClassLoader classLoader, String str) throws ClassNotFoundException {
        vv0 vv0Var = b;
        vv0 vv0Var2 = (vv0) vv0Var.getOrDefault(classLoader, null);
        if (vv0Var2 == null) {
            vv0Var2 = new vv0();
            vv0Var.put(classLoader, vv0Var2);
        }
        Class cls = (Class) vv0Var2.getOrDefault(str, null);
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(str, false, classLoader);
        vv0Var2.put(str, cls2);
        return cls2;
    }

    public static Class c(ClassLoader classLoader, String str) {
        try {
            return b(classLoader, str);
        } catch (ClassCastException e) {
            throw new Fragment$InstantiationException(zn.n("Unable to instantiate fragment ", str, ": make sure class is a valid subclass of Fragment"), e);
        } catch (ClassNotFoundException e2) {
            throw new Fragment$InstantiationException(zn.n("Unable to instantiate fragment ", str, ": make sure class name exists"), e2);
        }
    }

    public final vz a(String str) {
        try {
            return (vz) c(this.a.o.c0.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (IllegalAccessException e) {
            throw new Fragment$InstantiationException(zn.n("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e);
        } catch (InstantiationException e2) {
            throw new Fragment$InstantiationException(zn.n("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (NoSuchMethodException e3) {
            throw new Fragment$InstantiationException(zn.n("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e3);
        } catch (InvocationTargetException e4) {
            throw new Fragment$InstantiationException(zn.n("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e4);
        }
    }
}
