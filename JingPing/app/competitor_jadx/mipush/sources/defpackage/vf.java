package defpackage;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;

/* JADX INFO: loaded from: classes.dex */
public abstract class vf extends bg {
    public static final void j1(Collection collection, StringBuilder sb, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, b10 b10Var) {
        t40.n(collection, "<this>");
        sb.append(charSequence2);
        int i = 0;
        for (Object obj : collection) {
            i++;
            if (i > 1) {
                sb.append(charSequence);
            }
            o80.h(sb, obj, b10Var);
        }
        sb.append(charSequence3);
    }

    public static String k1(Collection collection, String str, p0 p0Var, int i) {
        String str2 = (i & 2) != 0 ? "" : "[";
        String str3 = (i & 4) == 0 ? "]" : "";
        if ((i & 32) != 0) {
            p0Var = null;
        }
        t40.n(collection, "<this>");
        StringBuilder sb = new StringBuilder();
        j1(collection, sb, str, str2, str3, "...", p0Var);
        String string = sb.toString();
        t40.m(string, "toString(...)");
        return string;
    }

    public static final void l1(Iterable iterable, AbstractCollection abstractCollection) {
        t40.n(iterable, "<this>");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            abstractCollection.add(it.next());
        }
    }

    public static List m1(Iterable iterable) {
        ArrayList arrayList;
        t40.n(iterable, "<this>");
        boolean z = iterable instanceof Collection;
        if (!z) {
            if (z) {
                arrayList = new ArrayList((Collection) iterable);
            } else {
                arrayList = new ArrayList();
                l1(iterable, arrayList);
            }
            int size = arrayList.size();
            return size != 0 ? size != 1 ? arrayList : l21.g0(arrayList.get(0)) : EmptyList.INSTANCE;
        }
        Collection collection = (Collection) iterable;
        int size2 = collection.size();
        if (size2 == 0) {
            return EmptyList.INSTANCE;
        }
        if (size2 != 1) {
            return new ArrayList(collection);
        }
        return l21.g0(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
    }

    public static Set n1(Iterable iterable) {
        t40.n(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            l1(iterable, linkedHashSet);
            int size = linkedHashSet.size();
            if (size == 0) {
                return EmptySet.INSTANCE;
            }
            if (size != 1) {
                return linkedHashSet;
            }
            Set setSingleton = Collections.singleton(linkedHashSet.iterator().next());
            t40.m(setSingleton, "singleton(...)");
            return setSingleton;
        }
        Collection collection = (Collection) iterable;
        int size2 = collection.size();
        if (size2 == 0) {
            return EmptySet.INSTANCE;
        }
        if (size2 != 1) {
            LinkedHashSet linkedHashSet2 = new LinkedHashSet(ec0.k0(collection.size()));
            l1(iterable, linkedHashSet2);
            return linkedHashSet2;
        }
        Set setSingleton2 = Collections.singleton(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
        t40.m(setSingleton2, "singleton(...)");
        return setSingleton2;
    }
}
