package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public abstract class nu0 extends pu0 {
    public static List S0(lu0 lu0Var) {
        Iterator it = lu0Var.iterator();
        if (!it.hasNext()) {
            return EmptyList.INSTANCE;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return l21.g0(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }
}
