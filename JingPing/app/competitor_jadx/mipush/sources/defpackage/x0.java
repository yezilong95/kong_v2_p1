package defpackage;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class x0 extends AbstractMap implements Map {
    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return new gh((hh) this, jg.G);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return new gh((hh) this, jg.H);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return hh.F.get((hh) this);
    }
}
