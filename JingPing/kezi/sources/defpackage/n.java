package defpackage;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class n extends AbstractMap implements Map, wz {
    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return new mf((nf) this, pe.d);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return new mf((nf) this, pe.e);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return nf.c.get((nf) this);
    }
}
