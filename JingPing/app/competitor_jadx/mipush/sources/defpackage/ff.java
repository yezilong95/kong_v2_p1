package defpackage;

import androidx.lifecycle.Lifecycle$Event;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ff {
    public final HashMap a = new HashMap();
    public final HashMap b;

    public ff(HashMap map) {
        this.b = map;
        for (Map.Entry entry : map.entrySet()) {
            Lifecycle$Event lifecycle$Event = (Lifecycle$Event) entry.getValue();
            List arrayList = (List) this.a.get(lifecycle$Event);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.a.put(lifecycle$Event, arrayList);
            }
            arrayList.add((gf) entry.getKey());
        }
    }

    public static void a(List list, x80 x80Var, Lifecycle$Event lifecycle$Event, w80 w80Var) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                gf gfVar = (gf) list.get(size);
                gfVar.getClass();
                try {
                    int i = gfVar.a;
                    Method method = gfVar.b;
                    if (i == 0) {
                        method.invoke(w80Var, new Object[0]);
                    } else if (i == 1) {
                        method.invoke(w80Var, x80Var);
                    } else if (i == 2) {
                        method.invoke(w80Var, x80Var, lifecycle$Event);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException("Failed to call observer method", e2.getCause());
                }
            }
        }
    }
}
