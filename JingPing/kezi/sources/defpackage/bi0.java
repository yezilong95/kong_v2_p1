package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class bi0 implements Iterable, wz {
    public final /* synthetic */ ek b;

    public bi0(ek ekVar) {
        this.b = ekVar;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new dk(this.b);
    }
}
