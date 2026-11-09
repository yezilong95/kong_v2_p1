package defpackage;

import com.google.gson.internal.LinkedTreeMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class l20 extends AbstractSet {
    public final /* synthetic */ int b;
    public final /* synthetic */ LinkedTreeMap c;

    public /* synthetic */ l20(LinkedTreeMap linkedTreeMap, int i) {
        this.b = i;
        this.c = linkedTreeMap;
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
        m20 m20VarA;
        Object obj2;
        Object value;
        switch (this.b) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                LinkedTreeMap linkedTreeMap = this.c;
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                m20 m20Var = null;
                if (key != null) {
                    try {
                        m20VarA = linkedTreeMap.a(key, false);
                    } catch (ClassCastException unused) {
                        m20VarA = null;
                    }
                    break;
                } else {
                    m20VarA = null;
                }
                if (m20VarA != null && ((obj2 = m20VarA.h) == (value = entry.getValue()) || (obj2 != null && obj2.equals(value)))) {
                    m20Var = m20VarA;
                }
                return m20Var != null;
            default:
                return this.c.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.b) {
            case 0:
                return new k20(this.c, 0);
            default:
                return new k20(this.c, 1);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        m20 m20VarA;
        Object obj2;
        Object value;
        switch (this.b) {
            case 0:
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    LinkedTreeMap linkedTreeMap = this.c;
                    Object key = entry.getKey();
                    m20 m20Var = null;
                    if (key != null) {
                        try {
                            m20VarA = linkedTreeMap.a(key, false);
                        } catch (ClassCastException unused) {
                            m20VarA = null;
                        }
                    } else {
                        m20VarA = null;
                    }
                    if (m20VarA != null && ((obj2 = m20VarA.h) == (value = entry.getValue()) || (obj2 != null && obj2.equals(value)))) {
                        m20Var = m20VarA;
                    }
                    if (m20Var != null) {
                        linkedTreeMap.c(m20Var, true);
                        break;
                    }
                    break;
                }
                break;
            default:
                LinkedTreeMap linkedTreeMap2 = this.c;
                m20 m20VarA2 = null;
                if (obj != null) {
                    try {
                        m20VarA2 = linkedTreeMap2.a(obj, false);
                        break;
                    } catch (ClassCastException unused2) {
                    }
                }
                if (m20VarA2 != null) {
                    linkedTreeMap2.c(m20VarA2, true);
                }
                if (m20VarA2 != null) {
                }
                break;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        switch (this.b) {
        }
        return this.c.d;
    }
}
