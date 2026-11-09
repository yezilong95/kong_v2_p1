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
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public abstract class ae extends ge {
    public static final void b0(Collection collection, StringBuilder sb, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, pu puVar) {
        oy.j(collection, "<this>");
        sb.append(charSequence2);
        int i = 0;
        for (Object obj : collection) {
            i++;
            if (i > 1) {
                sb.append(charSequence);
            }
            rp0.c(sb, obj, puVar);
        }
        sb.append(charSequence3);
    }

    public static String d0(Collection collection, String str, f fVar, int i) {
        int i2 = i & 2;
        String str2 = ExtensionRequestData.EMPTY_VALUE;
        String str3 = i2 != 0 ? ExtensionRequestData.EMPTY_VALUE : "[";
        if ((i & 4) == 0) {
            str2 = "]";
        }
        String str4 = str2;
        if ((i & 32) != 0) {
            fVar = null;
        }
        oy.j(collection, "<this>");
        StringBuilder sb = new StringBuilder();
        b0(collection, sb, str, str3, str4, "...", fVar);
        String string = sb.toString();
        oy.i(string, "toString(...)");
        return string;
    }

    public static final void e0(Iterable iterable, AbstractCollection abstractCollection) {
        oy.j(iterable, "<this>");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            abstractCollection.add(it.next());
        }
    }

    public static List f0(Iterable iterable) {
        ArrayList arrayList;
        oy.j(iterable, "<this>");
        boolean z = iterable instanceof Collection;
        if (!z) {
            if (z) {
                arrayList = new ArrayList((Collection) iterable);
            } else {
                arrayList = new ArrayList();
                e0(iterable, arrayList);
            }
            int size = arrayList.size();
            return size != 0 ? size != 1 ? arrayList : xi.G(arrayList.get(0)) : EmptyList.INSTANCE;
        }
        Collection collection = (Collection) iterable;
        int size2 = collection.size();
        if (size2 == 0) {
            return EmptyList.INSTANCE;
        }
        if (size2 != 1) {
            return new ArrayList(collection);
        }
        return xi.G(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
    }

    public static Set g0(Iterable iterable) {
        oy.j(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            e0(iterable, linkedHashSet);
            int size = linkedHashSet.size();
            if (size == 0) {
                return EmptySet.INSTANCE;
            }
            if (size != 1) {
                return linkedHashSet;
            }
            Set setSingleton = Collections.singleton(linkedHashSet.iterator().next());
            oy.i(setSingleton, "singleton(...)");
            return setSingleton;
        }
        Collection collection = (Collection) iterable;
        int size2 = collection.size();
        if (size2 == 0) {
            return EmptySet.INSTANCE;
        }
        if (size2 != 1) {
            LinkedHashSet linkedHashSet2 = new LinkedHashSet(o40.u0(collection.size()));
            e0(iterable, linkedHashSet2);
            return linkedHashSet2;
        }
        Set setSingleton2 = Collections.singleton(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
        oy.i(setSingleton2, "singleton(...)");
        return setSingleton2;
    }
}
