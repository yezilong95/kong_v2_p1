package defpackage;

import com.google.gson.internal.LinkedHashTreeMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class l90 extends AbstractSet {
    public final /* synthetic */ int E;
    public final /* synthetic */ LinkedHashTreeMap F;

    public /* synthetic */ l90(LinkedHashTreeMap linkedHashTreeMap, int i) {
        this.E = i;
        this.F = linkedHashTreeMap;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.E) {
            case 0:
                this.F.clear();
                break;
            default:
                this.F.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        n90 n90VarA;
        Object obj2;
        Object value;
        switch (this.E) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                LinkedHashTreeMap linkedHashTreeMap = this.F;
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                n90 n90Var = null;
                if (key != null) {
                    try {
                        n90VarA = linkedHashTreeMap.a(key, false);
                    } catch (ClassCastException unused) {
                        n90VarA = null;
                    }
                    break;
                } else {
                    n90VarA = null;
                }
                if (n90VarA != null && ((obj2 = n90VarA.L) == (value = entry.getValue()) || (obj2 != null && obj2.equals(value)))) {
                    n90Var = n90VarA;
                }
                return n90Var != null;
            default:
                return this.F.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.E) {
            case 0:
                return new k90(this.F, 0);
            default:
                return new k90(this.F, 1);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        n90 n90VarA;
        Object obj2;
        Object value;
        switch (this.E) {
            case 0:
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    LinkedHashTreeMap linkedHashTreeMap = this.F;
                    Object key = entry.getKey();
                    n90 n90Var = null;
                    if (key != null) {
                        try {
                            n90VarA = linkedHashTreeMap.a(key, false);
                        } catch (ClassCastException unused) {
                            n90VarA = null;
                        }
                    } else {
                        n90VarA = null;
                    }
                    if (n90VarA != null && ((obj2 = n90VarA.L) == (value = entry.getValue()) || (obj2 != null && obj2.equals(value)))) {
                        n90Var = n90VarA;
                    }
                    if (n90Var != null) {
                        linkedHashTreeMap.c(n90Var, true);
                        break;
                    }
                    break;
                }
                break;
            default:
                LinkedHashTreeMap linkedHashTreeMap2 = this.F;
                n90 n90VarA2 = null;
                if (obj != null) {
                    try {
                        n90VarA2 = linkedHashTreeMap2.a(obj, false);
                        break;
                    } catch (ClassCastException unused2) {
                    }
                }
                if (n90VarA2 != null) {
                    linkedHashTreeMap2.c(n90VarA2, true);
                }
                if (n90VarA2 != null) {
                }
                break;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        switch (this.E) {
        }
        return this.F.H;
    }
}
