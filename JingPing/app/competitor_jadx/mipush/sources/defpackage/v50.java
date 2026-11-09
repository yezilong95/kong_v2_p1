package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class v50 extends w50 implements Iterable {
    public final ArrayList E = new ArrayList();

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof v50) && ((v50) obj).E.equals(this.E);
        }
        return true;
    }

    public final int hashCode() {
        return this.E.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.E.iterator();
    }
}
