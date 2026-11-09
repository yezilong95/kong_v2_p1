package defpackage;

import com.google.gson.internal.LinkedHashTreeMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class h20 extends AbstractSet {
    public final /* synthetic */ int b;
    public final /* synthetic */ LinkedHashTreeMap c;

    public /* synthetic */ h20(LinkedHashTreeMap linkedHashTreeMap, int i) {
        this.b = i;
        this.c = linkedHashTreeMap;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.b) {
            case 0:
                this.c.clear();
                break;
            default:
                this.c.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        j20 j20VarA;
        Object obj2;
        Object value;
        switch (this.b) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                LinkedHashTreeMap linkedHashTreeMap = this.c;
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                j20 j20Var = null;
                if (key != null) {
                    try {
                        j20VarA = linkedHashTreeMap.a(key, false);
                    } catch (ClassCastException unused) {
                        j20VarA = null;
                    }
                    break;
                } else {
                    j20VarA = null;
                }
                if (j20VarA != null && ((obj2 = j20VarA.i) == (value = entry.getValue()) || (obj2 != null && obj2.equals(value)))) {
                    j20Var = j20VarA;
                }
                return j20Var != null;
            default:
                return this.c.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.b) {
            case 0:
                return new g20(this.c, 0);
            default:
                return new g20(this.c, 1);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        j20 j20VarA;
        Object obj2;
        Object value;
        switch (this.b) {
            case 0:
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    LinkedHashTreeMap linkedHashTreeMap = this.c;
                    Object key = entry.getKey();
                    j20 j20Var = null;
                    if (key != null) {
                        try {
                            j20VarA = linkedHashTreeMap.a(key, false);
                        } catch (ClassCastException unused) {
                            j20VarA = null;
                        }
                    } else {
                        j20VarA = null;
                    }
                    if (j20VarA != null && ((obj2 = j20VarA.i) == (value = entry.getValue()) || (obj2 != null && obj2.equals(value)))) {
                        j20Var = j20VarA;
                    }
                    if (j20Var != null) {
                        linkedHashTreeMap.c(j20Var, true);
                        break;
                    }
                    break;
                }
                break;
            default:
                LinkedHashTreeMap linkedHashTreeMap2 = this.c;
                j20 j20VarA2 = null;
                if (obj != null) {
                    try {
                        j20VarA2 = linkedHashTreeMap2.a(obj, false);
                        break;
                    } catch (ClassCastException unused2) {
                    }
                }
                if (j20VarA2 != null) {
                    linkedHashTreeMap2.c(j20VarA2, true);
                }
                if (j20VarA2 != null) {
                }
                break;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        switch (this.b) {
        }
        return this.c.e;
    }
}
