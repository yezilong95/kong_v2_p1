package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public abstract class o40 extends jq0 {
    public static int u0(int i) {
        if (i < 0) {
            return i;
        }
        if (i < 3) {
            return i + 1;
        }
        if (i < 1073741824) {
            return (int) ((i / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static Map v0(ArrayList arrayList) {
        ao aoVar = ao.b;
        int size = arrayList.size();
        if (size == 0) {
            return aoVar;
        }
        if (size == 1) {
            Pair pair = (Pair) arrayList.get(0);
            oy.j(pair, "pair");
            Map mapSingletonMap = Collections.singletonMap(pair.getFirst(), pair.getSecond());
            oy.i(mapSingletonMap, "singletonMap(...)");
            return mapSingletonMap;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(u0(arrayList.size()));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Pair pair2 = (Pair) it.next();
            linkedHashMap.put(pair2.component1(), pair2.component2());
        }
        return linkedHashMap;
    }

    public static Map w0(LinkedHashMap linkedHashMap) {
        oy.j(linkedHashMap, "<this>");
        int size = linkedHashMap.size();
        if (size == 0) {
            return ao.b;
        }
        if (size != 1) {
            return new LinkedHashMap(linkedHashMap);
        }
        oy.j(linkedHashMap, "<this>");
        Map.Entry entry = (Map.Entry) linkedHashMap.entrySet().iterator().next();
        Map mapSingletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
        oy.i(mapSingletonMap, "with(...)");
        return mapSingletonMap;
    }
}
