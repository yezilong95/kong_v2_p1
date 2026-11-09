package defpackage;

import com.google.gson.internal.LinkedTreeMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class p90 extends AbstractSet {
    public final /* synthetic */ int E;
    public final /* synthetic */ LinkedTreeMap F;

    public /* synthetic */ p90(LinkedTreeMap linkedTreeMap, int i) {
        this.E = i;
        this.F = linkedTreeMap;
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
        q90 q90VarA;
        Object obj2;
        Object value;
        switch (this.E) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                LinkedTreeMap linkedTreeMap = this.F;
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                q90 q90Var = null;
                if (key != null) {
                    try {
                        q90VarA = linkedTreeMap.a(key, false);
                    } catch (ClassCastException unused) {
                        q90VarA = null;
                    }
                    break;
                } else {
                    q90VarA = null;
                }
                if (q90VarA != null && ((obj2 = q90VarA.K) == (value = entry.getValue()) || (obj2 != null && obj2.equals(value)))) {
                    q90Var = q90VarA;
                }
                return q90Var != null;
            default:
                return this.F.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.E) {
            case 0:
                return new o90(this.F, 0);
            default:
                return new o90(this.F, 1);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        q90 q90VarA;
        Object obj2;
        Object value;
        switch (this.E) {
            case 0:
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    LinkedTreeMap linkedTreeMap = this.F;
                    Object key = entry.getKey();
                    q90 q90Var = null;
                    if (key != null) {
                        try {
                            q90VarA = linkedTreeMap.a(key, false);
                        } catch (ClassCastException unused) {
                            q90VarA = null;
                        }
                    } else {
                        q90VarA = null;
                    }
                    if (q90VarA != null && ((obj2 = q90VarA.K) == (value = entry.getValue()) || (obj2 != null && obj2.equals(value)))) {
                        q90Var = q90VarA;
                    }
                    if (q90Var != null) {
                        linkedTreeMap.c(q90Var, true);
                        break;
                    }
                    break;
                }
                break;
            default:
                LinkedTreeMap linkedTreeMap2 = this.F;
                q90 q90VarA2 = null;
                if (obj != null) {
                    try {
                        q90VarA2 = linkedTreeMap2.a(obj, false);
                        break;
                    } catch (ClassCastException unused2) {
                    }
                }
                if (q90VarA2 != null) {
                    linkedTreeMap2.c(q90VarA2, true);
                }
                if (q90VarA2 != null) {
                }
                break;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        switch (this.E) {
        }
        return this.F.G;
    }
}
