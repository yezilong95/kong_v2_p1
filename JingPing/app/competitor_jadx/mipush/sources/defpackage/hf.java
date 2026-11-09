package defpackage;

import androidx.lifecycle.Lifecycle$Event;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class hf {
    public static final hf c = new hf();
    public final HashMap a = new HashMap();
    public final HashMap b = new HashMap();

    public static void b(HashMap map, gf gfVar, Lifecycle$Event lifecycle$Event, Class cls) {
        Lifecycle$Event lifecycle$Event2 = (Lifecycle$Event) map.get(gfVar);
        if (lifecycle$Event2 == null || lifecycle$Event == lifecycle$Event2) {
            if (lifecycle$Event2 == null) {
                map.put(gfVar, lifecycle$Event);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + gfVar.b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + lifecycle$Event2 + ", new value " + lifecycle$Event);
    }

    public final ff a(Class cls, Method[] methodArr) {
        int i;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        HashMap map2 = this.a;
        if (superclass != null) {
            ff ffVarA = (ff) map2.get(superclass);
            if (ffVarA == null) {
                ffVarA = a(superclass, null);
            }
            map.putAll(ffVarA.b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            ff ffVarA2 = (ff) map2.get(cls2);
            if (ffVarA2 == null) {
                ffVarA2 = a(cls2, null);
            }
            for (Map.Entry entry : ffVarA2.b.entrySet()) {
                b(map, (gf) entry.getKey(), (Lifecycle$Event) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            try {
                methodArr = cls.getDeclaredMethods();
            } catch (NoClassDefFoundError e) {
                throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e);
            }
        }
        boolean z = false;
        for (Method method : methodArr) {
            gj0 gj0Var = (gj0) method.getAnnotation(gj0.class);
            if (gj0Var != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else {
                    if (!x80.class.isAssignableFrom(parameterTypes[0])) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i = 1;
                }
                Lifecycle$Event lifecycle$EventValue = gj0Var.value();
                if (parameterTypes.length > 1) {
                    if (!Lifecycle$Event.class.isAssignableFrom(parameterTypes[1])) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (lifecycle$EventValue != Lifecycle$Event.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                b(map, new gf(i, method), lifecycle$EventValue, cls);
                z = true;
            }
        }
        ff ffVar = new ff(map);
        map2.put(cls, ffVar);
        this.b.put(cls, Boolean.valueOf(z));
        return ffVar;
    }
}
