package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public abstract class ec0 extends o80 {
    public static int k0(int i) {
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

    public static Map l0(ArrayList arrayList) {
        st stVar = st.E;
        int size = arrayList.size();
        if (size == 0) {
            return stVar;
        }
        if (size == 1) {
            Pair pair = (Pair) arrayList.get(0);
            t40.n(pair, "pair");
            Map mapSingletonMap = Collections.singletonMap(pair.getFirst(), pair.getSecond());
            t40.m(mapSingletonMap, "singletonMap(...)");
            return mapSingletonMap;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(k0(arrayList.size()));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Pair pair2 = (Pair) it.next();
            linkedHashMap.put(pair2.component1(), pair2.component2());
        }
        return linkedHashMap;
    }
}
