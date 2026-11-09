package defpackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.FunctionImpl;

/* JADX INFO: loaded from: classes.dex */
public final class jd implements tz, id {
    public static final Map c;
    public static final HashMap d;
    public static final LinkedHashMap e;
    public final Class b;

    static {
        List listX = be.X(ou.class, pu.class, ru.class, su.class, tu.class, uu.class, vu.class, wu.class, FunctionImpl.class, FunctionImpl.class, FunctionImpl.class, FunctionImpl.class, FunctionImpl.class, FunctionImpl.class, FunctionImpl.class, FunctionImpl.class, FunctionImpl.class, FunctionImpl.class, FunctionImpl.class, FunctionImpl.class, FunctionImpl.class, FunctionImpl.class, FunctionImpl.class);
        ArrayList arrayList = new ArrayList(ce.Z(listX));
        int i = 0;
        for (Object obj : listX) {
            int i2 = i + 1;
            if (i < 0) {
                be.Y();
                throw null;
            }
            arrayList.add(new Pair((Class) obj, Integer.valueOf(i)));
            i = i2;
        }
        c = o40.v0(arrayList);
        HashMap map = new HashMap();
        map.put("boolean", "kotlin.Boolean");
        map.put("char", "kotlin.Char");
        map.put("byte", "kotlin.Byte");
        map.put("short", "kotlin.Short");
        map.put("int", "kotlin.Int");
        map.put("float", "kotlin.Float");
        map.put("long", "kotlin.Long");
        map.put("double", "kotlin.Double");
        HashMap map2 = new HashMap();
        map2.put("java.lang.Boolean", "kotlin.Boolean");
        map2.put("java.lang.Character", "kotlin.Char");
        map2.put("java.lang.Byte", "kotlin.Byte");
        map2.put("java.lang.Short", "kotlin.Short");
        map2.put("java.lang.Integer", "kotlin.Int");
        map2.put("java.lang.Float", "kotlin.Float");
        map2.put("java.lang.Long", "kotlin.Long");
        map2.put("java.lang.Double", "kotlin.Double");
        HashMap map3 = new HashMap();
        map3.put("java.lang.Object", "kotlin.Any");
        map3.put("java.lang.String", "kotlin.String");
        map3.put("java.lang.CharSequence", "kotlin.CharSequence");
        map3.put("java.lang.Throwable", "kotlin.Throwable");
        map3.put("java.lang.Cloneable", "kotlin.Cloneable");
        map3.put("java.lang.Number", "kotlin.Number");
        map3.put("java.lang.Comparable", "kotlin.Comparable");
        map3.put("java.lang.Enum", "kotlin.Enum");
        map3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        map3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        map3.put("java.util.Iterator", "kotlin.collections.Iterator");
        map3.put("java.util.Collection", "kotlin.collections.Collection");
        map3.put("java.util.List", "kotlin.collections.List");
        map3.put("java.util.Set", "kotlin.collections.Set");
        map3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        map3.put("java.util.Map", "kotlin.collections.Map");
        map3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        map3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        map3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        map3.putAll(map);
        map3.putAll(map2);
        Collection<String> collectionValues = map.values();
        oy.i(collectionValues, "<get-values>(...)");
        for (String str : collectionValues) {
            StringBuilder sb = new StringBuilder("kotlin.jvm.internal.");
            oy.g(str);
            sb.append(vk0.z0(str));
            sb.append("CompanionObject");
            Pair pair = new Pair(sb.toString(), str.concat(".Companion"));
            map3.put(pair.getFirst(), pair.getSecond());
        }
        for (Map.Entry entry : c.entrySet()) {
            Class cls = (Class) entry.getKey();
            int iIntValue = ((Number) entry.getValue()).intValue();
            map3.put(cls.getName(), "kotlin.Function" + iIntValue);
        }
        d = map3;
        LinkedHashMap linkedHashMap = new LinkedHashMap(o40.u0(map3.size()));
        for (Map.Entry entry2 : map3.entrySet()) {
            linkedHashMap.put(entry2.getKey(), vk0.z0((String) entry2.getValue()));
        }
        e = linkedHashMap;
    }

    public jd(Class cls) {
        oy.j(cls, "jClass");
        this.b = cls;
    }

    @Override // defpackage.id
    public final Class a() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof jd) && jq0.y(this).equals(jq0.y((tz) obj));
    }

    public final int hashCode() {
        return jq0.y(this).hashCode();
    }

    public final String toString() {
        return this.b.toString() + " (Kotlin reflection is not available)";
    }
}
