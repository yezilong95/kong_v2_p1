package androidx.work;

import androidx.work.Data;
import defpackage.oy;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ArrayCreatingInputMerger extends InputMerger {
    private final Object concatenateArrayAndNonArray(Object obj, Object obj2, Class<?> cls) {
        int length = Array.getLength(obj);
        Object objNewInstance = Array.newInstance(cls, length + 1);
        System.arraycopy(obj, 0, objNewInstance, 0, length);
        Array.set(objNewInstance, length, obj2);
        oy.i(objNewInstance, "newArray");
        return objNewInstance;
    }

    private final Object concatenateArrays(Object obj, Object obj2) {
        int length = Array.getLength(obj);
        int length2 = Array.getLength(obj2);
        Class<?> componentType = obj.getClass().getComponentType();
        oy.g(componentType);
        Object objNewInstance = Array.newInstance(componentType, length + length2);
        System.arraycopy(obj, 0, objNewInstance, 0, length);
        System.arraycopy(obj2, 0, objNewInstance, length, length2);
        oy.i(objNewInstance, "newArray");
        return objNewInstance;
    }

    private final Object createArrayFor(Object obj, Class<?> cls) {
        Object objNewInstance = Array.newInstance(cls, 1);
        Array.set(objNewInstance, 0, obj);
        oy.i(objNewInstance, "newArray");
        return objNewInstance;
    }

    @Override // androidx.work.InputMerger
    public Data merge(List<Data> list) throws Throwable {
        oy.j(list, "inputs");
        Data.Builder builder = new Data.Builder();
        HashMap map = new HashMap();
        Iterator<Data> it = list.iterator();
        while (it.hasNext()) {
            Map<String, Object> keyValueMap = it.next().getKeyValueMap();
            oy.i(keyValueMap, "input.keyValueMap");
            for (Map.Entry<String, Object> entry : keyValueMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                Class cls = value != null ? value.getClass() : String.class;
                Object obj = map.get(key);
                oy.i(key, "key");
                if (obj != null) {
                    Class<?> cls2 = obj.getClass();
                    if (cls2.equals(cls)) {
                        oy.i(value, "value");
                        value = concatenateArrays(obj, value);
                    } else {
                        if (!oy.c(cls2.getComponentType(), cls)) {
                            throw new IllegalArgumentException();
                        }
                        value = concatenateArrayAndNonArray(obj, value, cls);
                    }
                } else if (!cls.isArray()) {
                    value = createArrayFor(value, cls);
                }
                oy.i(value, "if (existingValue == nul…      }\n                }");
                map.put(key, value);
            }
        }
        builder.putAll(map);
        Data dataBuild = builder.build();
        oy.i(dataBuild, "output.build()");
        return dataBuild;
    }
}
