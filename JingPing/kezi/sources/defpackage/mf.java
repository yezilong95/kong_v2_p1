package defpackage;

import java.util.Iterator;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class mf extends o {
    public final Lambda b;
    public final /* synthetic */ nf c;

    /* JADX WARN: Multi-variable type inference failed */
    public mf(nf nfVar, ru ruVar) {
        this.c = nfVar;
        this.b = (Lambda) ruVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override // defpackage.o
    public final int getSize() {
        return this.c.size();
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [kotlin.jvm.internal.Lambda, ru] */
    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        kf kfVar = (kf) nf.d.get(this.c);
        kfVar.getClass();
        return new jf(kfVar, this.b);
    }
}
