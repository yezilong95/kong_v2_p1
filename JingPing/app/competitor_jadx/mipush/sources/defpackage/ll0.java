package defpackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class ll0 {
    public static final Map a;

    static {
        HashMap map = new HashMap(16);
        HashMap map2 = new HashMap(16);
        Class cls = Boolean.TYPE;
        map.put(cls, Boolean.class);
        map2.put(Boolean.class, cls);
        Class cls2 = Byte.TYPE;
        map.put(cls2, Byte.class);
        map2.put(Byte.class, cls2);
        Class cls3 = Character.TYPE;
        map.put(cls3, Character.class);
        map2.put(Character.class, cls3);
        Class cls4 = Double.TYPE;
        map.put(cls4, Double.class);
        map2.put(Double.class, cls4);
        Class cls5 = Float.TYPE;
        map.put(cls5, Float.class);
        map2.put(Float.class, cls5);
        Class cls6 = Integer.TYPE;
        map.put(cls6, Integer.class);
        map2.put(Integer.class, cls6);
        Class cls7 = Long.TYPE;
        map.put(cls7, Long.class);
        map2.put(Long.class, cls7);
        Class cls8 = Short.TYPE;
        map.put(cls8, Short.class);
        map2.put(Short.class, cls8);
        Class cls9 = Void.TYPE;
        map.put(cls9, Void.class);
        map2.put(Void.class, cls9);
        a = Collections.unmodifiableMap(map);
        Collections.unmodifiableMap(map2);
    }
}
