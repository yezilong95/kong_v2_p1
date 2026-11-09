package defpackage;

import android.app.Application;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public abstract class xg0 {
    public static final List a = be.X(Application.class, mg0.class);
    public static final List b = xi.G(mg0.class);

    public static final Constructor a(Class cls, List list) {
        oy.j(list, "signature");
        i iVarW = k9.w(cls.getConstructors());
        while (iVarW.hasNext()) {
            Constructor constructor = (Constructor) iVarW.next();
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            oy.i(parameterTypes, "getParameterTypes(...)");
            int length = parameterTypes.length;
            List arrayList = length != 0 ? length != 1 ? new ArrayList(new y6(parameterTypes, false)) : xi.G(parameterTypes[0]) : EmptyList.INSTANCE;
            if (list.equals(arrayList)) {
                return constructor;
            }
            if (list.size() == arrayList.size() && arrayList.containsAll(list)) {
                throw new UnsupportedOperationException("Class " + cls.getSimpleName() + " must have parameters in the proper order: " + list);
            }
        }
        return null;
    }

    public static final gs0 b(Class cls, Constructor constructor, Object... objArr) {
        try {
            return (gs0) constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access " + cls, e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("A " + cls + " cannot be instantiated.", e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException("An exception happened in constructor of " + cls, e3.getCause());
        }
    }
}
